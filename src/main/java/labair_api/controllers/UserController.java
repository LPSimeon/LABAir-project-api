package labair_api.controllers;

import labair_api.dto.UserDTO;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.User;
import labair_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/utente")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PatchMapping("/{userId}")
    ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserDTO selectedUser) {
        User updated = userService.updateUserById(userId, selectedUser);
        if (updated == null) {
            throw new ResourceNotFoundException("Utente non trovato");
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.removeUser(userId);
        return ResponseEntity.noContent().build();
    }
}
