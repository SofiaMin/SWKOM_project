package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HopArrivalRepository extends JpaRepository<HopArrivalEntity, Long> {
    Optional<HopArrivalEntity> findById(Long id);
}
