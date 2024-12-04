package ff.cimex.chrifacile.service.impl;

import ff.cimex.chrifacile.constant.ConfigConstant;
import ff.cimex.chrifacile.entity.Abonnement;
import ff.cimex.chrifacile.entity.AchatAbonnement;
import ff.cimex.chrifacile.entity.UserEntity;
import ff.cimex.chrifacile.enums.Role;
import ff.cimex.chrifacile.exception.EmailExistsException;
import ff.cimex.chrifacile.exception.UsernameExistsException;
import ff.cimex.chrifacile.exception.UsernameNotFoundException;
import ff.cimex.chrifacile.repository.UserRepository;
import ff.cimex.chrifacile.request.dto.*;
import ff.cimex.chrifacile.response.dto.UserDetailRecord;
import ff.cimex.chrifacile.service.AbonnementService;
import ff.cimex.chrifacile.service.UserService;
import ff.cimex.chrifacile.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AbonnementService abonnementService;



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
            user.setPassword(new BCryptPasswordEncoder().encode(ConfigConstant.SOCIAL_MEDIA_PASSWORD_ACCOUNT));
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
            user.setPassword(new BCryptPasswordEncoder().encode(ConfigConstant.SOCIAL_MEDIA_PASSWORD_ACCOUNT));
        }
        user.setFacebookToken(request.getFacebookToken());
        return userRepository.save(user);
    }

    @Override
    public UserEntity loginWithGoogle(LoginRequestWithGoogle request) {
        return userRepository.findByGoogleToken(request.getGoogleToken()).orElse(null);
    }

    @Override
    public  UserEntity loginWithFacebook(LoginRequestWithFacebook request){
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

    @Override
    public UserEntity getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Date userHaveValidSubscription() {

        List<AchatAbonnement> abonnements = getCurrentUser().getVendeur().getAbonnements();

        if(abonnementService.isThereAnActifAbonnement(abonnements)){
            return new Date();
        }
        return null;
    }

    @Override
    public void subscribNewAbonnement(AbonnementDto abonnementDto) {
        UserEntity user = getCurrentUser();
        Abonnement abonnement = abonnementService.getOrCreateAbonnement(abonnementDto);

        abonnementService.subscribNewAbonnement(abonnement, user.getVendeur());
    }

    @Override
    public void deleteUser() {
        userRepository.delete(getCurrentUser());
    }

    @Override
    public UserDetailRecord getUserDetailRecord(){
        UserEntity user = getCurrentUser();
        return new UserDetailRecord(user.getUsername(),user.getEmail());
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
