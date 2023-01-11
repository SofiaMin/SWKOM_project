package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
    Optional<WarehouseEntity> findById(Long id);

    @Query(value = "select h.*, w.level " +
            "from warehouse_next_hops wnh " +
            "join warehouse w on w.id = wnh.warehouse_entity_id " +
            "join hop h on h.id = w.id " +
            "where next_hops_id = :id", nativeQuery = true)
    Optional<WarehouseEntity> findWarehouseByHopId(Long id);
}
