package ff.cimex.chrifacile.request.dto;

import lombok.Data;

@Data
public class UserRegistrationFacebookDto {
    private String fullName;
    private String email;
    private String facebookToken;
}
