package labair_api.controllers;

import labair_api.dto.CreateOrderDTO;
import labair_api.dto.OrderDTO;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.Order;
import labair_api.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/ordine")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(Authentication auth){
        String userEmail = auth.getName();
        return ResponseEntity.ok(orderService.getOrdersByUserEmail(userEmail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(Authentication auth, @PathVariable String id){
        String userEmail = auth.getName();
        return ResponseEntity.ok(orderService.findById(id, userEmail));
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderDTO order, Authentication auth) {
        if(order == null){
            throw new ResourceNotFoundException("Order non trovato");
        }

        String userEmail = auth.getName();
        return ResponseEntity.ok(orderService.createOrder(order, userEmail));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String orderId, Authentication auth) {
        String userEmail = auth.getName();
        return ResponseEntity.ok(orderService.deleteOrderById(orderId));
    }
}
