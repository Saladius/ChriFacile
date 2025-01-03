package ff.cimex.chrifacile.request.dto;

import ff.cimex.chrifacile.enums.Type;
import ff.cimex.chrifacile.enums.Unites;
import lombok.Data;

@Data
public class AnnonceDto {
    private Long idAnnonce;
    private String ville;
    private Type type;
    private Double superficieMax;
    private Double superficieMin;
    private Unites unites;
    private Long prixMax;
    private Long prixMin;
    private BienImmobilierDto bienImmobilierDto;
    private TerrainAgricoleDto terrainAgricoleDto;
    private TerrainUrbainDto terrainUrbainDto;
    private String nomAcheteur;
    private String numTelephone;
    private String description;
}
