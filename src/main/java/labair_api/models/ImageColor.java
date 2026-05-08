package labair_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "immagini_colore")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String colore;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "url_images", joinColumns = @JoinColumn(name = "img_colore_id"))
    @Column(name = "url")
    private List<String> urls;

    @ManyToOne
    @JoinColumn(name = "scarpa_id")
    @JsonIgnore
    private Shoe scarpa;
}
