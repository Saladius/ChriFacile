package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.request.dto.AppartDto;
import ff.cimex.chrifacile.entity.Appart;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppartMapper {
    // Convert Entity to DTO
    public static AppartDto mapToDto(Appart appart) {
        AppartDto dto = new AppartDto();
        copyPropertiesFromAppart(dto, appart);
        return dto;
    }
    // Convert DTO to Entity
    public static Appart mapToEntity(AppartDto dto) {
        Appart appart = new Appart();
        copyPropertiesToAppart(appart, dto);
        return appart;
    }

    private static void copyPropertiesFromAppart(AppartDto dto, Appart appart) {
        dto.setNbrChambreMax(appart.getNbrChambreMax());
        dto.setNbrChambreMin(appart.getNbrChambreMin());
        dto.setNbrSalleDeBainMax(appart.getNbrSalleDeBainMax());
        dto.setNbrSalleDeBainMin(appart.getNbrSalleDeBainMin());
        dto.setEtageMax(appart.getEtageMax());
        dto.setQuartier(appart.getQuartier());
    }

    private static void copyPropertiesToAppart(Appart appart, AppartDto dto) {
        appart.setNbrChambreMax(dto.getNbrChambreMax());
        appart.setNbrChambreMin(dto.getNbrChambreMin());
        appart.setNbrSalleDeBainMax(dto.getNbrSalleDeBainMax());
        appart.setNbrSalleDeBainMin(dto.getNbrSalleDeBainMin());
        appart.setEtageMax(dto.getEtageMax());
        appart.setQuartier(dto.getQuartier());
    }
}