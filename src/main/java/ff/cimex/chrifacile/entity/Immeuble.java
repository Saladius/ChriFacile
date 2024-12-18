package ff.cimex.chrifacile.entity;

import ff.cimex.chrifacile.enums.TypeImmeuble;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Immeuble extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImmeuble;

    private Integer etageMax;
    private Integer etageMin;

    private Integer nbrAppartMax;
    private Integer nbrAppartMin;

    private boolean isGarage;

    @Enumerated(EnumType.STRING)
    private TypeImmeuble typeImmeuble;

    @OneToOne
    private BienImmobilier bienImmobilier;
}
