package labair_api.services;

import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.Order;
import labair_api.models.User;
import labair_api.repositories.OrderRepository;
import labair_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order createOrder(Order order, String email) {
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));



        orderRepository.save(order);

        return order;
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

    public Order updateOrderById(String id, Order modifiedOrder, String email){
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));


        return modifiedOrder;
    }

    public boolean deleteOrderById(String id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
