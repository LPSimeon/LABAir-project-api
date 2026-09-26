package labair_api.services;

import labair_api.dto.*;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.*;
import labair_api.repositories.OrderRepository;
import labair_api.repositories.ShoeRepository;
import labair_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemService cartItemRepository;
    private final ShoeRepository shoeRepository;

    public OrderDTO createOrder(CreateOrderDTO order, String email) {
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));

        List<CartItemDTO> cartItems = cartItemRepository.getCartByUserEmail(userFound.getEmail());
        double totale = 0;

        Order orderToAdd = new Order();

        // to create the order id
        String iniziali =
                order.getDatiSpedizione().getNome().substring(0, 1).toLowerCase()
                        + order.getDatiSpedizione().getCognome().substring(0, 1).toLowerCase();

        String codice = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        orderToAdd.setId(iniziali + "-" + codice);
        System.out.println("Order ID: " + orderToAdd.getId());

        orderToAdd.setDataOrdine(LocalDateTime.now().toString()); // Per adesso metto a stringa
        orderToAdd.setPagamento(order.getPagamento());
        ShippingData shippingData = order.getDatiSpedizione();

        if (shippingData != null) {
            shippingData.setOrdine(orderToAdd);
            orderToAdd.setDatiSpedizione(shippingData);
        }

        orderToAdd.setUtente(userFound);

        List<OrderDetails> details = new ArrayList<>();

        for (CartItemDTO cartItem : cartItems) {
            Shoe shoe = shoeRepository.findById(cartItem.getScarpaId()).orElseThrow(() -> new ResourceNotFoundException("Scarpa non trovata con id: " + cartItem.getScarpaId()));

            OrderDetails detail = new OrderDetails();
            detail.setOrdine(orderToAdd);
            detail.setScarpa(shoe);
            detail.setQuantita(cartItem.getQuantita());
            detail.setTaglia(cartItem.getTaglia());
            detail.setColore(cartItem.getColore());
            detail.setPrezzoUnitario(shoe.getPrezzo());

            details.add(detail);

            totale += shoe.getPrezzo() * cartItem.getQuantita();
        }

        orderToAdd.setTotale(totale);
        orderToAdd.setDettagli(details);


        orderRepository.save(orderToAdd);
        return convertToDTO(orderToAdd); // provare a mettere il convertToDTO
    }

    public OrderDTO findById(String id, String email) {
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));

        Order orderFound = orderRepository.findById(id).orElse(null);

        return convertToDTO(orderFound);
    }

    public List<Order> getOrdersByUserEmail(String email) {
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));

        return orderRepository.findAll();
    }

    public void deleteOrderById(String id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
    }

    public OrderDTO convertToDTO(Order orderToConvert) {
        if (orderToConvert == null) {
            return null;
        }

        OrderDTO convertedOrder = new OrderDTO();

        convertedOrder.setId(orderToConvert.getId());
        convertedOrder.setDataOrdine(orderToConvert.getDataOrdine());
        convertedOrder.setTotale(orderToConvert.getTotale());
        convertedOrder.setMetodoPagamento(orderToConvert.getPagamento());

        // dati_spedizione
        ShippingData shippingData = orderToConvert.getDatiSpedizione();

        if (shippingData != null) {
            ShippingDataDTO shippingDTO = new ShippingDataDTO();

            shippingDTO.setEmail(shippingData.getEmail());
            shippingDTO.setNome(shippingData.getNome());
            shippingDTO.setCognome(shippingData.getCognome());
            shippingDTO.setIndirizzo(shippingData.getIndirizzo());
            shippingDTO.setCap(shippingData.getCap());
            shippingDTO.setCitta(shippingData.getCitta());
            shippingDTO.setPaese(shippingData.getPaese());
            shippingDTO.setTel(shippingData.getTel());

            convertedOrder.setDatiSpedizione(shippingDTO);
        }

        // dettagli
        List<OrderDetailsDTO> dettagli = orderToConvert.getDettagli()
                .stream()
                .map(detail -> {

                    OrderDetailsDTO detailDTO = new OrderDetailsDTO();

                    detailDTO.setId(detail.getId());
                    detailDTO.setScarpaId(detail.getScarpa().getId());
                    detailDTO.setOrdineId(detail.getOrdine().getId());
                    detailDTO.setPrezzoUnitario(detail.getPrezzoUnitario());
                    detailDTO.setQuantita(detail.getQuantita());
                    detailDTO.setTaglia(detail.getTaglia());
                    detailDTO.setColore(detail.getColore());

                    return detailDTO;
                })
                .toList();

        convertedOrder.setDettagli(dettagli);

        return convertedOrder;
    }
}
