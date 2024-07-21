package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnonce;

    @ManyToOne
    @JoinColumn(name = "name_type")
    private Type type;

    private Double superficie;

    private Long prixMin;

    private Long prixMax;


}
