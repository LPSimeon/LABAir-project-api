package labair_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dati_spedizione")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShippingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String cap;
    private String citta;
    private String paese;
    private String tel;
}
