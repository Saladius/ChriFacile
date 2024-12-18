package ff.cimex.chrifacile.request.dto;

import ff.cimex.chrifacile.enums.Type;
import lombok.Data;

@Data
public class BienImmobilierDto {
    private String quartier;
    private Type typeBien;

    private AppartDto appart;
    private VillaDto villa;
    private RiadDto riad;
    private ImmeubleDto immeuble;
}
