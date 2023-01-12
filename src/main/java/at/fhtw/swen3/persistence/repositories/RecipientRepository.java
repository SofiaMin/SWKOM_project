package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipientRepository extends JpaRepository<RecipientEntity, Long> {
    Optional<RecipientEntity> findById(Long id);
    RecipientEntity save(RecipientEntity recipient);
}
