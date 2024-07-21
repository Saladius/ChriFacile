package ff.cimex.chrifacile.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<String> roles;

}
