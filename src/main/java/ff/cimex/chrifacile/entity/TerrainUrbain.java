package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TerrainUrbain extends AbstractAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTerrainUrbain;

    @ManyToOne
    @JoinColumn(name = "id_quartier")
    private Quartier quartier;

    @ManyToOne
    @JoinColumn(name = "name_autorisation")
    private Authorization authorization;

    @OneToOne(mappedBy = "terrainUrbain")
    private Annonce annonce;
}
