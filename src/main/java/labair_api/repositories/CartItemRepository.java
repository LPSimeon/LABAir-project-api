package labair_api.repositories;

import labair_api.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUtenteId(Long id);

    Optional<CartItem> findByUtenteAndScarpaId(Long id, Long scarpaId);
}
