package labair_api.controllers;

import labair_api.dto.CartItemDTO;
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
    public ResponseEntity<List<CartItemDTO>> getAllItems(){
        return ResponseEntity.ok(cartItemService.findAllItems());
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> addNewItem(@RequestBody CartItemDTO itemToAdd){
        return ResponseEntity.ok(cartItemService.addCartItem(itemToAdd));
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Map<String,String>> updateItemQuantity(@PathVariable String itemId, @RequestBody CartItemDTO selectedCartItem){
        CartItem updated = cartItemService.updateCartItemQuantity(itemId, selectedCartItem);

        Map<String, String> response = new HashMap<>();

        if(updated != null){
            response.put("code", "202 ACCEPTED");
            response.put("message", "Quantità aggiornata con successo");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            response.put("code", "404 NOT FOUND");
            response.put("message", "Item non trovato nel db con id: " + itemId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Map<String, String>> deleteItem(@PathVariable String itemId){
        boolean deleted = cartItemService.removeCartItem(itemId);

        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("code", "202 ACCEPTED");
            response.put("message", "Item eliminato");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            response.put("code", "404 NOT FOUND");
            response.put("message", "Item non trovato nel db con id: " + itemId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
