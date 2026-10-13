package labair_api.services;

import labair_api.dto.ShoeDTO;
import labair_api.exceptions.ExistingShoeException;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.Shoe;
import labair_api.repositories.ShoeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoeService {
    private final ShoeRepository shoeRepository;

    public ShoeService(ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
    }

    public List<ShoeDTO> findAllShoes() {
        List<Shoe> shoes = shoeRepository.findAll();
        List<ShoeDTO> convertedShoes = new ArrayList<>();

        for (Shoe shoe : shoes) {
            convertedShoes.add(convertToDTO(shoe));
        }

//        shoeRepository.findAll()
//            .stream()
//            .map(this::convertToDTO)
//            .toList();

        return convertedShoes;
    }

    public Shoe createNewShoe(Shoe shoe) {
        if (shoeRepository.getShoeByNomeIgnoreCase(shoe.getNome()) != null) {
            throw new ExistingShoeException();
        }

        return shoeRepository.save(shoe);
    }

    public void updateShoeById(Long id, Shoe shoeDetails) {
        // Imparare a usare exception handler
        Shoe existingShoe = shoeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Scarpa non trovata con id: " + shoeDetails.getId()));
        updateShoeProperties(shoeDetails, existingShoe);
        shoeRepository.save(existingShoe);
    }

    public void deleteShoeById(Long id) {
        if (!shoeRepository.existsById(id)) {
            shoeRepository.deleteById(id);
        }
    }

    public List<ShoeDTO> findShoesByFilters(String category, String sortBy) {
        Sort sortedShoes = getSort(sortBy);
        List<Shoe> extractedShoes;
        List<ShoeDTO> convertedShoes = new ArrayList<>();

        if (category == null || category.isBlank() || category.equalsIgnoreCase("all")) {
            extractedShoes = shoeRepository.findAll(sortedShoes);
        } else {
            extractedShoes = shoeRepository.findByCategoria(category, sortedShoes);
        }

        for (Shoe shoe : extractedShoes) {
            convertedShoes.add(convertToDTO(shoe));
        }

        return convertedShoes;
    }

    public Shoe findShoeByNome(String nome) {
        String shoeWithSpaces = nome.replace("-", " ");

        if (!shoeRepository.existsByNomeIgnoreCase(shoeWithSpaces)) {
            throw new ResourceNotFoundException("Scarpa non trovata con nome: " + nome);
        }

        return shoeRepository.getShoeByNomeIgnoreCase(shoeWithSpaces);
    }

    public ShoeDTO convertToDTO(Shoe shoe) {
        if (shoe == null) return null;

        ShoeDTO convertedShoe = new ShoeDTO();

        convertedShoe.setId(shoe.getId());
        convertedShoe.setNome(shoe.getNome());
        convertedShoe.setCategoria(shoe.getCategoria());
        convertedShoe.setPrezzo(shoe.getPrezzo());
        convertedShoe.setColoriDisponibili(shoe.getColoriDisponibili());
        convertedShoe.setImmagineCover(shoe.getImmagineCover());
        convertedShoe.setNuoviArrivi(shoe.getNuoviArrivi());
        convertedShoe.setBestSeller(shoe.getBestSeller());

        return convertedShoe;
    }

    private void updateShoeProperties(Shoe target, Shoe source) {
        if (source.getNome() != null) target.setNome(source.getNome());
        if (source.getCategoria() != null) target.setCategoria(source.getCategoria());
        if (source.getPrezzo() != null) target.setPrezzo(source.getPrezzo());
        if (source.getTaglieDisponibili() != null) target.setTaglieDisponibili(source.getTaglieDisponibili());
        if (source.getColoriDisponibili() != null) target.setColoriDisponibili(source.getColoriDisponibili());
        if (source.getDescrizione() != null) target.setDescrizione(source.getDescrizione());
        if (source.getImmagineCover() != null) target.setImmagineCover(source.getImmagineCover());
        if (source.getImmaginiScarpa() != null) target.setImmaginiScarpa(source.getImmaginiScarpa());
        if (source.getNuoviArrivi() != null) target.setNuoviArrivi(source.getNuoviArrivi());
        if (source.getBestSeller() != null) target.setBestSeller(source.getBestSeller());
    }

    private Sort getSort(String sortBy) {
        if (sortBy == null || sortBy.isBlank()) {
            return Sort.unsorted();
        }

        return switch (sortBy) {
            case "newest"    -> Sort.by("nuoviArrivi").descending();
            case "priceAsc"  -> Sort.by("prezzo").ascending();
            case "priceDesc" -> Sort.by("prezzo").descending();
            default          -> Sort.unsorted();
        };
    }
}
