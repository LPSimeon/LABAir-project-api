package labair_api.services;

import labair_api.dto.CartItemDTO;
import labair_api.exceptions.ExistingShoeException;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.CartItem;
import labair_api.models.ImageColor;
import labair_api.models.Shoe;
import labair_api.models.User;
import labair_api.repositories.CartItemRepository;
import labair_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public List<CartItemDTO> getCartByUserEmail(String email) {
        User userFound = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));
        List<CartItem> userCart = this.cartItemRepository.findByUtenteId(userFound.getId());
        List<CartItemDTO> convertedItems = new ArrayList<>();

        for (CartItem item : userCart) {
            convertedItems.add(convertToDTO(item));
        }

        return convertedItems;
    }

    public CartItemDTO addCartItem(CartItemDTO itemDTO,
                                   String email) {
        if (cartItemRepository.existsById(itemDTO.getId())) {
            throw new ExistingShoeException();
        }
        User userFound = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));

        CartItem itemToAdd = new CartItem();

        itemToAdd.setId(itemDTO.getId());
        itemToAdd.setQuantita(itemDTO.getQuantita());
        itemToAdd.setColore(itemDTO.getColore().toLowerCase());
        itemToAdd.setTaglia(itemDTO.getTaglia());
        itemToAdd.setUtente(userFound);

        if (itemDTO.getScarpaId() != null) {
            Shoe scarpa = new Shoe();
            scarpa.setId(itemDTO.getScarpaId());
            itemToAdd.setScarpa(scarpa);
        }
        cartItemRepository.save(itemToAdd);
        return itemDTO;
    }

    public void updateCartItemQuantity(String id, CartItemDTO item, String email) {
        User userFound = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));
        CartItem existingItem = cartItemRepository
                .findByIdAndUtenteId(id, userFound.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Item non trovato con id: " + id));

        if (item.getQuantita() != null) existingItem.setQuantita(item.getQuantita());

        cartItemRepository.save(existingItem);
    }

    public void removeCartItem(String id, String email) {
        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));

        CartItem existingItem = cartItemRepository.findByIdAndUtenteId(id, userFound.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Item non trovato con id: " + id));

        cartItemRepository.delete(existingItem);
    }

    public CartItemDTO convertToDTO(CartItem item) {
        if (item == null) return null;

        CartItemDTO convertedItem = new CartItemDTO();

        convertedItem.setId(item.getId());
        convertedItem.setQuantita(item.getQuantita());
        convertedItem.setTaglia(item.getTaglia());

        // In order to have the shoe color with the first letter capitalized
        convertedItem.setColore(item.getColore().substring(0, 1).toUpperCase() + item.getColore().substring(1));

        if (item.getScarpa() != null) {
            convertedItem.setScarpaId(item.getScarpa().getId());
            convertedItem.setNome(item.getScarpa().getNome());
            convertedItem.setPrezzo(item.getScarpa().getPrezzo());
            String imgCover = item.getScarpa().getImmaginiScarpa()
                    .stream()
                    .filter(img -> img.getColore().equalsIgnoreCase(item.getColore()))
                    .filter(img -> !img.getUrls().isEmpty())
                    .findFirst()
                    .map(img -> img.getUrls().get(0))
                    .orElse(item.getScarpa().getImmagineCover());

            convertedItem.setImgScarpaCover(imgCover);
        }

        return convertedItem;
    }
}
