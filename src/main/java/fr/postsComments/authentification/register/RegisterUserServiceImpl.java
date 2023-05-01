package fr.postsComments.authentification.register;

import fr.postsComments.authentification.models.ERole;
import fr.postsComments.authentification.models.Role;
import fr.postsComments.authentification.models.UserApp;
import fr.postsComments.authentification.repository.RoleRepo;
import fr.postsComments.authentification.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private final UserRepo userRepository;

    private final RoleRepo roleRepository;

    private final PasswordEncoder encoder;

    public RegisterUserServiceImpl(UserRepo userRepository, RoleRepo roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public void register(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("user already exist");
        }
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findOneByNameRole(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findOneByNameRole(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findOneByNameRole(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        // Create new user's account
        UserApp user = new UserApp(signUpRequest.getEmail()
                , encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(), roles);

        userRepository.save(user);
    }
}