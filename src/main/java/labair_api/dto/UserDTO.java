package labair_api.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String giorno;
    private String mese;
    private String anno;
}
