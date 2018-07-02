package com.canoerent.controller;


import com.canoerent.dto.UserDTO;
import com.canoerent.model.Canoe;
import com.canoerent.model.Rent;
import com.canoerent.model.Trip;
import com.canoerent.model.User;
import com.canoerent.repository.RentRepository;
import com.canoerent.service.CanoeService;
import com.canoerent.service.RentService;
import com.canoerent.service.TripService;
import com.canoerent.service.UserService;
import com.canoerent.service.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
public class Controller {

    @Autowired
    private CanoeService canoeService;

    @Autowired
    private RentService rentService;

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @Autowired
    public RentRepository rentRepository;

    @RequestMapping(value = "/AddUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/User", method = RequestMethod.GET)
    public List<User> showUser() {
        return userService.getUser();
    }

    @RequestMapping(value = "/AddCanoe", method = RequestMethod.POST)
    public Canoe addCanoe(@RequestBody Canoe canoe) {
        return canoeService.save(canoe);
    }

    @RequestMapping(value = "/Canoe", method = RequestMethod.GET)
    public List<Canoe> showCanoe() {
        return canoeService.getCanoe();
    }

    @RequestMapping(value = "/AddTrip", method = RequestMethod.POST)
    public Trip addTrip(@RequestBody Trip trip) {
        return tripService.save(trip);
    }

    @RequestMapping(value = "Trip", method = RequestMethod.GET)
    public List<Trip> showTrip() {
        return tripService.getTrip();
    }

    @RequestMapping(value = "/AddRent", method = RequestMethod.POST)
    public Rent addRent(@RequestBody Rent rent) throws ParseException {
        return rentService.save(rent);
    }

    @RequestMapping(value = "/Rent", method = RequestMethod.GET)
    public List<Rent> showRent() {
        return rentService.getRent();
    }

    @RequestMapping(value = "/Rent/{id}", method = RequestMethod.GET)
    public Optional<Rent> showRentById(@PathVariable long id) {
        return rentService.getRentById(id);
    }

//    @RequestMapping(value = "/Rent/{id}",method = RequestMethod.DELETE)
//    public void deleteByEmail(@PathVariable long id){
//         rentService.deleteById(7l);
//    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @RequestMapping(value = "/Rent/up/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Rent updateRentById(@PathVariable long id, @RequestBody Optional<Rent> rent) throws ParseException {

        DateHelper.priceCalculation(rent);

        return rentService.save(rent.get());
    }
}
