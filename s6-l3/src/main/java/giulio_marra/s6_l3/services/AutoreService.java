package giulio_marra.s6_l3.services;

import giulio_marra.s6_l3.entities.Autore;
import giulio_marra.s6_l3.exceptions.NotFoundException;
import giulio_marra.s6_l3.repositories.AutoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepo autoreRepo;

    //SALVARE UN NUOVO UTENTE
    public Autore save(Autore autore) {
        if (autoreRepo.existsByEmail(autore.getEmail())) {
            throw new RuntimeException();
        }
        return autoreRepo.save(autore);
    }

    //RECUPERARE TUTTI GLI UTENTI
    public Page<Autore> getAutori(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return autoreRepo.findAll(pageable);
    }

    //RECUPERARE UN UTENTE BY ID
    public Autore getAutore(UUID uuid) {
        return autoreRepo.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }
}
