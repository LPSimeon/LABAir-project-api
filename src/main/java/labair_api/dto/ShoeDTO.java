package labair_api.dto;

import lombok.Data;

@Data
public class ShoeDTO {
    private Long id;
    private String nome;
    private String colore;
    private String categoria;
    private Double prezzo;
    private String taglia;
    private String immagineCover;
}
