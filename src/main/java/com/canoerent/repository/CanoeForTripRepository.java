package com.canoerent.repository;

import com.canoerent.model.CanoeTrip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanoeForTripRepository extends JpaRepository<CanoeTrip, Long> {

    Optional<CanoeTrip> findCanoeTripsByCanoeTripType(String canoe);

    CanoeTrip findCanoeTripByCanoeTripId(long id);

    Optional<CanoeTrip> deleteCanoeTripByCanoeTripId(long id);
}
