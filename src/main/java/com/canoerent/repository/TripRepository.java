package com.canoerent.repository;

import com.canoerent.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {

    Optional<Trip> findTripByLocation(String trip);

    Trip findTripById(long id);

    Optional<Trip> deleteTripById(long id);

}
