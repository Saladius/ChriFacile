package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TerrainAgricole extends AbstractAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTerrainAgricole;

    private boolean puit;

    private boolean battie;

    @OneToOne
    private Annonce annonce;
}
