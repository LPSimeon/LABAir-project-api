package labair_api.controllers;

import labair_api.services.CartItemService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrello")
@CrossOrigin(origins = "http://localhost:4200")
public class CartItemController {
    private CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) { this.cartItemService = cartItemService; }
}
