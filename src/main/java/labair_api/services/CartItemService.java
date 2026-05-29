package labair_api.services;

import labair_api.dto.CartItemDTO;
import labair_api.exceptions.ExistingShoeException;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.CartItem;
import labair_api.models.Shoe;
import labair_api.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItemDTO> findAllItems() {
        List<CartItem> items = cartItemRepository.findAll();
        List<CartItemDTO> convertedItems = new ArrayList<>();

        for (CartItem cartItem : items) {
            convertedItems.add(convertToDTO(cartItem));
        }

        return convertedItems;
    }

    public List<CartItem> getCartByUser(Long userId) {
        return cartItemRepository.findByUtenteId(userId);
    }

    public CartItem addCartItem(CartItemDTO itemDTO) {
        if (cartItemRepository.existsById(itemDTO.getId())) {
            throw new ExistingShoeException();
        }

        CartItem itemToAdd = new CartItem();

        itemToAdd.setId(itemDTO.getId());
        itemToAdd.setQuantita(itemDTO.getQuantita());
        itemToAdd.setColore(itemDTO.getColore());
        itemToAdd.setTaglia(itemDTO.getTaglia());

        if(itemDTO.getScarpaId() != null){
            Shoe scarpa = new Shoe();
            scarpa.setId(itemDTO.getScarpaId());
            itemToAdd.setScarpa(scarpa);
        }

        return cartItemRepository.save(itemToAdd);
    }

    public CartItem updateCartItemQuantity(String id, CartItem selectedCartItem) {
        CartItem existingItem = cartItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item non trovato con id: " + id));

        if (selectedCartItem.getQuantita() != null) existingItem.setQuantita(selectedCartItem.getQuantita());

        return cartItemRepository.save(existingItem);
    }

    public void removeCartItem(String id) {
        cartItemRepository.deleteById(id);
    }

    public CartItemDTO convertToDTO(CartItem itemToConvert) {
        if (itemToConvert == null) return null;

        CartItemDTO convertedItem = new CartItemDTO();

        convertedItem.setId(itemToConvert.getId());
        convertedItem.setQuantita(itemToConvert.getQuantita());
        convertedItem.setColore(itemToConvert.getColore());
        convertedItem.setTaglia(itemToConvert.getTaglia());

        if (itemToConvert.getScarpa() != null) {
            convertedItem.setScarpaId(itemToConvert.getScarpa().getId());
            convertedItem.setNome(itemToConvert.getScarpa().getNome());
            convertedItem.setPrezzo(itemToConvert.getScarpa().getPrezzo());
            convertedItem.setImgScarpaCover(itemToConvert.getScarpa().getImmagineCover());
        }

        return convertedItem;
    }
}
