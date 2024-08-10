package ff.cimex.chrifacile.dto;

import ff.cimex.chrifacile.enums.Type;
import ff.cimex.chrifacile.enums.Unites;
import lombok.Data;

@Data
public class AnnonceDto {
    private VilleDto ville;
    private Type type;
    private Double superficieMax;
    private Double superficieMin;
    private Unites unites;
    private Long prixMax;
    private Long prixMin;
    private AppartDto appartDto;
    private TerrainAgricoleDto terrainAgricoleDto;
    private TerrainUrbainDto terrainUrbainDto;
}
