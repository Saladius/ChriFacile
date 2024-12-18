package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.entity.Villa;
import ff.cimex.chrifacile.request.dto.VillaDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VillaMapper {

    public static VillaDto mapToDto(Villa villa) {
        VillaDto dto = new VillaDto();
        copyPropertiesFromVilla(dto, villa);
        return dto;
    }
    // Convert DTO to Entity
    public static Villa mapToEntity(VillaDto dto) {
        Villa villa = new Villa();
        copyPropertiesToVilla(villa, dto);
        return villa;
    }

    private static void copyPropertiesFromVilla(VillaDto dto, Villa villa) {
        dto.setNbrChambreMax(villa.getNbrChambreMax());
        dto.setNbrChambreMin(villa.getNbrChambreMin());
        dto.setNbrSalleDeBainMax(villa.getNbrSalleDeBainMax());
        dto.setNbrSalleDeBainMin(villa.getNbrSalleDeBainMin());
        dto.setEtageMax(villa.getEtageMax());
        dto.setEtageMin(villa.getEtageMin());
        dto.setGarage(villa.isGarage());
    }

    private static void copyPropertiesToVilla(Villa villa, VillaDto dto) {
        villa.setNbrChambreMax(dto.getNbrChambreMax());
        villa.setNbrChambreMin(dto.getNbrChambreMin());
        villa.setNbrSalleDeBainMax(dto.getNbrSalleDeBainMax());
        villa.setNbrSalleDeBainMin(dto.getNbrSalleDeBainMin());
        villa.setEtageMax(dto.getEtageMax());
        villa.setEtageMin(dto.getEtageMin());
        villa.setGarage(dto.isGarage());
    }
}
