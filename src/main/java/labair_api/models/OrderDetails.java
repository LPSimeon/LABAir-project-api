package labair_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "dettagli_ordine")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prezzo_unitario")
    private Double prezzoUnitario;
    private Integer quantita;
    private String taglia;
    private String colore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordine_id", nullable = false)
    @ToString.Exclude
    private Order ordine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scarpa_id")
    @ToString.Exclude
    private Shoe scarpa;
}
