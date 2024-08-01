package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendeurRepository extends JpaRepository<Vendeur, Long> {
}
