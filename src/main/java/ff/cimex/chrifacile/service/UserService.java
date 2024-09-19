package ff.cimex.chrifacile.service;

import ff.cimex.chrifacile.request.dto.UserRegistrationDto;
import ff.cimex.chrifacile.entity.UserEntity;
import ff.cimex.chrifacile.request.dto.UserRegistrationFacebookDto;
import ff.cimex.chrifacile.request.dto.UserRegistrationGoogleDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity registerUser(UserRegistrationDto userRegistrationDto);

    UserEntity registerUserWithGoogle(UserRegistrationGoogleDto request);

    UserEntity registerUserWithFacebook(UserRegistrationFacebookDto request);

    UserEntity loginWithGoogle(UserRegistrationGoogleDto request);

    UserEntity loginWithFacebook(UserRegistrationFacebookDto request);

    String getJwtToken(String username, String password);
}
