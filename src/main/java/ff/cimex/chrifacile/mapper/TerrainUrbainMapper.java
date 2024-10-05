package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.request.dto.TerrainUrbainDto;
import ff.cimex.chrifacile.entity.TerrainUrbain;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TerrainUrbainMapper {

    // Convert Entity to DTO
    public static TerrainUrbainDto mapToDto(TerrainUrbain terrainUrbain) {
        TerrainUrbainDto dto = new TerrainUrbainDto();

        dto.setQuartier(terrainUrbain.getQuartier());

        // Convert Autorisation to AutorisationDto
        if (terrainUrbain.getAuthorization() != null) {
            dto.setAuthorization(terrainUrbain.getAuthorization());
        }

        return dto;
    }

    // Convert DTO to Entity
    public static TerrainUrbain mapToEntity(TerrainUrbainDto dto) {
        TerrainUrbain terrainUrbain = new TerrainUrbain();

        terrainUrbain.setQuartier(dto.getQuartier());

        // Convert AutorisationDto to Autorisation
        if (dto.getAuthorization() != null) {
            terrainUrbain.setAuthorization(dto.getAuthorization());
        }

        return terrainUrbain;
    }
}