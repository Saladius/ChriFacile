package ff.cimex.chrifacile.service;

import ff.cimex.chrifacile.request.dto.*;
import ff.cimex.chrifacile.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity registerUser(UserRegistrationDto userRegistrationDto);

    UserEntity registerUserWithGoogle(UserRegistrationGoogleDto request);

    UserEntity registerUserWithFacebook(UserRegistrationFacebookDto request);

    UserEntity loginWithGoogle(LoginRequestWithGoogle request);

    UserEntity loginWithFacebook(LoginRequestWithFacebook request);

    String getJwtToken(String username, String password);
}
