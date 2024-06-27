package giulio_marra.s6_l3.controllers;

import giulio_marra.s6_l3.entities.Autore;
import giulio_marra.s6_l3.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/autori")
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public Page<Autore> getAllAutori(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "uuid") String sortBy) {
        return autoreService.getAutori(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore saveAutore(@RequestBody Autore body) throws Exception {
        return autoreService.save(body);
    }

    @GetMapping("/{autoreId}")
    public Autore getAutoreById(@PathVariable UUID autoreId) {
        return autoreService.getAutore(autoreId);
    }

}
