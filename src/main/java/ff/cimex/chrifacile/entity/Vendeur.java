package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Vendeur extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVendeur;

    @OneToOne
    private UserEntity user;

    @OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AchatAbonnement> abonnements;

    private boolean actif;
}
