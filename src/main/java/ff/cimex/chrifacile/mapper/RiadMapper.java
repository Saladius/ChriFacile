package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.entity.Riad;
import ff.cimex.chrifacile.request.dto.RiadDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RiadMapper {
    public static RiadDto mapToDto(Riad riad) {
        RiadDto dto = new RiadDto();
        copyPropertiesFromRiad(dto, riad);
        return dto;
    }
    // Convert DTO to Entity
    public static Riad mapToEntity(RiadDto dto) {
        Riad riad = new Riad();
        copyPropertiesToRiad(riad, dto);
        return riad;
    }

    private static void copyPropertiesFromRiad(RiadDto dto, Riad riad) {
        dto.setNbrChambreMax(riad.getNbrChambreMax());
        dto.setNbrChambreMin(riad.getNbrChambreMin());
        dto.setNbrSalleDeBainMax(riad.getNbrSalleDeBainMax());
        dto.setNbrSalleDeBainMin(riad.getNbrSalleDeBainMin());
        dto.setEtageMax(riad.getEtageMax());
        dto.setEtageMin(riad.getEtageMin());
    }

    private static void copyPropertiesToRiad(Riad riad, RiadDto dto) {
        riad.setNbrChambreMax(dto.getNbrChambreMax());
        riad.setNbrChambreMin(dto.getNbrChambreMin());
        riad.setNbrSalleDeBainMax(dto.getNbrSalleDeBainMax());
        riad.setNbrSalleDeBainMin(dto.getNbrSalleDeBainMin());
        riad.setEtageMax(dto.getEtageMax());
        riad.setEtageMin(dto.getEtageMin());
    }
}
