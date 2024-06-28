package giulio_marra.s6_l3.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giulio_marra.s6_l3.entities.Autore;
import giulio_marra.s6_l3.exceptions.NotFoundException;
import giulio_marra.s6_l3.payloads.AutoreDTO;
import giulio_marra.s6_l3.repositories.AutoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepo autoreRepo;

    @Autowired
    private Cloudinary cloudinary;

    public Autore save(AutoreDTO body) {

        if (autoreRepo.existsByEmail(body.email())) {
            throw new RuntimeException("El'email esiste gia");
        }

        Autore autore = new Autore();
        autore.setNome(body.nome());
        autore.setCognome(body.cognome());
        autore.setEmail(body.email());
        autore.setData_di_nascita(body.data_di_nascita());


        return autoreRepo.save(autore);
    }

    public Page<Autore> getAutori(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return autoreRepo.findAll(pageable);
    }

    public Autore getAutore(UUID uuid) {
        return autoreRepo.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public String uploadImage(UUID uuid, MultipartFile file) throws IOException {
        Autore autore = getAutore(uuid);
        String imageUrl = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");

        autore.setAvatar(imageUrl);

        autoreRepo.save(autore);
        return imageUrl;
    }
}
