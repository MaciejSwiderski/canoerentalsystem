package com.canoerent.service;

import com.canoerent.model.Rent;
import com.canoerent.repository.*;
import com.canoerent.service.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    CanoeRepository canoeRepository;

    @Autowired
    CanoeForTripRepository canoeForTripRepository;

    @Autowired
    RentOfTripRepository rentOfTripRepository;

    @Autowired
    HttpServletRequest request;


    public List<Rent> getRent() {
        return rentRepository.findAllByOrderByRentId();
    }

    public List<Rent> getbyEmail() {
        return rentRepository.findByUserEmailOrderByRentId(request.getUserPrincipal().getName());
    }

    public List<Rent> getbyEmailLast() {
        return rentRepository.findDistinctFirstByUserEmailOrderByRentIdDesc(request.getUserPrincipal().getName());
    }

    @Transactional
    public void deleteRentById(Long id) {
        if (id.equals(null)) {

        }
        rentRepository.deleteRentByRentId(id);
    }


    public Rent findRentById(long id) {
        return rentRepository.findRentByRentId(id);
    }

    public Rent findRentByEmail(String email) {
        return rentRepository.findRentByUser_Email(email);
    }

    @Transactional
    public void deleteRentByUserEmail(String email) {
        rentRepository.deleteRentByUserEmail(email);
    }

    @Transactional
    public void deleteRentByCanoeId(long id) {
        rentRepository.deleteRentByCanoeId(id);
    }


    public Rent save(Rent rent) throws ParseException {
        rent.getUser().setEmail(request.getUserPrincipal().getName());  // adding current user to the rent that is making

        if (rent.getUser().getEmail().equals(userRepository.findUserByEmail(rent.getUser().getEmail()).getEmail())) {
            rent.getUser().setId(userRepository.findUserByEmail(rent.getUser().getEmail()).getId());
            rent.getUser().setName(userRepository.findUserByEmail(rent.getUser().getEmail()).getName());
            rent.getUser().setSurname(userRepository.findUserByEmail(rent.getUser().getEmail()).getSurname());
        }


        if (rent.getCanoe().getCanoeType().equals(canoeRepository.findCanoeByCanoeType(rent.getCanoe().getCanoeType()).get().getCanoeType())) {
            rent.getCanoe().setId(canoeRepository.findCanoeByCanoeType(rent.getCanoe().getCanoeType()).get().getId());
            rent.getCanoe().setPrice(canoeRepository.findCanoeByCanoeType(rent.getCanoe().getCanoeType()).get().getPrice());
        }

        DateHelper.priceCalculation(Optional.of((rent)));

        return rentRepository.save(rent);
    }

    public Optional<Rent> getRentById(long id) {
        return rentRepository.findById(id);
    }

}
