package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.TerrainAgricole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrainAgricoleRepository extends CrudRepository<TerrainAgricole, Long> {
}
