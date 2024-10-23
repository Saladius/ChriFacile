package ff.cimex.chrifacile.request.dto;

import lombok.Data;

@Data
public class AbonnementDto {
    private String nomAbonnement;
    private int nbrJour;
    private double prix;
}
