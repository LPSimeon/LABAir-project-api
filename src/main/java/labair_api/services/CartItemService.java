package labair_api.services;

import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.CartItem;
import labair_api.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) { this.cartItemRepository = cartItemRepository; }

    public List<CartItem> getCartByUser(Long userId) {
        return cartItemRepository.findByUtenteId(userId);
    }

    public CartItem addCartItem(CartItem cartItem){
//        boolean isCartItemExistent = cartItemRepository.existsById(cartItem.getId());
//        if(isCartItemExistent){
//            throw ResourceNotFoundException("CartItem with id "+cartItem.getId()+" already exists");
//        } else {
            return cartItemRepository.save(cartItem);

    }

    public void updateCartItemQuantity(CartItem cartItem, int quantity){
        cartItemRepository.findById(cartItem.getId()).ifPresent(item ->{
            item.setQuantita(quantity);
            cartItemRepository.save(item);
        });
    }

    public void removeCartItem(Long id){
        cartItemRepository.deleteById(id);
    }
}
