package ff.cimex.chrifacile.service.impl;

import ff.cimex.chrifacile.dto.UserRegistrationDto;
import ff.cimex.chrifacile.entity.UserEntity;
import ff.cimex.chrifacile.enums.Role;
import ff.cimex.chrifacile.exception.EmailExistsException;
import ff.cimex.chrifacile.exception.UsernameExistsException;
import ff.cimex.chrifacile.repository.UserRepository;
import ff.cimex.chrifacile.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Override
    public UserEntity registerUser(UserRegistrationDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameExistsException("Le nom d'utilisateur est déjà pris !");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistsException("L'e-mail est déjà utilisé !");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setRoles(Collections.singleton(Role.ROLE_VENDEUR));

        return userRepository.save(user);
    }
}
