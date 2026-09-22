package labair_api.dto;

import jakarta.persistence.*;
import labair_api.models.Order;
import labair_api.models.Shoe;
import lombok.Data;
import lombok.ToString;

@Data
public class OrderDetailsDTO {
    private Long id;
    private Long scarpaId;
    private Double prezzoUnitario;
    private Integer quantita;
    private String taglia;
    private String colore;
}
