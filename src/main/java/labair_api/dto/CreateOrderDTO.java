package labair_api.dto;

import labair_api.models.ShippingData;
import labair_api.models.enums.PaymentMethod;
import lombok.Data;

@Data
public class CreateOrderDTO {
    private String id;
    private ShippingData datiSpedizione;
    private PaymentMethod pagamento;
    private String dataOrdine;
}
