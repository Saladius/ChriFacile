package ff.cimex.chrifacile.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Authorization extends AbstractAudit {

    @Id
    private String nameAuthorization;

    @OneToMany(mappedBy = "authorization")
    private List<TerrainUrbain> terrainUrbains = new ArrayList<>();

}
