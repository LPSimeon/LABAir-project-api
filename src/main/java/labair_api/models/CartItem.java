package labair_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "item_carrello")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
    @Id
    private String id;

    private Integer productId;
    private Integer quantita;
    private String nome;
    private String colore;
    private Double prezzo;
    private Integer taglia;
    private String imgScarpaCover;

    @ManyToOne()
    @JoinColumn(name = "scarpa_id")
    @ToString.Exclude
    private Shoe scarpa;

    @ManyToOne()
    @JoinColumn(name = "utente_id", nullable = true)
    @ToString.Exclude
    private User utente;
}
