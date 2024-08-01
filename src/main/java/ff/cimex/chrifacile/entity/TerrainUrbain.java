package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class TerrainUrbain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTerrainUrbain;

    @ManyToOne
    @JoinColumn(name = "id_quartier")
    private Quartier quartier;

    @ManyToOne
    @JoinColumn(name = "name_autorisation")
    private Autorisation autorisation;

}
