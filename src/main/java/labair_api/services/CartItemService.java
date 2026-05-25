package labair_api.services;

import labair_api.exceptions.ExistingShoeException;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.CartItem;
import labair_api.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) { this.cartItemRepository = cartItemRepository; }

    public List<CartItem> findAllItems(){
        return cartItemRepository.findAll();
    }

    public List<CartItem> getCartByUser(Long userId) {
        return cartItemRepository.findByUtenteId(userId);
    }

    public CartItem addCartItem(CartItem cartItem){
        if(cartItemRepository.existsById(cartItem.getId())){
            throw new ExistingShoeException();
        }

        return cartItemRepository.save(cartItem);

    }

    public CartItem updateCartItemQuantity(String id, CartItem selectedCartItem){
        CartItem existingItem = cartItemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item non trovato con id: " + id));

        if(selectedCartItem.getQuantita() != null) existingItem.setQuantita(selectedCartItem.getQuantita());

        return cartItemRepository.save(existingItem);
    }

    public void removeCartItem(String id){
        cartItemRepository.deleteById(id);
    }
}
