package labair_api.controllers;

import labair_api.dto.UserDTO;
import labair_api.models.User;
import labair_api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/utente")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.addUser(userDTO));
    }

    @PatchMapping("/{userId}")
    ResponseEntity<Map<String, String>> updateUser(@PathVariable Long userId, @RequestBody UserDTO selectedUser) {
        User updated = userService.updateUserById(userId, selectedUser);

        Map<String, String> response = new HashMap<>();

        if(updated != null){
            response.put("code", "202 ACCEPTED");
            response.put("message", "Utente aggiornato con successo");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            response.put("code", "404 NOT FOUND");
            response.put("message", "Utente non trovato nel db con id: " + userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userId) {
        boolean deleted = userService.removeUser(userId);

        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("code", "202 ACCEPTED");
            response.put("message", "Item eliminato");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            response.put("code", "404 NOT FOUND");
            response.put("message", "Item non trovato nel db con id: " + userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
