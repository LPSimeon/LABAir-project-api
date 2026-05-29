package labair_api.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private String id;
    private Integer quantita;
    private Long scarpaId;
    private String nome;
    private String colore;
    private Double prezzo;
    private String taglia;
    private String imgScarpaCover;
}
