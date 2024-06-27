package giulio_marra.s6_l3.entities;

import giulio_marra.s6_l3.enums.Categorie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private UUID uuid;

    private List<Categorie> categorie;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempo_di_lettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

    public Post(List<Categorie> categorie, String titolo, String cover, String contenuto, int tempo_di_lettura, Autore autore) {
        this.categorie = categorie;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempo_di_lettura = tempo_di_lettura;
        this.autore = autore;
    }
}
