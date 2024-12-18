package ff.cimex.chrifacile.entity;


import ff.cimex.chrifacile.enums.Type;
import ff.cimex.chrifacile.enums.Unites;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Annonce extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnonce;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Double superficieMax;

    private Double superficieMin;

    private Unites unites;

    private Long prixMin;

    private Long prixMax;

    private String ville;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_terrain_urbain")
    private TerrainUrbain terrainUrbain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_terrain_agricole")
    private TerrainAgricole terrainAgricole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bien_immobilier")
    private BienImmobilier bienImmobilier;

    private String nomAcheteur;

    private String numTelephone;

    private String description;
}
