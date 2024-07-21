package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class TerrainAgricole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTerrainAgricole;

    private boolean puit;

    @OneToOne(cascade = CascadeType.ALL)
    private Annonce annonce;
}
