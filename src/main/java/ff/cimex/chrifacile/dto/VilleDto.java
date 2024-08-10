package ff.cimex.chrifacile.dto;

import ff.cimex.chrifacile.entity.Quartier;
import lombok.Data;

import java.util.List;

@Data
public class VilleDto {

    private Long idVille;
    private String nomVille;
    List<QuartierDto> quartiers;
}
