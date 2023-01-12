package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HopRepository extends JpaRepository<HopEntity, Long> {
    Optional<HopEntity> findById(Long id);
    Optional<HopEntity> findByCode(String code);
}