package labair_api.dto;

import labair_api.models.enums.PaymentMethod;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private String id;
    private ShippingDataDTO datiSpedizione;
    private PaymentMethod metodoPagamento;
    private List<OrderDetailsDTO> dettagli;
    private Double totale;
    private LocalDateTime dataOrdine;
}
