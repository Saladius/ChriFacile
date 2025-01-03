package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Appart extends AbstractAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAppart;

    private Integer nbrChambreMax;
    private Integer nbrChambreMin;

    private Integer nbrSalleDeBainMax;
    private Integer nbrSalleDeBainMin;

    private Integer etageMax;
    private Integer etageMin;

    @OneToOne
    private BienImmobilier bienImmobilier;



}
