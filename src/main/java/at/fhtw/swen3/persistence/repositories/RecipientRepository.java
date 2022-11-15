package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RecipientRepository extends JpaRepository<RecipientEntity, Long> {
    Optional<RecipientEntity> findById(Long id);
}