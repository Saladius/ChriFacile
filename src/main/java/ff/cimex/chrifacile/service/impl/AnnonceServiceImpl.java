package ff.cimex.chrifacile.service.impl;


import ff.cimex.chrifacile.entity.*;
import ff.cimex.chrifacile.enums.Type;
import ff.cimex.chrifacile.mapper.AnnonceMapper;
import ff.cimex.chrifacile.repository.AnnonceRepository;
import ff.cimex.chrifacile.request.dto.*;
import ff.cimex.chrifacile.service.AnnonceService;
import ff.cimex.chrifacile.util.CompareUtil;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnnonceServiceImpl implements AnnonceService {
    private AnnonceRepository annonceRepository;

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
        List<Annonce> annonces = annonceRepository.findAllByTypeAndCreatedAtAfterAndVille(filter.getType(), dateOfLastVisibleAnnonces, filter.getVille());
        return annonces.stream().filter(annonce -> isFilteredBySuperficie(annonce, filter.getSuperficie()))
                .filter(annonce -> isFilteredByPrix(annonce, filter.getPrixMin(), filter.getPrixMax()))
                .filter(annonce -> isFilteredByPrerequisiteofType(annonce, filter))
                .map(AnnonceMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnonceDto getAnnonceById(Long id) {
        return AnnonceMapper.mapToDto(annonceRepository.getReferenceById(id));
    }

    @Override
    public Page<AnnonceDto> getAllAnnonces(Pageable pageable) {
        LocalDateTime dateLimit = LocalDateTime.now().minusDays(15);
        Page<Annonce> annoncesPage = annonceRepository.findAllByCreatedAtAfter(dateLimit, pageable);

        // Map the Page of Annonce entities to a Page of AnnonceDto
        return annoncesPage.map(AnnonceMapper::mapToDto);
    }

    private boolean isFilteredByPrerequisiteofType(Annonce annonce, FilterDto filterDto) {
        switch (filterDto.getType()) {
           /* case TYPE_VILLA -> {
                return isFilteredVilla(annonce);
            }*/
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

   /* private boolean isFilteredVilla(Annonce annonce) {
        return true;
    }*/

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
        return superficie == null || superficie == 0.0
                || ((annonce.getSuperficieMin() == null || annonce.getSuperficieMin() <= superficie)
                && (annonce.getSuperficieMax() == null || annonce.getSuperficieMax() >= superficie));
    }

    private boolean isFilteredByPrix(Annonce annonce, Long prixMin, Long prixMax) {
        Long annoncePrixMin = annonce.getPrixMin();
        Long annoncePrixMax = annonce.getPrixMax();

        // Check if there is any overlap between the two intervals
        if (annoncePrixMin == null || annoncePrixMax == null || prixMin == null || prixMax == null) {
            return true; // If any of the prices are null, we cannot filter
        }

        // Check for overlap
        return (prixMin == 0 || annoncePrixMax==0 || annoncePrixMax >= prixMin) && (prixMax==0 || annoncePrixMin==0 || prixMax >= annoncePrixMin);
    }

    private boolean isFilteredByNbrChambre(Appart appart, Integer nbrChambre) {
        return nbrChambre == null || nbrChambre == 0
                || ((appart.getNbrChambreMin() == null || appart.getNbrChambreMin() == 0 || appart.getNbrChambreMin() <= nbrChambre)
                && (appart.getNbrChambreMax() == null || appart.getNbrChambreMax() ==0 || appart.getNbrChambreMax() >= nbrChambre));
    }

    private boolean isFilteredByNbrSalleDeBain(Appart appart, Integer nbrSalleDeBain) {
        return nbrSalleDeBain == null || nbrSalleDeBain==0
                || ((appart.getNbrSalleDeBainMin() == null || appart.getNbrSalleDeBainMin() == 0 || appart.getNbrSalleDeBainMin() <= nbrSalleDeBain)
                && (appart.getNbrSalleDeBainMax() == null || appart.getNbrSalleDeBainMax() == 0 || appart.getNbrSalleDeBainMax() >= nbrSalleDeBain));
    }

    private boolean isFilteredByEtage(Appart appart, Integer etageActuel) {
        return CompareUtil.oneIsNull(etageActuel, appart.getEtageMax(),appart.getEtageMin()) || CompareUtil.oneIntegerIsZero(etageActuel,appart.getEtageMax()) ||  (appart.getEtageMax() >= etageActuel && appart.getEtageMin() <= etageActuel);
    }

    private boolean isFilteredByQuartier(String quartier, String quartierDto) {
        return CompareUtil.oneIsNull(quartier, quartierDto) || CompareUtil.oneStringIsEmpty(quartier,quartierDto) || quartier.contains(quartierDto);
    }

    private boolean isFilteredByAuthorization(String authorization, String authorizationDto) {
        return CompareUtil.oneIsNull(authorization, authorizationDto)
                || authorization.equals(authorizationDto);
    }


    private Annonce remplirAnnonce(AnnonceDto annonceDto) {
        Annonce annonce =AnnonceMapper.mapToEntity(annonceDto);
        if(annonceDto.getType().equals(Type.TYPE_TERRAIN_URBAIN)){

        }
        return annonce;
    }
}
