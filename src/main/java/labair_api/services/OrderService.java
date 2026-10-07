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
        Order orderToAdd = new Order();
        List<CartItemDTO> cartItems;

        if (email != null) {
            User userFound = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Utente non trovato con email: " + email
                            ));

            orderToAdd.setUtente(userFound);
            cartItems = cartItemRepository.getCartByUserEmail(userFound.getEmail());
        } else {
            String orderAccessToken = UUID.randomUUID().toString().substring(0, 4);
            orderToAdd.setOrderAccessToken(orderAccessToken);

            orderToAdd.setUtente(null);
            cartItems = order.getCartItems();
        }

        double totale = 0;

        // to create the order id
        String iniziali =
                order.getDatiSpedizione().getNome().substring(0, 1).toLowerCase()
                        + order.getDatiSpedizione().getCognome().substring(0, 1).toLowerCase();

        String codice = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        orderToAdd.setId(iniziali + "-" + codice);
//        System.out.println("Order ID: " + orderToAdd.getId());

        orderToAdd.setDataOrdine(LocalDateTime.now().toString()); // Per adesso metto a stringa
        orderToAdd.setPagamento(order.getPagamento());
        ShippingData shippingData = order.getDatiSpedizione();

        if (shippingData != null) {
            shippingData.setOrdine(orderToAdd);
            orderToAdd.setDatiSpedizione(shippingData);
        }

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
        return convertToDTO(orderToAdd);
    }

    public OrderDTO findById(String id, String email, String orderAccessToken) {
        Order orderFound;

        if (email != null) {
            orderFound = orderRepository
                    .findByIdAndUtente_Email(id, email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Ordine non trovato con id: " + id
                            )
                    );

        } else if (orderAccessToken != null) {
            orderFound = orderRepository
                    .findByIdAndOrderAccessToken(id, orderAccessToken)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Ordine non trovato con id: " + id
                            )
                    );
        } else {
            throw new ResourceNotFoundException("Credenziali ordine mancanti");
        }

        return convertToDTO(orderFound);
    }

    public List<OrderDTO> getAllOrders(String email) {
        List<Order> orders = orderRepository.findAllByUtente_Email(email);
        List<OrderDTO> convertedOrders = new ArrayList<>();

        for (Order order : orders) {
            convertedOrders.add(convertToDTO(order));
        }

        return convertedOrders;
    }

    public void deleteOrderById(String id, String email) {
          if (orderRepository.existsByIdAndUtente_Email(id,email)) {
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
