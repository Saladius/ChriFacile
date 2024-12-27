package ff.cimex.chrifacile.service;

import ff.cimex.chrifacile.request.dto.*;
import ff.cimex.chrifacile.entity.UserEntity;
import ff.cimex.chrifacile.response.dto.UserDetailRecord;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface UserService {

    UserEntity registerUser(UserRegistrationDto userRegistrationDto);

    UserEntity registerUserWithGoogle(UserRegistrationGoogleDto request);

    UserEntity registerUserWithFacebook(UserRegistrationFacebookDto request);

    UserEntity loginWithGoogle(LoginRequestWithGoogle request);

    UserEntity loginWithFacebook(LoginRequestWithFacebook request);

    String getJwtToken(String username, String password);

    UserEntity getCurrentUser();

    Date userHaveValidSubscription();

    void subscribNewAbonnement(AbonnementDto abonnement);

    UserDetailRecord getUserDetailRecord();

    void deleteUser(String jwtToken);
}
