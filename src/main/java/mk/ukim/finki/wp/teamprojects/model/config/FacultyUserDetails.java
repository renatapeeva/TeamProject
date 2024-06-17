package mk.ukim.finki.wp.teamprojects.model.config;


import mk.ukim.finki.wp.teamprojects.model.Professor;
import mk.ukim.finki.wp.teamprojects.model.Student;
import mk.ukim.finki.wp.teamprojects.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class FacultyUserDetails implements UserDetails {

    private User user;

    private Student student;

    private Professor professor;

    private String password;

    public FacultyUserDetails(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public FacultyUserDetails(User user, Professor professor, String password) {
        this.user = user;
        this.professor = professor;
        this.password = password;
    }

    public FacultyUserDetails(User user, Student student, String password) {
        this.user = user;
        this.student = student;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().applicationRole.roleName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Student getStudent() {
        return student;
    }
}
