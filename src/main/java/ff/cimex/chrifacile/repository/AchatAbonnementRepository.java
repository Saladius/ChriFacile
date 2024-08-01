package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.AchatAbonnement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchatAbonnementRepository extends CrudRepository<AchatAbonnement, Long> {
}
