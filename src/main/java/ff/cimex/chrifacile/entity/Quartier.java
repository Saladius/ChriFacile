package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Quartier {

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
