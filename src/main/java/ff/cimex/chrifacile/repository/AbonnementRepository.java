package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    public Optional<Abonnement> findAbonnementByNomAbonnementAndNbrJourAndPrix(String nomAbonnement, int nbrJour, double prix);
}
