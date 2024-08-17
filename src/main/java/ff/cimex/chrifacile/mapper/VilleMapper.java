package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.request.dto.VilleDto;
import ff.cimex.chrifacile.entity.Ville;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class VilleMapper {

    // Convert Entity to DTO
    public static VilleDto mapToDto(Ville ville) {
        VilleDto dto = new VilleDto();

        dto.setIdVille(ville.getIdVille());
        dto.setNomVille(ville.getNomVille());

        if (ville.getQuartiers() != null) {
            dto.setQuartiers(ville.getQuartiers().stream()
                    .map(QuartierMapper::mapToDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    // Convert DTO to Entity
    public static Ville mapToEntity(VilleDto dto) {
        Ville ville = new Ville();

        ville.setIdVille(dto.getIdVille());
        ville.setNomVille(dto.getNomVille());

        if (dto.getQuartiers() != null) {
            ville.setQuartiers(dto.getQuartiers().stream()
                    .map(QuartierMapper::mapToEntity)
                    .collect(Collectors.toList()));
        }

        return ville;
    }
}
