package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Appart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAppart;

    private Integer nbrChambre;

    private Integer nbrSalleDeBain;

    @ManyToOne
    @JoinColumn(name = "id_quartier")
    private Quartier quartier;

    @OneToOne(cascade = CascadeType.ALL)
    private Annonce annonce;
}
