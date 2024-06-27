package giulio_marra.s6_l3.repositories;

import giulio_marra.s6_l3.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoreRepo extends JpaRepository<Autore, UUID> {

    boolean existsByEmail(String email);
}
