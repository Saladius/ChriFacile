package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.dto.TerrainUrbainDto;
import ff.cimex.chrifacile.entity.Authorization;
import ff.cimex.chrifacile.entity.TerrainUrbain;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TerrainUrbainMapper {

    // Convert Entity to DTO
    public static TerrainUrbainDto mapToDto(TerrainUrbain terrainUrbain) {
        TerrainUrbainDto dto = new TerrainUrbainDto();

        // Convert Quartier to QuartierDto
        if (terrainUrbain.getQuartier() != null) {
            dto.setQuartier(QuartierMapper.mapToDto(terrainUrbain.getQuartier()));
        }

        // Convert Autorisation to AutorisationDto
        if (terrainUrbain.getAuthorization() != null) {
            dto.setAuthorization(AuthorizationMapper.mapToDto(terrainUrbain.getAuthorization()));
        }

        return dto;
    }

    // Convert DTO to Entity
    public static TerrainUrbain mapToEntity(TerrainUrbainDto dto) {
        TerrainUrbain terrainUrbain = new TerrainUrbain();

        // Convert QuartierDto to Quartier
        if (dto.getQuartier() != null) {
            terrainUrbain.setQuartier(QuartierMapper.mapToEntity(dto.getQuartier()));
        }  

        // Convert AutorisationDto to Autorisation
        if (dto.getAuthorization() != null) {
            terrainUrbain.setAuthorization(AuthorizationMapper.mapToEntity(dto.getAuthorization()));
        }        

        return terrainUrbain;
    }
}