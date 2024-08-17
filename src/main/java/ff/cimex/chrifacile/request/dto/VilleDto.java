package ff.cimex.chrifacile.request.dto;

import lombok.Data;

import java.util.List;

@Data
public class VilleDto {

    private Long idVille;
    private String nomVille;
    List<QuartierDto> quartiers;
}
