package ff.cimex.chrifacile.request.dto;

import ff.cimex.chrifacile.enums.Type;
import lombok.Data;

@Data
public class FilterDto {
    private Type type;
    private String ville;

    private Double superficie;
    private Long prixMin;
    private Long prixMax;

    private BienImmobilierDto bienImmobilierDto;
    private TerrainAgricoleDto terrainAgricoleDto;
    private TerrainUrbainDto terrainUrbainDto;
}
