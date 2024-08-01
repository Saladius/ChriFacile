package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Autorisation {

    @Id
    private String nameAutorisation;

    @OneToMany(mappedBy = "autorisation")
    private List<TerrainUrbain> terrainUrbains = new ArrayList<>();

}
