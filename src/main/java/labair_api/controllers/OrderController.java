package labair_api.controllers;

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

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order, Authentication auth) {
        String userEmail = auth.getName();
        return ResponseEntity.ok(orderService.createOrder(order, userEmail));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody Order modifiedOrder, Authentication auth) {
        String userEmail = auth.getName();
        return ResponseEntity.ok(orderService.updateOrderById(orderId, modifiedOrder, userEmail));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String orderId, Authentication auth) {
        String userEmail = auth.getName();
        return ResponseEntity.ok(orderService.deleteOrderById(orderId));
    }
}
