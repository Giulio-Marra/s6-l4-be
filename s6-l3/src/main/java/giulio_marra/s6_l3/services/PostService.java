package giulio_marra.s6_l3.services;

import giulio_marra.s6_l3.entities.Autore;
import giulio_marra.s6_l3.entities.Post;
import giulio_marra.s6_l3.payloads.NewBlogPostPayload;
import giulio_marra.s6_l3.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private AutoreService autoreService;

    public Page<Post> getPosts(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return postRepo.findAll(pageable);
    }

    public Post savePost(Post post) {
        if (postRepo.existsByTitolo(post.getTitolo())) {
            throw new RuntimeException();
        }
        return postRepo.save(post);
    }

    public Post getById(UUID uuid) {
        return postRepo.findById(uuid).orElseThrow();
    }

    public void findByIdAndDelete(UUID uuid) {
        Post found = getById(uuid);
        postRepo.delete(found);
    }

    public Post findByIdAndUpdate(UUID uuid, NewBlogPostPayload body) {
        Post found = getById(uuid);

        found.setCategorie(body.getCategorie());
        found.setTempo_di_lettura(body.getTempo_di_lettura());
        found.setTitolo(body.getTitolo());
        found.setContenuto(body.getContenuto());

        if (found.getAutore().getUuid() != body.getUuid()) {
            Autore nuovoAutore = autoreService.getAutore(body.getUuid());
            found.setAutore(nuovoAutore);
        }

        return postRepo.save(found);
    }
}
