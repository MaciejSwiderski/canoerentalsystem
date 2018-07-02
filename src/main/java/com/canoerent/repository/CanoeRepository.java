package com.canoerent.repository;

import com.canoerent.model.Canoe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanoeRepository extends JpaRepository<Canoe, Long> {

    Optional<Canoe> findCanoeByCanoeType(String canoe);

    Canoe findCanoeById(long id);

    Optional<Canoe> deleteCanoeById(long id);
}
