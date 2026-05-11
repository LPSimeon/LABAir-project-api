package labair_api.repositories;

import labair_api.models.Shoe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {
    Shoe getShoeByNome(String nome);

    List<Shoe> findByCategoria(String categoria, Sort scarpeOrdinate);

    boolean existsByNome(String nome);
}
