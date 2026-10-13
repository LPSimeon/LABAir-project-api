package labair_api.controllers;

import labair_api.dto.CartItemDTO;
import labair_api.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/carrello")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping
    ResponseEntity<List<CartItemDTO>> getCartItem(Authentication auth) {
        String userEmail = auth.getName();
        return ResponseEntity.ok(cartItemService.getCartByUserEmail(userEmail));
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> addNewItem(@RequestBody CartItemDTO itemToAdd, Authentication auth) {
        String userEmail = auth.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addCartItem(itemToAdd, userEmail));
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Void> updateItemQuantity(@PathVariable String itemId, @RequestBody CartItemDTO selectedCartItem, Authentication auth) {
        String userEmail = auth.getName();
        cartItemService.updateCartItemQuantity(itemId, selectedCartItem, userEmail);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable String itemId, Authentication auth) {
        String userEmail = auth.getName();
        cartItemService.removeCartItem(itemId, userEmail);
        return ResponseEntity.noContent().build();
    }
}
