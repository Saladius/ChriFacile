package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.entity.Abonnement;
import ff.cimex.chrifacile.request.dto.AbonnementDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AbonnementMapper {

    public Abonnement mapToEntity(AbonnementDto abonnementDto) {
        Abonnement abonnement = new Abonnement();
        abonnement.setNomAbonnement(abonnementDto.getNomAbonnement());
        abonnement.setPrix(abonnementDto.getPrix());
        abonnement.setNbrJour(abonnementDto.getNbrJour());
        return abonnement;
    }

    public AbonnementDto mapToDto(Abonnement abonnement) {
        AbonnementDto abonnementDto = new AbonnementDto();
        abonnementDto.setNomAbonnement(abonnement.getNomAbonnement());
        abonnementDto.setPrix(abonnement.getPrix());
        abonnementDto.setNbrJour(abonnement.getNbrJour());
        return abonnementDto;
    }
}
