package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Ville extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVille;


    private String nomVille;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="idVille")
    private List<Quartier> quartiers = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="idVille")
    private List<Annonce> annonces = new ArrayList<>();
}
