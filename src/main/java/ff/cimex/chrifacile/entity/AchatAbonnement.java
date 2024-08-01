package ff.cimex.chrifacile.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class AchatAbonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAchatAbonnement;

    @ManyToOne
    @JoinColumn(name = "vendeur_id")
    private Vendeur vendeur;

    @ManyToOne
    @JoinColumn(name = "abonnement_id")
    private Abonnement abonnement;

    private Date dateDebut;
    private Date dateFin;
}
