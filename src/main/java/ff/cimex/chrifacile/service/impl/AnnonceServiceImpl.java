package ff.cimex.chrifacile.service.impl;


import ff.cimex.chrifacile.entity.*;
import ff.cimex.chrifacile.enums.Type;
import ff.cimex.chrifacile.enums.TypeImmeuble;
import ff.cimex.chrifacile.mapper.AnnonceMapper;
import ff.cimex.chrifacile.repository.AnnonceRepository;
import ff.cimex.chrifacile.request.dto.*;
import ff.cimex.chrifacile.service.AnnonceService;
import ff.cimex.chrifacile.util.CompareUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

        List<Annonce> annonces = getAllAnnoncesByFilter(filter);
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

    private List<Annonce> getAllAnnoncesByFilter(FilterDto filter) {
        LocalDateTime dateOfLastVisibleAnnonces = LocalDate.now().minusDays(15).atStartOfDay();
        if(Objects.nonNull(filter.getType())){
            return annonceRepository.findAllByTypeAndCreatedAtAfterAndVille(filter.getType(), dateOfLastVisibleAnnonces, filter.getVille());
        }
        return annonceRepository.findAllByCreatedAtAfterAndVille(dateOfLastVisibleAnnonces, filter.getVille());
    }
    private boolean isFilteredByPrerequisiteofType(Annonce annonce, FilterDto filterDto) {
        if(CompareUtil.oneIsNull(filterDto.getType())){
            return true;
        }
        switch (filterDto.getType()) {

            case TYPE_BIEN_IMMOBILIER -> {
                return isFilteredBienImmobilier(annonce.getBienImmobilier(), filterDto.getBienImmobilierDto());
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

    private boolean isFilteredBienImmobilier(BienImmobilier bienImmobilier, BienImmobilierDto bienImmobilierDto) {
        if(isFilteredByQuartier(bienImmobilier.getQuartier(), bienImmobilierDto.getQuartier())){
            switch (bienImmobilierDto.getTypeBien()){
                case TYPE_APPARTEMENT -> {
                    return isFilteredAppart(bienImmobilier.getAppart(), bienImmobilierDto.getAppart());
                }
                case TYPE_VILLA -> {
                    return isFilteredVilla(bienImmobilier.getVilla(), bienImmobilierDto.getVilla());
                }
                case TYPE_RIAD -> {
                    return isFilteredRiad(bienImmobilier.getRiad(), bienImmobilierDto.getRiad());
                }
                case TYPE_IMMEUBLE -> {
                    return isFilteredImmeuble(bienImmobilier.getImmeuble(), bienImmobilierDto.getImmeuble());
                }
            }
        }
        return false;
    }

    private boolean isFilteredAppart(Appart appart, AppartDto appartDto) {

        return isFilteredByNbrChambreAppart(appart, appartDto.getNbrChambreActuel())
                && isFilteredByNbrSalleDeBainAppart(appart, appartDto.getNbrSalleDeBainActuel())
                && isFilteredByEtageAppart(appart, appartDto.getEtageActuel());
    }

    private boolean isFilteredVilla(Villa villa, VillaDto villaDto) {
        return isFilteredByNbrChambreVilla(villa, villaDto.getNbrChambreActuel())
                && isFilteredByNbrSalleDeBainVilla(villa, villaDto.getNbrSalleDeBainActuel())
                && isFilteredByEtageVilla(villa, villaDto.getEtageActuel())
                && (villa.isGarage() || !villaDto.isGarage());
    }

    private boolean isFilteredRiad(Riad riad, RiadDto riadDto) {
        return isFilteredByNbrChambreRiad(riad, riadDto.getNbrChambreActuel())
                && isFilteredByNbrSalleDeBainRiad(riad, riadDto.getNbrSalleDeBainActuel())
                && isFilteredByEtageRiad(riad, riadDto.getEtageActuel());
    }

    private boolean isFilteredImmeuble(Immeuble immeuble, ImmeubleDto immeubleDto) {
        return CompareUtil.oneIsNull(immeuble, immeubleDto) ||
                (isFilteredByNbrAppartImmeuble(immeuble, immeubleDto.getNbrAppartActuel())
                && isFilteredByEtageImmeuble(immeuble, immeubleDto.getEtageActuel())
                && (immeuble.isGarage() || !immeubleDto.isGarage())
                && isFilteredTypeImmeuble(immeuble.getTypeImmeuble(),immeubleDto.getTypeImmeuble()));
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

    private boolean isFilteredByNbrChambreAppart(Appart appart, Integer nbrChambre) {
        return nbrChambre == null || nbrChambre == 0
                || ((appart.getNbrChambreMin() == null || appart.getNbrChambreMin() == 0 || appart.getNbrChambreMin() <= nbrChambre)
                && (appart.getNbrChambreMax() == null || appart.getNbrChambreMax() ==0 || appart.getNbrChambreMax() >= nbrChambre));
    }

    private boolean isFilteredByNbrSalleDeBainAppart(Appart appart, Integer nbrSalleDeBain) {
        return nbrSalleDeBain == null || nbrSalleDeBain==0
                || ((appart.getNbrSalleDeBainMin() == null || appart.getNbrSalleDeBainMin() == 0 || appart.getNbrSalleDeBainMin() <= nbrSalleDeBain)
                && (appart.getNbrSalleDeBainMax() == null || appart.getNbrSalleDeBainMax() == 0 || appart.getNbrSalleDeBainMax() >= nbrSalleDeBain));
    }

    private boolean isFilteredByEtageAppart(Appart appart, Integer etageActuel) {
        return CompareUtil.oneIsNull(etageActuel, appart.getEtageMax(),appart.getEtageMin()) || CompareUtil.oneIntegerIsZero(etageActuel,appart.getEtageMax()) ||  (appart.getEtageMax() >= etageActuel && appart.getEtageMin() <= etageActuel);
    }

    private boolean isFilteredByNbrChambreVilla(Villa villa, Integer nbrChambre) {
        return nbrChambre == null || nbrChambre == 0
                || ((villa.getNbrChambreMin() == null || villa.getNbrChambreMin() == 0 || villa.getNbrChambreMin() <= nbrChambre)
                && (villa.getNbrChambreMax() == null || villa.getNbrChambreMax() ==0 || villa.getNbrChambreMax() >= nbrChambre));
    }

    private boolean isFilteredByNbrSalleDeBainVilla(Villa villa, Integer nbrSalleDeBain) {
        return nbrSalleDeBain == null || nbrSalleDeBain==0
                || ((villa.getNbrSalleDeBainMin() == null || villa.getNbrSalleDeBainMin() == 0 || villa.getNbrSalleDeBainMin() <= nbrSalleDeBain)
                && (villa.getNbrSalleDeBainMax() == null || villa.getNbrSalleDeBainMax() == 0 || villa.getNbrSalleDeBainMax() >= nbrSalleDeBain));
    }

    private boolean isFilteredByEtageVilla(Villa villa, Integer etageActuel) {
        return CompareUtil.oneIsNull(etageActuel, villa.getEtageMax(),villa.getEtageMin()) || CompareUtil.oneIntegerIsZero(etageActuel,villa.getEtageMax()) ||  (villa.getEtageMax() >= etageActuel && villa.getEtageMin() <= etageActuel);
    }

    private boolean isFilteredByNbrChambreRiad(Riad riad, Integer nbrChambre) {
        return nbrChambre == null || nbrChambre == 0
                || ((riad.getNbrChambreMin() == null || riad.getNbrChambreMin() == 0 || riad.getNbrChambreMin() <= nbrChambre)
                && (riad.getNbrChambreMax() == null || riad.getNbrChambreMax() ==0 || riad.getNbrChambreMax() >= nbrChambre));
    }

    private boolean isFilteredByNbrSalleDeBainRiad(Riad riad, Integer nbrSalleDeBain) {
        return nbrSalleDeBain == null || nbrSalleDeBain==0
                || ((riad.getNbrSalleDeBainMin() == null || riad.getNbrSalleDeBainMin() == 0 || riad.getNbrSalleDeBainMin() <= nbrSalleDeBain)
                && (riad.getNbrSalleDeBainMax() == null || riad.getNbrSalleDeBainMax() == 0 || riad.getNbrSalleDeBainMax() >= nbrSalleDeBain));
    }

    private boolean isFilteredByEtageRiad(Riad riad, Integer etageActuel) {
        return CompareUtil.oneIsNull(etageActuel, riad.getEtageMax(),riad.getEtageMin()) || CompareUtil.oneIntegerIsZero(etageActuel,riad.getEtageMax()) ||  (riad.getEtageMax() >= etageActuel && riad.getEtageMin() <= etageActuel);
    }

    private boolean isFilteredByEtageImmeuble(Immeuble immeuble, Integer etageActuel) {
        return CompareUtil.oneIsNull(etageActuel, immeuble.getEtageMax(),immeuble.getEtageMin()) || CompareUtil.oneIntegerIsZero(etageActuel,immeuble.getEtageMax()) ||  (immeuble.getEtageMax() >= etageActuel && immeuble.getEtageMin() <= etageActuel);
    }

    private boolean isFilteredByNbrAppartImmeuble(Immeuble immeuble, Integer nbrAppartActuel) {
        return nbrAppartActuel == null || nbrAppartActuel == 0
                || ((immeuble.getNbrAppartMin() == null || immeuble.getNbrAppartMin() == 0 || immeuble.getNbrAppartMin() <= nbrAppartActuel)
                && (immeuble.getNbrAppartMax() == null || immeuble.getNbrAppartMax() ==0 || immeuble.getNbrAppartMax() >= nbrAppartActuel));
    }

    private boolean isFilteredByQuartier(String quartier, String quartierDto) {
        return CompareUtil.oneIsNull(quartier, quartierDto) || CompareUtil.oneStringIsEmpty(quartier,quartierDto) || quartier.contains(quartierDto);
    }

    private boolean isFilteredByAuthorization(String authorization, String authorizationDto) {
        return CompareUtil.oneIsNull(authorization, authorizationDto)
                || authorization.equals(authorizationDto);
    }

    private boolean isFilteredTypeImmeuble(TypeImmeuble typeImmeuble, TypeImmeuble typeImmeubleDto) {
        return CompareUtil.oneIsNull(typeImmeuble, typeImmeubleDto) || typeImmeuble.equals(typeImmeubleDto);
    }


    private Annonce remplirAnnonce(AnnonceDto annonceDto) {
        Annonce annonce =AnnonceMapper.mapToEntity(annonceDto);
        if(annonceDto.getType().equals(Type.TYPE_TERRAIN_URBAIN)){

        }
        return annonce;
    }
}
