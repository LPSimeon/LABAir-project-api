package labair_api.repositories;

import labair_api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findByIdAndUtente_Email(String id, String email);

    Optional<Order> findByIdAndOrderAccessToken(String id, String orderAccessToken);

    List<Order> findAllByUtente_Email(String email);
}
