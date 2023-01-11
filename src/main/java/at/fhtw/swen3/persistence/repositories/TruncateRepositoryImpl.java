package at.fhtw.swen3.persistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TruncateRepositoryImpl {
    @Autowired
    private List<JpaRepository<?, ?>> repositories;

    @Transactional
    public void truncate() {
        repositories.forEach(CrudRepository::deleteAll);
    }
}
