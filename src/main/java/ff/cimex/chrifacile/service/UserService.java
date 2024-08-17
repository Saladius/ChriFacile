package ff.cimex.chrifacile.service;

import ff.cimex.chrifacile.request.dto.UserRegistrationDto;
import ff.cimex.chrifacile.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity registerUser(UserRegistrationDto userRegistrationDto);

}
