package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Villa extends AbstractAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVilla;

    private Integer nbrChambreMax;
    private Integer nbrChambreMin;

    private Integer nbrSalleDeBainMax;
    private Integer nbrSalleDeBainMin;

    private Integer etageMax;
    private Integer etageMin;

    private boolean isGarage;

    @OneToOne
    private BienImmobilier bienImmobilier;


}
