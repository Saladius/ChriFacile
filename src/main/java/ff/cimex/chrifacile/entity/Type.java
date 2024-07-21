package ff.cimex.chrifacile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Type {
    @Id
    private String nameType;

    @OneToMany(mappedBy = "type")
    private List<Annonce> annonces = new ArrayList<>();
}
