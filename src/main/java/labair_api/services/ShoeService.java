package labair_api.services;

import labair_api.dto.ShoeDTO;
import labair_api.exceptions.ExistingShoeException;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.Shoe;
import labair_api.repositories.ShoeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService {
    private final ShoeRepository shoeRepository;

    public ShoeService(ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
    }

    public List<Shoe> findAllShoes() {
        return shoeRepository.findAll();
    }

    public Shoe createNewShoe(Shoe shoe) {
        if (shoeRepository.getShoeByNomeIgnoreCase(shoe.getNome()) != null) {
            throw new ExistingShoeException();
        }

        return shoeRepository.save(shoe);
    }

    public Shoe updateShoeById(Long shoeId, Shoe shoeDetails){
        Shoe existingShoe = shoeRepository.findById(shoeId).orElseThrow(() -> new ResourceNotFoundException("Scarpa non trovata con id: " + shoeDetails.getId()));

        if(shoeDetails.getNome() != null) existingShoe.setNome(shoeDetails.getNome());
        if(shoeDetails.getCategoria() != null) existingShoe.setCategoria(shoeDetails.getCategoria());
        if(shoeDetails.getPrezzo() != null) existingShoe.setPrezzo(shoeDetails.getPrezzo());
        if(shoeDetails.getTaglieDisponibili() != null) existingShoe.setTaglieDisponibili(shoeDetails.getTaglieDisponibili());
        if(shoeDetails.getColoriDisponibili() != null) existingShoe.setColoriDisponibili(shoeDetails.getColoriDisponibili());
        if(shoeDetails.getDescrizione() != null) existingShoe.setDescrizione(shoeDetails.getDescrizione());
        if(shoeDetails.getImmagineCover() != null) existingShoe.setImmagineCover(shoeDetails.getImmagineCover());
        if(shoeDetails.getImmaginiScarpa() != null) existingShoe.setImmaginiScarpa(shoeDetails.getImmaginiScarpa());
        if(shoeDetails.getNuoviArrivi() != null) existingShoe.setNuoviArrivi(existingShoe.getNuoviArrivi());
        if(shoeDetails.getBestSeller() != null) existingShoe.setBestSeller(existingShoe.getBestSeller());

        return shoeRepository.save(existingShoe);
    }

    public boolean deleteShoeById(Long id) {
        if (!shoeRepository.existsById(id)) {
            return false;
        }
        shoeRepository.deleteById(id);
        return true;
    }

    public List<Shoe> getShoesByFilters(String category, String sortBy) {
        Sort sortedShoes = Sort.unsorted();

        if (sortBy != null && !sortBy.isEmpty()) {
            sortedShoes = switch (sortBy) {
                case "newest" -> Sort.by("nuoviArrivi").descending();
                case "priceAsc" -> Sort.by("prezzo").ascending();
                case "priceDesc" -> Sort.by("prezzo").descending();
                default -> Sort.unsorted();
            };
        }

        if(category == null || category.isBlank() || category.equalsIgnoreCase("all"))
            return shoeRepository.findAll(sortedShoes);
        else
            return shoeRepository.findByCategoria(category, sortedShoes);
    }

    public Shoe findShoeByNome(String nome) {
        if (!shoeRepository.existsByNomeIgnoreCase(nome)) {
            throw new ResourceNotFoundException("Scarpa non trovata con nome: " + nome);
        }

        return shoeRepository.getShoeByNomeIgnoreCase(nome);
    }

    public ShoeDTO convertToDTO(Shoe shoeToConvert){
        if(shoeToConvert == null) return null;

        ShoeDTO convertedShoe = new ShoeDTO();

        convertedShoe.setId(shoeToConvert.getId());
        convertedShoe.setNome(shoeToConvert.getNome());
        convertedShoe.setCategoria(shoeToConvert.getCategoria());
        convertedShoe.setPrezzo(shoeToConvert.getPrezzo());
        convertedShoe.setImmagineCover(shoeToConvert.getImmagineCover());



        return convertedShoe;
    }
}
