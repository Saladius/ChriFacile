package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.Appart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppartRepository extends JpaRepository<Appart, Long> {
}
