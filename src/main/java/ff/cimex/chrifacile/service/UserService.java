package ff.cimex.chrifacile.service;

import ff.cimex.chrifacile.dto.UserRegistrationDto;
import ff.cimex.chrifacile.entity.UserEntity;
import ff.cimex.chrifacile.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity registerUser(UserRegistrationDto userRegistrationDto);

}
