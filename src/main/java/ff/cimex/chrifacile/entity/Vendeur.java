package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vendeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVendeur;

    @OneToOne
    private UserEntity user;

    @OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AchatAbonnement> abonnements;

    private boolean actif;
}
