package ff.cimex.chrifacile.request.dto;

import lombok.Data;

@Data
public class VillaDto {

    private Integer nbrChambreMax;
    private Integer nbrChambreMin;
    private Integer nbrChambreActuel;

    private Integer nbrSalleDeBainMax;
    private Integer nbrSalleDeBainMin;
    private Integer nbrSalleDeBainActuel;

    private Integer etageMax;
    private Integer etageActuel;
    private Integer etageMin;

    private boolean isGarage;
}
