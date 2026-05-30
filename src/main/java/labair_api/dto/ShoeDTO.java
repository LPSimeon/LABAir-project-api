package labair_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoeDTO {
    private Long id;
    private String nome;
    private String categoria;
    private Double prezzo;
    private List<String> coloriDisponibili;
    private String immagineCover;
    private Boolean nuoviArrivi;
    private Integer bestSeller;
}
