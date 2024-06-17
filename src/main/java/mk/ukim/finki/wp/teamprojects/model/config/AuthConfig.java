package mk.ukim.finki.wp.teamprojects.model.config;

import mk.ukim.finki.wp.teamprojects.model.enums.AppRole;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

public class AuthConfig {

    public HttpSecurity authorize(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/admin/subject-requests/my",
                                "/engagement/my",
                                "/engagement/add",
                                "/engagement/edit/**",
                                "/engagement/save/**",
                                "/engagement/delete/**",
                                "/admin/joined-subjects",
                                "/admin/joined-subjects/add",
                                "/admin/joined-subjects/edit/**",
                                "/admin/joined-subjects/save",
                                "/admin/course-preferences/add",
                                "/admin/course-preferences/edit/**",
                                "/admin/course-preferences/save",
                                "/admin/course-preferences").hasAnyRole(
                                AppRole.PROFESSOR.name(),
                                AppRole.ADMIN.name()
                        )
                        .requestMatchers("/admin/subject-requests/{professorId}/**").access(
                                new WebExpressionAuthorizationManager("#professorId == authentication.name or hasRole('ROLE_ADMIN')")
                        )
                        .requestMatchers("/engagement/{professorId}/**").access(
                                new WebExpressionAuthorizationManager("#professorId == authentication.name or hasRole('ROLE_ADMIN')")
                        )
                        .requestMatchers("/admin/**", "/api/**", "/build/**", "/engagement/init").hasAnyRole(
                                AppRole.ADMIN.name()
                        )
                        .requestMatchers("/active-subjects", "io.png", "/allocation/*").permitAll()
                        .anyRequest().authenticated()
                )
                .logout(LogoutConfigurer::permitAll);
    }

}
