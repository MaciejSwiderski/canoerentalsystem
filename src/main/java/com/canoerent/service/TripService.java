package com.canoerent.service;

import com.canoerent.model.Trip;
import com.canoerent.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public List<Trip>getTrip(){
        return tripRepository.findAll();
    }

    public Trip save(Trip trip){
        return  tripRepository.save(trip);
    }

    public Trip findTripById(long id){
        return tripRepository.findTripById(id);
    }

    @Transactional
    public void deleteTripById(long id){
        tripRepository.deleteTripById(id);
    }
}
