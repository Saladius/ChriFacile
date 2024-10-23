package ff.cimex.chrifacile.service;

import ff.cimex.chrifacile.entity.Abonnement;
import ff.cimex.chrifacile.entity.AchatAbonnement;
import ff.cimex.chrifacile.entity.Vendeur;
import ff.cimex.chrifacile.request.dto.AbonnementDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AbonnementService {
    public boolean isThereAnActifAbonnement(List<AchatAbonnement> abonnements);

    public Abonnement getOrCreateAbonnement(AbonnementDto abonnement);

    AchatAbonnement subscribNewAbonnement(Abonnement abonnement, Vendeur vendeur);
}
