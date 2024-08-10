package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.Annonce;
import ff.cimex.chrifacile.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    List<Annonce> findByTypeAndCreatedAtAfterAndVilleNomVille(Type type, LocalDateTime date, String villeName);
}
