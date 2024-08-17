package ff.cimex.chrifacile.service.impl;


import ff.cimex.chrifacile.entity.*;
import ff.cimex.chrifacile.mapper.AnnonceMapper;
import ff.cimex.chrifacile.repository.AnnonceRepository;
import ff.cimex.chrifacile.repository.VilleRepository;
import ff.cimex.chrifacile.request.dto.*;
import ff.cimex.chrifacile.service.AnnonceService;
import ff.cimex.chrifacile.util.CompareUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnnonceServiceImpl implements AnnonceService {
    private AnnonceRepository annonceRepository;
    private VilleRepository villeRepository;

    @Override
    public Annonce addAnnonce(AnnonceDto annonceDto) {
        Annonce annonce = remplirAnnonce(annonceDto);

        return annonceRepository.save(annonce);

    }

    @Override
    public List<AnnonceDto> getAllAnnonces() {
        return List.of();
    }

    @Override
    public List<AnnonceDto> getAnnoncesByFilter(FilterDto filter) {
        LocalDateTime dateOfLastVisibleAnnonces = LocalDate.now().minusDays(15).atStartOfDay();
        List<Annonce> annonces = annonceRepository.findByTypeAndCreatedAtAfterAndVilleNomVille(filter.getType(), dateOfLastVisibleAnnonces, filter.getVilleDto().getNomVille());
        return annonces.stream().filter(annonce -> isFilteredBySuperficie(annonce, filter.getSuperficie()))
                .filter(annonce -> isFilteredByPrix(annonce, filter.getPrix()))
                .filter(annonce -> isFilteredByPrerequisiteofType(annonce, filter))
                .map(AnnonceMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnonceDto getAnnonceById(Long id) {
        return AnnonceMapper.mapToDto(annonceRepository.getReferenceById(id));
    }

    private boolean isFilteredByPrerequisiteofType(Annonce annonce, FilterDto filterDto) {
        switch (filterDto.getType()) {
            case TYPE_VILLA -> {
                return isFilteredVilla(annonce);
            }
            case TYPE_APPARTEMENT -> {
                return isFilteredAppart(annonce.getAppart(), filterDto.getAppartDto());
            }
            case TYPE_TERRAIN_URBAIN -> {
                return isFilteredTerrainUrbain(annonce.getTerrainUrbain(), filterDto.getTerrainUrbainDto());
            }
            case TYPE_TERRAIN_AGRICOLE -> {
                return isFilteredTerrainAgricole(annonce.getTerrainAgricole(), filterDto.getTerrainAgricoleDto());
            }
            default -> throw new IllegalStateException("Unexpected value: " + filterDto.getType());
        }
    }

    private boolean isFilteredVilla(Annonce annonce) {
        return true;
    }

    private boolean isFilteredAppart(Appart appart, AppartDto appartDto) {

        return isFilteredByNbrChambre(appart, appartDto.getNbrChambreActuel())
                && isFilteredByNbrSalleDeBain(appart, appartDto.getNbrSalleDeBainActuel())
                && isFilteredByEtage(appart, appartDto.getEtageActuel())
                && isFilteredByQuartier(appart.getQuartier(), appartDto.getQuartier());
    }

    private boolean isFilteredTerrainAgricole(TerrainAgricole terrainAgricole, TerrainAgricoleDto terrainAgricoleDto) {
        return CompareUtil.oneIsNull(terrainAgricole, terrainAgricoleDto)
                || (terrainAgricole.isBattie() == terrainAgricoleDto.isBattie()
                && terrainAgricole.isPuit() == terrainAgricoleDto.isPuit());
    }

    private boolean isFilteredTerrainUrbain(TerrainUrbain terrainUrbain, TerrainUrbainDto terrainUrbainDto) {

        return CompareUtil.oneIsNull(terrainUrbain, terrainUrbainDto) ||
                (isFilteredByAuthorization(terrainUrbain.getAuthorization(), terrainUrbainDto.getAuthorization())
                        && isFilteredByQuartier(terrainUrbain.getQuartier(), terrainUrbainDto.getQuartier()));
    }

    private boolean isFilteredBySuperficie(Annonce annonce, Double superficie) {
        return superficie == null
                || ((annonce.getSuperficieMin() == null || annonce.getSuperficieMin() <= superficie)
                && (annonce.getSuperficieMax() == null || annonce.getSuperficieMax() >= superficie));
    }

    private boolean isFilteredByPrix(Annonce annonce, Long prix) {
        return prix == null
                || ((annonce.getPrixMin() == null || annonce.getPrixMin() <= prix)
                && (annonce.getPrixMax() == null || annonce.getPrixMax() >= prix));
    }

    private boolean isFilteredByNbrChambre(Appart appart, Integer nbrChambre) {
        return nbrChambre == null
                || ((appart.getNbrChambreMin() == null || appart.getNbrChambreMin() <= nbrChambre)
                && (appart.getNbrChambreMax() == null || appart.getNbrChambreMax() >= nbrChambre));
    }

    private boolean isFilteredByNbrSalleDeBain(Appart appart, Integer nbrSalleDeBain) {
        return nbrSalleDeBain == null
                || ((appart.getNbrSalleDeBainMin() == null || appart.getNbrSalleDeBainMin() <= nbrSalleDeBain)
                && (appart.getNbrSalleDeBainMax() == null || appart.getNbrSalleDeBainMax() >= nbrSalleDeBain));
    }

    private boolean isFilteredByEtage(Appart appart, Integer etageActuel) {
        return CompareUtil.oneIsNull(etageActuel, appart.getEtageMax()) || appart.getEtageMax() >= etageActuel;
    }

    private boolean isFilteredByQuartier(Quartier quartier, QuartierDto quartierDto) {
        return CompareUtil.oneIsNull(quartier, quartierDto) || CompareUtil.oneIsNull(quartier.getNomQuartier(), quartierDto.getNomQuartier())
                || quartier.getNomQuartier().equals(quartierDto.getNomQuartier());
    }

    private boolean isFilteredByAuthorization(Authorization authorization, AuthorizationDto authorizationDto) {
        return CompareUtil.oneIsNull(authorization, authorizationDto, authorization.getNameAutorisation(), authorizationDto.getNameAutorisation())
                || authorization.getNameAutorisation().equals(authorizationDto.getNameAutorisation());
    }


    private Annonce remplirAnnonce(AnnonceDto annonceDto) {
        return AnnonceMapper.mapToEntity(annonceDto);
    }
}
