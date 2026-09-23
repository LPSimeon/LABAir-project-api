package labair_api.dto;

import lombok.Data;

@Data
public class OrderDetailsDTO {
    private Long id;
    private Long scarpaId;
    private String ordineId;
    private Double prezzoUnitario;
    private Integer quantita;
    private String taglia;
    private String colore;
}
