package ff.cimex.chrifacile.service.impl;

import ff.cimex.chrifacile.entity.UserEntity;
import ff.cimex.chrifacile.enums.Role;
import ff.cimex.chrifacile.exception.EmailExistsException;
import ff.cimex.chrifacile.exception.UsernameExistsException;
import ff.cimex.chrifacile.repository.UserRepository;
import ff.cimex.chrifacile.request.dto.UserRegistrationDto;
import ff.cimex.chrifacile.request.dto.UserRegistrationFacebookDto;
import ff.cimex.chrifacile.request.dto.UserRegistrationGoogleDto;
import ff.cimex.chrifacile.service.UserService;
import ff.cimex.chrifacile.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Value("${socialmedia.account.password}")
    private static String socialMediaAccountPassword;


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

    @Override
    public UserEntity registerUserWithGoogle(UserRegistrationGoogleDto request) {
        String username = generateUsername(request.getFullName());
        UserEntity user = findUserbyEmail(request.getEmail());

        if (user.getEmail() == null) {
            user.setUsername(username);
            user.setEmail(request.getEmail());
            user.setRoles(Collections.singleton(Role.ROLE_VENDEUR));
            user.setPassword(socialMediaAccountPassword);
        }
        user.setGoogleToken(request.getGoogleToken());
        return userRepository.save(user);
    }

    @Override
    public UserEntity registerUserWithFacebook(UserRegistrationFacebookDto request) {
        String username = generateUsername(request.getFullName());
        UserEntity user = findUserbyEmail(request.getEmail());

        if (user.getEmail() == null) {
            user.setUsername(username);
            user.setEmail(request.getEmail());
            user.setRoles(Collections.singleton(Role.ROLE_VENDEUR));
            user.setPassword(socialMediaAccountPassword);
        }
        user.setFacebookToken(request.getFacebookToken());
        return userRepository.save(user);
    }

    @Override
    public UserEntity loginWithGoogle(UserRegistrationGoogleDto request) {
        return userRepository.findByGoogleToken(request.getGoogleToken()).orElse(null);
    }

    @Override
    public  UserEntity loginWithFacebook(UserRegistrationFacebookDto request){
        return userRepository.findByFacebookToken(request.getFacebookToken()).orElse(null);
    }



    @Override
    public String getJwtToken(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtUtil.generateToken(authentication);
        }catch (Exception e){
            return null;
        }
    }

    private String generateUsername(String fullname) {
        if (!userRepository.existsByUsername(fullname)) {
            return fullname;
        }

        int uniqueIndex = getUniqueSuffixIndex(fullname);
        return fullname + " - " + uniqueIndex;
    }

    private int getUniqueSuffixIndex(String fullname) {
        int i = 0;
        while (userRepository.existsByUsername(fullname + " - " + i)) {
            i++;
        }
        return i;
    }

    private UserEntity findUserbyEmail(String email) {
        return userRepository.findByEmail(email).orElse(new UserEntity());
    }
}
