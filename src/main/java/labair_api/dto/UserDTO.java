package labair_api.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascita;

    private String password;
}
