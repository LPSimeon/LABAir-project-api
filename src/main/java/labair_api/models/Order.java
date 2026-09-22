package labair_api.models;

import jakarta.persistence.*;
import labair_api.models.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordini")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    private String id;

    private LocalDateTime dataOrdine;

    @Enumerated(EnumType.STRING)
    private PaymentMethod pagamento;

    // private CartItem[] prodotti; mettere la relazione
    private Double totale;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @ToString.Exclude
    private User utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrderDetails> dettagli;

    // Mettere la relazione con CartItem
}
