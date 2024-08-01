package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAbonnement;

    private String nomAbonnement;
    private int nbrJour;
    private double prix;

    @OneToMany(mappedBy = "abonnement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AchatAbonnement> achatabonnements;
}
