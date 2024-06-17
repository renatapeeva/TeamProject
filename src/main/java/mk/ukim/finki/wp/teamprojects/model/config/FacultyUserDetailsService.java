package mk.ukim.finki.wp.teamprojects.model.config;


import mk.ukim.finki.wp.teamprojects.model.Professor;
import mk.ukim.finki.wp.teamprojects.model.User;
import mk.ukim.finki.wp.teamprojects.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.wp.teamprojects.repository.ProfessorRepository;
import mk.ukim.finki.wp.teamprojects.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FacultyUserDetailsService implements UserDetailsService {

    @Value("${system.authentication.password}")
    String systemAuthenticationPassword;

    final UserRepository userRepository;

    final ProfessorRepository professorService;

    final PasswordEncoder passwordEncoder;

    public FacultyUserDetailsService(UserRepository userRepository, ProfessorRepository professorService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.professorService = professorService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow(InvalidUsernameException::new);
        if (user.getRole().isProfessor()) {
            Professor professor = professorService.findById(username).orElseThrow(InvalidUsernameException::new);
            return new FacultyUserDetails(user, professor, passwordEncoder.encode(systemAuthenticationPassword));
        } else {
            return new FacultyUserDetails(user, passwordEncoder.encode(systemAuthenticationPassword));
        }
    }
}
