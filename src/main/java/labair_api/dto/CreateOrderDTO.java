package labair_api.dto;

import labair_api.models.ShippingData;
import lombok.Data;

@Data
public class CreateOrderDTO {
    private String id;
    private ShippingData shippingData;
    private String metodoPagamento;
    private String dataOrdine;
}
