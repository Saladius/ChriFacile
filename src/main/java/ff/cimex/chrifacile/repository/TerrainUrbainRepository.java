package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.TerrainUrbain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrainUrbainRepository extends CrudRepository<TerrainUrbain, Long> {
}
