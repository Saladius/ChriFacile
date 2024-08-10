package ff.cimex.chrifacile.dto;

import com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap;
import lombok.Data;

@Data
public class UserRegistrationDto {

    private String username;
    private String password;
    private String email;
}
