package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVille;


    private String nomVille;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="idVille")
    private List<Quartier> quartiers = new ArrayList<>();
}
