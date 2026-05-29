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

    public CartItemService(CartItemRepository cartItemRepository) { this.cartItemRepository = cartItemRepository; }

    public List<CartItemDTO> findAllItems() {
        List<CartItem> items = cartItemRepository.findAll();
        List<CartItemDTO> convertedItems = new ArrayList<>();

        for (CartItem cartItem : items) {
            convertedItems.add(convertToDTO(cartItem));
        }

        return convertedItems;
    }

    public List<CartItem> getCartByUser(Long id) {
        return cartItemRepository.findByUtenteId(id);
    }

    public CartItemDTO addCartItem(CartItemDTO itemDTO) {
        if (cartItemRepository.existsById(itemDTO.getId())) {
            throw new ExistingShoeException();
        }

        CartItem itemToAdd = new CartItem();

        itemToAdd.setId(itemDTO.getId());
        itemToAdd.setQuantita(itemDTO.getQuantita());
        itemToAdd.setColore(itemDTO.getColore().toLowerCase());
        itemToAdd.setTaglia(itemDTO.getTaglia());

        if(itemDTO.getScarpaId() != null){
            Shoe scarpa = new Shoe();
            scarpa.setId(itemDTO.getScarpaId());
            itemToAdd.setScarpa(scarpa);
        }
        cartItemRepository.save(itemToAdd);
        return itemDTO;
    }

    public CartItem updateCartItemQuantity(String id, CartItemDTO item) {
        CartItem existingItem = cartItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item non trovato con id: " + id));
        if (item.getQuantita() != null) existingItem.setQuantita(item.getQuantita());

        return cartItemRepository.save(existingItem);
    }

    public boolean removeCartItem(String id) {
        if(!cartItemRepository.existsById(id)) {
            return false;
        }

        cartItemRepository.deleteById(id);
        return true;
    }

    public CartItemDTO convertToDTO(CartItem item) {
        if (item == null) return null;

        CartItemDTO convertedItem = new CartItemDTO();

        convertedItem.setId(item.getId());
        convertedItem.setQuantita(item.getQuantita());
        convertedItem.setColore(item.getColore().toUpperCase());
        convertedItem.setTaglia(item.getTaglia());

        if (item.getScarpa() != null) {
            convertedItem.setScarpaId(item.getScarpa().getId());
            convertedItem.setNome(item.getScarpa().getNome());
            convertedItem.setPrezzo(item.getScarpa().getPrezzo());
            convertedItem.setImgScarpaCover(item.getScarpa().getImmagineCover());
        }

        return convertedItem;
    }
}
