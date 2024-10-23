package ff.cimex.chrifacile.service.impl;

import ff.cimex.chrifacile.entity.Abonnement;
import ff.cimex.chrifacile.entity.AchatAbonnement;
import ff.cimex.chrifacile.entity.Vendeur;
import ff.cimex.chrifacile.mapper.AbonnementMapper;
import ff.cimex.chrifacile.repository.AbonnementRepository;
import ff.cimex.chrifacile.repository.AchatAbonnementRepository;
import ff.cimex.chrifacile.request.dto.AbonnementDto;
import ff.cimex.chrifacile.service.AbonnementService;
import ff.cimex.chrifacile.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbonnementServiceImpl implements AbonnementService {

    private final AbonnementRepository abonnementRepository;

    private final AchatAbonnementRepository achatAbonnementRepository;

    @Override
    public boolean isThereAnActifAbonnement(List<AchatAbonnement> abonnements) {
        Date today = new Date();
        return abonnements.stream()
                .anyMatch(abonnement -> abonnement.getDateDebut().before(today) && abonnement.getDateFin().after(today));
    }

    @Override
    public Abonnement getOrCreateAbonnement(AbonnementDto abonnementDto) {

        Optional<Abonnement> optionalAbonnement = abonnementRepository.findAbonnementByNomAbonnementAndNbrJourAndPrix(abonnementDto.getNomAbonnement(), abonnementDto.getNbrJour(), abonnementDto.getPrix());
        if(optionalAbonnement.isPresent()){
            return optionalAbonnement.get();
        }
        else{
        Abonnement abonnement = AbonnementMapper.mapToEntity(abonnementDto);
        return abonnementRepository.save(abonnement);
        }
    }

    @Override
    public AchatAbonnement subscribNewAbonnement(Abonnement abonnement, Vendeur vendeur){
        AchatAbonnement achatAbonnement = new AchatAbonnement();
        achatAbonnement.setAbonnement(abonnement);
        achatAbonnement.setVendeur(vendeur);
        achatAbonnement.setDateDebut(new Date());
        achatAbonnement.setDateFin(DateUtil.ajouterJours(abonnement.getNbrJour()));
        return achatAbonnementRepository.save(achatAbonnement);
    }
}
