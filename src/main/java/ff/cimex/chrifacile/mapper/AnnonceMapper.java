package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.request.dto.AnnonceDto;
import ff.cimex.chrifacile.entity.Annonce;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class AnnonceMapper {

    // Convert Entity to DTO
    public static AnnonceDto mapToDto(Annonce annonce) {
        AnnonceDto dto = new AnnonceDto();

        dto.setType(annonce.getType());
        dto.setSuperficieMax(annonce.getSuperficieMax());
        dto.setSuperficieMin(annonce.getSuperficieMin());
        dto.setUnites(annonce.getUnites());
        dto.setPrixMax(annonce.getPrixMax());
        dto.setPrixMin(annonce.getPrixMin());
        dto.setVille(annonce.getVille());
        dto.setNomAcheteur(annonce.getNomAcheteur());
        dto.setNumTelephone(annonce.getNumTelephone());
        dto.setDescription(annonce.getDescription());

        if(Objects.nonNull(annonce.getAppart())) {
            dto.setAppartDto(AppartMapper.mapToDto(annonce.getAppart()));
        }
        if(Objects.nonNull(annonce.getTerrainAgricole())) {
            dto.setTerrainAgricoleDto(TerrainAgricoleMapper.mapToDto(annonce.getTerrainAgricole()));
        }
        if(Objects.nonNull(annonce.getTerrainUrbain())) {
            dto.setTerrainUrbainDto(TerrainUrbainMapper.mapToDto(annonce.getTerrainUrbain()));
        }

        return dto;
    }

    // Convert DTO to Entity
    public static Annonce mapToEntity(AnnonceDto dto) {
        Annonce annonce = new Annonce();

        annonce.setType(dto.getType());
        annonce.setSuperficieMax(dto.getSuperficieMax());
        annonce.setSuperficieMin(dto.getSuperficieMin());
        annonce.setUnites(dto.getUnites());
        annonce.setPrixMax(dto.getPrixMax());
        annonce.setPrixMin(dto.getPrixMin());
        annonce.setNomAcheteur(dto.getNomAcheteur());
        annonce.setNumTelephone(dto.getNumTelephone());
        annonce.setDescription(dto.getDescription());

        annonce.setVille(dto.getVille());
        if(Objects.nonNull(dto.getAppartDto())) {
            annonce.setAppart(AppartMapper.mapToEntity(dto.getAppartDto()));
        }
        if(Objects.nonNull(dto.getTerrainAgricoleDto())) {
            annonce.setTerrainAgricole(TerrainAgricoleMapper.mapToEntity(dto.getTerrainAgricoleDto()));
        }
        if(Objects.nonNull(dto.getTerrainUrbainDto())) {
            annonce.setTerrainUrbain(TerrainUrbainMapper.mapToEntity(dto.getTerrainUrbainDto()));
        }

        return annonce;
    }
}

