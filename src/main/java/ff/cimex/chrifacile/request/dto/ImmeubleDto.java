package ff.cimex.chrifacile.request.dto;

import ff.cimex.chrifacile.enums.TypeImmeuble;
import lombok.Data;

@Data
public class ImmeubleDto {

    private Integer etageMax;
    private Integer etageActuel;
    private Integer etageMin;

    private Integer nbrAppartMax;
    private Integer nbrAppartActuel;
    private Integer nbrAppartMin;

    private boolean isGarage;

    private TypeImmeuble typeImmeuble;
}
