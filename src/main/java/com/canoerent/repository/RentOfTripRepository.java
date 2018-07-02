package com.canoerent.repository;


import com.canoerent.model.RentOfTrips;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentOfTripRepository extends JpaRepository<RentOfTrips, Long> {

    List<RentOfTrips> findByUserEmailOrderByRentOfTripId(String email);

    List<RentOfTrips> deleteRentOfTripsByUserEmail(String email);

    List<RentOfTrips> findAllByOrderByRentOfTripId();

    List<RentOfTrips> deleteRentOfTripsByTripId(long id);

    Optional<RentOfTrips> deleteRentOfTripsByRentOfTripId(long id);

    RentOfTrips findRentOfTripsByRentOfTripId(long id);

    RentOfTrips findRentOfTripsByUser_Email(String email);

    List<RentOfTrips> deleteRentOfTripsByCanoeTripCanoeTripId(long id);

    List<RentOfTrips> findDistinctFirstByUserEmailOrderByRentOfTripIdDesc(String email);
}
