package ff.cimex.chrifacile.request.dto;

import ff.cimex.chrifacile.enums.Type;
import lombok.Data;

@Data
public class FilterDto {
    private Type type;
    private String villeDto;

    private Double superficie;
    private Long prixMin;
    private Long prixMax;

    private AppartDto appartDto;
    private TerrainAgricoleDto terrainAgricoleDto;
    private TerrainUrbainDto terrainUrbainDto;
}
