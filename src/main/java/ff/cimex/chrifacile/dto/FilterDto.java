package ff.cimex.chrifacile.dto;

import ff.cimex.chrifacile.enums.Type;
import lombok.Data;

@Data
public class FilterDto {
    private Type type;
    private VilleDto villeDto;

    private Double superficie;
    private Long prix;

    private AppartDto appartDto;
    private TerrainAgricoleDto terrainAgricoleDto;
    private TerrainUrbainDto terrainUrbainDto;
}
