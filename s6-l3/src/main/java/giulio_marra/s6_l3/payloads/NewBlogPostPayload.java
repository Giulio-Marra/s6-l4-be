package giulio_marra.s6_l3.payloads;

import giulio_marra.s6_l3.enums.Categorie;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class NewBlogPostPayload {
    private UUID uuid;
    private List<Categorie> categorie;
    private String contenuto;
    private int tempo_di_lettura;
    private String titolo;
}
