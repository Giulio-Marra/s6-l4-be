package giulio_marra.s6_l3.repositories;

import giulio_marra.s6_l3.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {

    boolean existsByTitolo(String titolo);

    Page<Post> findByAutoreUuid(Pageable page, UUID uuid);
}
