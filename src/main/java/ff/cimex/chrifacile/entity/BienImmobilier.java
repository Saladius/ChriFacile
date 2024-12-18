package ff.cimex.chrifacile.entity;

import ff.cimex.chrifacile.enums.Type;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BienImmobilier extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBienImmobilier;

    private String quartier;

    @Enumerated(EnumType.STRING)
    private Type typeBien;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_immeuble")
    private Immeuble immeuble;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_villa")
    private Villa villa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_riad")
    private Riad riad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_appart")
    private Appart appart;

    @OneToOne
    private Annonce annonce;
}
