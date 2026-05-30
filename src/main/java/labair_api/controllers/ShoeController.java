package labair_api.controllers;

import labair_api.dto.ShoeDTO;
import labair_api.models.Shoe;
import labair_api.services.ShoeService;
import org.springframework.http.HttpStatus;
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

    public ShoeController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

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
    ResponseEntity<Map<String, String>> addShoe(@RequestBody Shoe newShoe) {
        Shoe created = shoeService.createNewShoe(newShoe);

        Map<String, String> response = new HashMap<>();

        if (created != null) {
            response.put("code", "201 CREATED");
            response.put("message", "Scarpa creata con successo");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("code", "409 CONFLICT");
            response.put("message", "Scarpa già presente nel db");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @PatchMapping("/{shoeId}")
    ResponseEntity<Map<String, String>> modifyShoe(@PathVariable Long shoeId, @RequestBody Shoe shoeToUpdate) {
        Shoe updated = shoeService.updateShoeById(shoeId, shoeToUpdate);

        Map<String, String> response = new HashMap<>();

        if (updated != null) {
            response.put("code", "202 ACCEPTED");
            response.put("message", "Scarpa aggiornata con successo");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            response.put("code", "404 NOT FOUND");
            response.put("message", "Scarpa non trovata nel db con shoeId: " + shoeId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{shoeId}")
    ResponseEntity<Map<String, String>> deleteShoe(@PathVariable Long shoeId) {
        boolean deleted = shoeService.deleteShoeById(shoeId);

        Map<String, String> response = new HashMap<>();

        if (deleted) {
            response.put("code", "202 ACCEPTED");
            response.put("message", "Scarpa eliminata");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            response.put("code", "404 NOT FOUND");
            response.put("message", "Scarpa non trovata nel db con shoeId: " + shoeId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
