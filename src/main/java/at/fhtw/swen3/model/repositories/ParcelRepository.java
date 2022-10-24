package at.fhtw.swen3.model.repositories;

import at.fhtw.swen3.model.entities.ParcelEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParcelRepository extends JpaRepository <ParcelEntityModel, Long> {

    @Override
    <S extends ParcelEntityModel> S save(S entity);

    @Override
    Optional<ParcelEntityModel> findById(Long aLong);

    @Override
    List<ParcelEntityModel> findAll();

    @Override
    void deleteById(Long aLong);
}

