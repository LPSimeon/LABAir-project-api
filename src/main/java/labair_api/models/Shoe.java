package labair_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "scarpe")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;
    private Double prezzo;

    @ElementCollection
    @CollectionTable(name = "scarpa_taglie", joinColumns = @JoinColumn(name = "scarpa_id"))
    private List<String> taglieDisponibili;

    @ElementCollection
    @CollectionTable(name = "scarpa_colori", joinColumns = @JoinColumn(name = "scarpa_id"))
    private List<String> coloriDisponibili;

    private String descrizione;
    private String immagineCover;

    @OneToMany(mappedBy = "scarpa", cascade = CascadeType.ALL)
    private List<ImageColor> immaginiScarpa;

    private Boolean nuoviArrivi;
    private Integer bestSeller;
}
