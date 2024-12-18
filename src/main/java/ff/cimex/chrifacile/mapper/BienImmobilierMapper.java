package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.entity.BienImmobilier;
import ff.cimex.chrifacile.request.dto.BienImmobilierDto;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class BienImmobilierMapper {
    public static BienImmobilierDto mapToDto(BienImmobilier bienImmobilier) {
        BienImmobilierDto dto = new BienImmobilierDto();
        copyPropertiesFromBienImmobilier(dto, bienImmobilier);
        return dto;
    }
    // Convert DTO to Entity
    public static BienImmobilier mapToEntity(BienImmobilierDto dto) {
        BienImmobilier bienImmobilier = new BienImmobilier();
        copyPropertiesToBienImmobilier(bienImmobilier, dto);
        return bienImmobilier;
    }

    private static void copyPropertiesFromBienImmobilier(BienImmobilierDto dto, BienImmobilier bienImmobilier) {
        dto.setQuartier(bienImmobilier.getQuartier());
        dto.setTypeBien(bienImmobilier.getTypeBien());

        if(Objects.nonNull(bienImmobilier.getAppart())){
            dto.setAppart(AppartMapper.mapToDto(bienImmobilier.getAppart()));
        }
        if(Objects.nonNull(bienImmobilier.getVilla())){
            dto.setVilla(VillaMapper.mapToDto(bienImmobilier.getVilla()));
        }
        if(Objects.nonNull(bienImmobilier.getRiad())){
            dto.setRiad(RiadMapper.mapToDto(bienImmobilier.getRiad()));
        }
        if(Objects.nonNull(bienImmobilier.getImmeuble())){
            dto.setImmeuble(ImmeubleMapper.mapToDto(bienImmobilier.getImmeuble()));
        }
    }

    private static void copyPropertiesToBienImmobilier(BienImmobilier bienImmobilier, BienImmobilierDto dto) {
        bienImmobilier.setQuartier(dto.getQuartier());
        bienImmobilier.setTypeBien(dto.getTypeBien());

        if(Objects.nonNull(dto.getAppart())){
            bienImmobilier.setAppart(AppartMapper.mapToEntity(dto.getAppart()));
        }
        if(Objects.nonNull(dto.getVilla())){
            bienImmobilier.setVilla(VillaMapper.mapToEntity(dto.getVilla()));
        }
        if(Objects.nonNull(dto.getRiad())){
            bienImmobilier.setRiad(RiadMapper.mapToEntity(dto.getRiad()));
        }
        if(Objects.nonNull(dto.getImmeuble())){
            bienImmobilier.setImmeuble(ImmeubleMapper.mapToEntity(dto.getImmeuble()));
        }
    }
}
