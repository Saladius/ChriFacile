package ff.cimex.chrifacile.request.dto;

import lombok.Data;

@Data
public class UserRegistrationGoogleDto {

    private String fullName;
    private String email;
    private String googleToken;
}
