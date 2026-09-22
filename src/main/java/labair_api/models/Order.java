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

    private Double totale;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    @ToString.Exclude
    private User utente;

    @OneToOne(mappedBy = "ordine", cascade = CascadeType.ALL)
    private ShippingData datiSpedizione;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrderDetails> dettagli;
}
