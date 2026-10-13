package labair_api.controllers;

import labair_api.dto.ShoeDTO;
import labair_api.models.Shoe;
import labair_api.services.ShoeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/scarpeList")
@RequiredArgsConstructor
public class ShoeController {
    private final ShoeService shoeService;

    @GetMapping
    public ResponseEntity<List<ShoeDTO>> getAllShoes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy) {

        if (category != null || sortBy != null) {
            return ResponseEntity.ok(shoeService.findShoesByFilters(category, sortBy));
        }
        return ResponseEntity.ok(shoeService.findAllShoes());
    }

    @GetMapping("/{shoeName}")
    ResponseEntity<Shoe> getShoeByNome(@PathVariable String shoeName) {
        return ResponseEntity.ok(shoeService.findShoeByNome(shoeName));
    }

    @PostMapping
    ResponseEntity<Shoe> addShoe(@RequestBody Shoe newShoe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shoeService.createNewShoe(newShoe));
    }

    @PatchMapping("/{shoeId}")
    ResponseEntity<Void> modifyShoe(@PathVariable Long shoeId, @RequestBody Shoe shoeToUpdate) {
        shoeService.updateShoeById(shoeId, shoeToUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{shoeId}")
    ResponseEntity<Void> deleteShoe(@PathVariable Long shoeId) {
        shoeService.deleteShoeById(shoeId);
        return ResponseEntity.noContent().build();
    }
}
