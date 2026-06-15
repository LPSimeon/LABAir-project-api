package labair_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nome;
    private String cognome;
//    private String indirizzo;
//    private String cap;
//    private String citta;
//    private String paese;
//    private String telefono;
    private String giorno;
    private String mese;
    private String anno;
//
//    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
//    private List<Order> ordini;
//
//    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CartItem> carrello;
}
