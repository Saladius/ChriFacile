package ff.cimex.chrifacile.repository;

import ff.cimex.chrifacile.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByGoogleToken(String googleToken);
    Optional<UserEntity> findByFacebookToken(String facebookToken);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
