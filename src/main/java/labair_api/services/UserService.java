package labair_api.services;

import labair_api.dto.UserDTO;
import labair_api.exceptions.ResourceNotFoundException;
import labair_api.models.User;
import labair_api.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public User updateUserById(Long id, UserDTO userDTO) {
        User userFound = userRepository.findById(userDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id: " + id));

        userFound.setNome(userDTO.getNome());
        userFound.setCognome(userDTO.getCognome());
        userFound.setEmail(userDTO.getEmail());
        userFound.setPassword(encoder.encode(userDTO.getPassword()));
        userFound.setDataNascita(userDTO.getDataNascita());

        return userRepository.save(userFound);
    }

    public boolean removeUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }

    public UserDTO convertToDTO(User user) {
        if (user == null) return null;

        UserDTO convertedUser = new UserDTO();

        convertedUser.setId(user.getId());
        convertedUser.setNome(user.getNome());
        convertedUser.setCognome(user.getCognome());
        convertedUser.setEmail(user.getEmail());
        convertedUser.setDataNascita(user.getDataNascita());

        convertedUser.setPassword(user.getPassword());

        return convertedUser;
    }
}
