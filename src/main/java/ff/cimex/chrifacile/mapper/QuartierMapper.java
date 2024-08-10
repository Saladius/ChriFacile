package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.dto.QuartierDto;
import ff.cimex.chrifacile.entity.Quartier;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuartierMapper {

    // Convert Entity to DTO
    public static QuartierDto mapToDto(Quartier quartier) {
        QuartierDto dto = new QuartierDto();
        dto.setIdQuartier(quartier.getIdQuartier());
        dto.setNomQuartier(quartier.getNomQuartier());
        return dto;
    }

    // Convert DTO to Entity
    public static Quartier mapToEntity(QuartierDto dto) {
        Quartier quartier = new Quartier();
        quartier.setIdQuartier(dto.getIdQuartier());
        quartier.setNomQuartier(dto.getNomQuartier());
        return quartier;
    }
}