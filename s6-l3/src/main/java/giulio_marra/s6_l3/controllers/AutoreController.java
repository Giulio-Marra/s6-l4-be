package giulio_marra.s6_l3.controllers;

import giulio_marra.s6_l3.entities.Autore;
import giulio_marra.s6_l3.exceptions.BadRequestException;
import giulio_marra.s6_l3.payloads.AutoreDTO;
import giulio_marra.s6_l3.payloads.AutoreRsponsDTO;
import giulio_marra.s6_l3.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public AutoreRsponsDTO saveAutore(@RequestBody @Validated AutoreDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors().toString());
        }
        return new AutoreRsponsDTO(autoreService.save(body).getUuid());
    }

    @GetMapping("/{autoreId}")
    public Autore getAutoreById(@PathVariable UUID autoreId) {
        return autoreService.getAutore(autoreId);
    }

    @PostMapping("/{autoreId}/avatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile image, @PathVariable("autoreId") UUID autoreId) throws IOException {

        return autoreService.uploadImage(autoreId, image);
    }

}
