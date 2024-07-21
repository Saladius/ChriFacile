package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
}
