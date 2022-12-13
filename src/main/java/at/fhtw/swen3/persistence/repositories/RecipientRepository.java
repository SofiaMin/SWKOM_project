package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RecipientRepository extends JpaRepository<RecipientEntity, Long> {
    Optional<RecipientEntity> findById(Long id);
}
