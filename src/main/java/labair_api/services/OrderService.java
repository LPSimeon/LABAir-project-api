package labair_api.services;

import labair_api.dto.CartItemDTO;
import labair_api.dto.OrderDTO;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.*;
import labair_api.repositories.OrderRepository;
import labair_api.repositories.ShoeRepository;
import labair_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemService cartItemRepository;
    private final ShoeRepository shoeRepository;

    public Order createOrder(OrderDTO order, String email) {
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));

        List<CartItemDTO> cartItems = cartItemRepository.getCartByUserEmail(userFound.getEmail());
        double totale = 0;

        Order orderToAdd = new Order();

        orderToAdd.setId(order.getId());
        orderToAdd.setDataOrdine(order.getDataOrdine());
        orderToAdd.setTotale(order.getTotale());
        orderToAdd.setPagamento(order.getMetodoPagamento());
        orderToAdd.setUtente(userFound);

        List<OrderDetails> details = new ArrayList<>();

        for (CartItemDTO cartItem : cartItems) {
            Shoe shoe = shoeRepository.findById(cartItem.getScarpaId()).orElseThrow(()-> new ResourceNotFoundException("Scarpa non trovata con id: " +  cartItem.getScarpaId()));

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

        return orderRepository.save(orderToAdd); // provare a mettere il convertToDTO
    }

    public Order findById(String id, String email) {
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));

        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByUserEmail(String email) {
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));

        return orderRepository.findAll();
    }

    public boolean deleteOrderById(String id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public OrderDTO convertToDTO(Order orderToConvert){
        if(orderToConvert == null){ return null; }

        OrderDTO convertedOrder = new OrderDTO();

        convertedOrder.setId(orderToConvert.getId());
        convertedOrder.setDataOrdine(orderToConvert.getDataOrdine());
        convertedOrder.setTotale(orderToConvert.getTotale());
        convertedOrder.setMetodoPagamento(orderToConvert.getPagamento());

        return convertedOrder;
    }
}
