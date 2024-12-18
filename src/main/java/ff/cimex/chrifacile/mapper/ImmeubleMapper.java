package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.entity.Immeuble;
import ff.cimex.chrifacile.request.dto.ImmeubleDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImmeubleMapper {
    public static ImmeubleDto mapToDto(Immeuble immeuble) {
        ImmeubleDto dto = new ImmeubleDto();
        copyPropertiesFromImmeuble(dto, immeuble);
        return dto;
    }
    // Convert DTO to Entity
    public static Immeuble mapToEntity(ImmeubleDto dto) {
        Immeuble immeuble = new Immeuble();
        copyPropertiesToImmeuble(immeuble, dto);
        return immeuble;
    }

    private static void copyPropertiesFromImmeuble(ImmeubleDto dto, Immeuble immeuble) {
        dto.setNbrAppartMax(immeuble.getNbrAppartMax());
        dto.setNbrAppartMin(immeuble.getNbrAppartMin());
        dto.setEtageMax(immeuble.getEtageMax());
        dto.setEtageMin(immeuble.getEtageMin());
        dto.setGarage(immeuble.isGarage());
    }

    private static void copyPropertiesToImmeuble(Immeuble immeuble, ImmeubleDto dto) {
        immeuble.setNbrAppartMax(dto.getNbrAppartMax());
        immeuble.setNbrAppartMin(dto.getNbrAppartMin());
        immeuble.setEtageMax(dto.getEtageMax());
        immeuble.setEtageMin(dto.getEtageMin());
        immeuble.setGarage(dto.isGarage());
    }
}
