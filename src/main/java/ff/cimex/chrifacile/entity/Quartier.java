package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Quartier extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuartier;

    private String nomQuartier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idVille", referencedColumnName="idVille", insertable=false, updatable=false)
    private Ville ville;

    @OneToMany(mappedBy = "quartier")
    private List<Appart> apparts = new ArrayList<Appart>();

    @OneToMany(mappedBy = "quartier")
    private List<TerrainUrbain> terrainUrbains = new ArrayList<>();
}
