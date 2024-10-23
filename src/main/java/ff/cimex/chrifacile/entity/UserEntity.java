package ff.cimex.chrifacile.entity;


import ff.cimex.chrifacile.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class UserEntity extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Collection<Role> roles;

    @OneToOne(fetch = FetchType.LAZY)
    private Vendeur vendeur;

    private String googleToken;
    private String facebookToken;
}
