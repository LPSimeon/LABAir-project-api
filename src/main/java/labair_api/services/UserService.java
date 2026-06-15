package labair_api.services;

import labair_api.dto.UserDTO;
import labair_api.exceptions.ExistingUserException;
import labair_api.models.User;
import labair_api.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) { this.userRepository = userRepository; this.encoder = encoder; }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public UserDTO addUser(UserDTO userDTO){
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new ExistingUserException();
        }

        User userToAdd = new User();
        userToAdd.setNome(userDTO.getNome());
        userToAdd.setCognome(userDTO.getCognome());
        userToAdd.setEmail(userDTO.getEmail());
        userToAdd.setPassword(userDTO.getPassword());
        userToAdd.setGiorno(userDTO.getGiorno());
        userToAdd.setMese(userDTO.getMese());
        userToAdd.setAnno(userDTO.getAnno());

        userRepository.save(userToAdd);
        return userDTO;
    }

    public UserDTO convertToDTO(User user) {
        if (user == null) return null;

        UserDTO convertedUser = new UserDTO();

        convertedUser.setId(user.getId());
        convertedUser.setNome(user.getNome());
        convertedUser.setCognome(user.getCognome());
        convertedUser.setEmail(user.getEmail());

        convertedUser.setPassword(encoder.encode(user.getPassword()));

        convertedUser.setGiorno(user.getGiorno());
        convertedUser.setMese(user.getMese());
        convertedUser.setAnno(user.getAnno());

        return convertedUser;
    }
}
