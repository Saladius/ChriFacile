package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.dto.TerrainAgricoleDto;
import ff.cimex.chrifacile.entity.TerrainAgricole;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TerrainAgricoleMapper {

    // Convert Entity to DTO
    public static TerrainAgricoleDto mapToDto(TerrainAgricole terrainAgricole) {
        TerrainAgricoleDto dto = new TerrainAgricoleDto();
        dto.setPuit(terrainAgricole.isPuit());
        dto.setBattie(terrainAgricole.isBattie());
        return dto;
    }

    // Convert DTO to Entity
    public static TerrainAgricole mapToEntity(TerrainAgricoleDto dto) {
        TerrainAgricole terrainAgricole = new TerrainAgricole();
        terrainAgricole.setPuit(dto.isPuit());
        terrainAgricole.setBattie(dto.isBattie());
        return terrainAgricole;
    }
}