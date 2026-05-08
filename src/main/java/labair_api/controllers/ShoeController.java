package labair_api.controllers;

import labair_api.models.Shoe;
import labair_api.services.ShoeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scarpeList")
@CrossOrigin(origins = "http://localhost:4200")
public class ShoeController {
    private final ShoeService shoeService;

    public ShoeController(ShoeService shoeService) { this.shoeService = shoeService; }

    @GetMapping
    public ResponseEntity<List<Shoe>> getShoes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy) {

        if (category != null || sortBy != null) {
            return ResponseEntity.ok(shoeService.getShoesByFilters(category, sortBy));
        }
        return ResponseEntity.ok(shoeService.getAllShoes());
    }

    @GetMapping("/{nome}")
    ResponseEntity<Shoe> getShoe(@PathVariable String nome) {
        return ResponseEntity.ok(shoeService.findShoeByNome(nome));
    }

    @PostMapping
    ResponseEntity<Shoe> addShoe(@RequestBody Shoe newShoe) {
        return ResponseEntity.ok(shoeService.saveNewShoe(newShoe));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> deleteShoe(@PathVariable Long id) {
        shoeService.deleteShoeById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
