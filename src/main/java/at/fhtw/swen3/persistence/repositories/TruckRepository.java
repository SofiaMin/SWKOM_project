package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<TruckEntity, Long> {
    Optional<TruckEntity> findById(Long id);
    List<TruckEntity> findAll();
}

