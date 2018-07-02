package com.canoerent.service;


import com.canoerent.model.Rent;
import com.canoerent.model.RentOfTrips;
import com.canoerent.repository.*;
import com.canoerent.service.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class RentOfTripsService {


    @Autowired
    RentOfTripRepository rentOfTripRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    CanoeRepository canoeRepository;

    @Autowired
    CanoeForTripRepository canoeForTripRepository;

    @Autowired
    HttpServletRequest request;


    public List<RentOfTrips> getRentOfTrips() {
        return rentOfTripRepository.findAllByOrderByRentOfTripId();
    }

    public List<RentOfTrips> getbyEmail() {
        return rentOfTripRepository.findByUserEmailOrderByRentOfTripId(request.getUserPrincipal().getName());
    }

    public List<RentOfTrips> getbyEmailLast() {
        return rentOfTripRepository.findDistinctFirstByUserEmailOrderByRentOfTripIdDesc(request.getUserPrincipal().getName());
    }

    @Transactional
    public void deleteRentOfTripsById(Long id) {
        if (id.equals(null)) {

        }
        rentOfTripRepository.deleteRentOfTripsByRentOfTripId(id);
    }


    public RentOfTrips findRentOfTripsById(long id) {
        return rentOfTripRepository.findRentOfTripsByRentOfTripId(id);
    }

    public RentOfTrips findRentOfTripsByEmail(String email) {
        return rentOfTripRepository.findRentOfTripsByUser_Email(email);
    }

    @Transactional
    public void deleteRentOfTripsByUserEmail(String email) {
        rentOfTripRepository.deleteRentOfTripsByUserEmail(email);
    }


    @Transactional
    public void deleteRentOfTripsByTripId(long id) {
        rentOfTripRepository.deleteRentOfTripsByTripId(id);
    }

    @Transactional
    public void deleteRentOfTripsByCanoeId(long id) {
        rentOfTripRepository.deleteRentOfTripsByCanoeTripCanoeTripId(id);
    }

    @Transactional
    public void deleteRentOfTripByCanoeTripId(long id) {
        rentOfTripRepository.deleteRentOfTripsByCanoeTripCanoeTripId(id);
    }


    public RentOfTrips save(RentOfTrips rentOfTrips) throws ParseException {
        rentOfTrips.getUser().setEmail(request.getUserPrincipal().getName());  // adding current user to the rent that is making

        if (rentOfTrips.getUser().getEmail().equals(userRepository.findUserByEmail(rentOfTrips.getUser().getEmail()).getEmail())) {
            rentOfTrips.getUser().setId(userRepository.findUserByEmail(rentOfTrips.getUser().getEmail()).getId());
            rentOfTrips.getUser().setName(userRepository.findUserByEmail(rentOfTrips.getUser().getEmail()).getName());
            rentOfTrips.getUser().setSurname(userRepository.findUserByEmail(rentOfTrips.getUser().getEmail()).getSurname());
        }


        if (rentOfTrips.getCanoeTrip().getCanoeTripType().equals(canoeForTripRepository.findCanoeTripsByCanoeTripType(rentOfTrips.getCanoeTrip().getCanoeTripType()).get().getCanoeTripType())) {
            rentOfTrips.getCanoeTrip().setCanoeTripId(canoeForTripRepository.findCanoeTripsByCanoeTripType(rentOfTrips.getCanoeTrip().getCanoeTripType()).get().getCanoeTripId());
            rentOfTrips.getCanoeTrip().setCanoeForTripPrice(canoeForTripRepository.findCanoeTripsByCanoeTripType(rentOfTrips.getCanoeTrip().getCanoeTripType()).get().getCanoeForTripPrice());
        }


        if (rentOfTrips.getTrip().getLocation().equals(tripRepository.findTripByLocation(rentOfTrips.getTrip().getLocation()).get().getLocation())) {
            rentOfTrips.getTrip().setId(tripRepository.findTripByLocation(rentOfTrips.getTrip().getLocation()).get().getId());
            rentOfTrips.getTrip().setTripPrice(tripRepository.findTripByLocation(rentOfTrips.getTrip().getLocation()).get().getTripPrice());
        }

        rentOfTrips.setStartDate(rentOfTrips.getStartDate());
        rentOfTrips.setEndDate(rentOfTrips.getEndDate());

        DateHelper.priceCalculationRentOfTrips(Optional.of((rentOfTrips)));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.parse(rentOfTrips.getStartDate());
        simpleDateFormat.parse(rentOfTrips.getEndDate());

        DateHelper.getDifferenceDays(simpleDateFormat.parse(rentOfTrips.getStartDate()), simpleDateFormat.parse(rentOfTrips.getEndDate()));

        return rentOfTripRepository.save(rentOfTrips);
    }

    public Optional<RentOfTrips> getRentOfTripById(long id) {
        return rentOfTripRepository.findById(id);
    }
}
