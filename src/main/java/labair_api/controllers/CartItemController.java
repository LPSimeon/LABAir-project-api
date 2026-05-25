package labair_api.controllers;

import labair_api.models.CartItem;
import labair_api.services.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carrello")
@CrossOrigin(origins = "http://localhost:4200")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllItems(){
        return ResponseEntity.ok(cartItemService.findAllItems());
    }

    @PostMapping
    public ResponseEntity<CartItem> addNewItem(@RequestBody CartItem itemToAdd){
        return ResponseEntity.ok(cartItemService.addCartItem(itemToAdd));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateItemQuantity(@PathVariable String id, @RequestBody CartItem itemToModify){
        CartItem updated = cartItemService.updateCartItemQuantity(id, itemToModify);

        Map<String, String> response = new HashMap<>();

        if(updated != null){
            response.put("code", "202 ACCEPTED");
            response.put("message", "Quantità aggiornata con successo");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            response.put("code", "404 NOT FOUND");
            response.put("message", "Item non trovato nel db con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteItem(@RequestBody CartItem itemToDelete){
        cartItemService.removeCartItem(itemToDelete.getId());
        return ResponseEntity.ok("FATTO");
    }
}
