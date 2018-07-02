package com.canoerent.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trips")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String location;
    @Column(nullable = true)
    private double tripPrice;

    @OneToMany(cascade = CascadeType.REFRESH)
    Set<Rent> userSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }

    public Set<Rent> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<Rent> userSet) {
        this.userSet = userSet;
    }

    public Trip(String location, double tripPrice, Set<Rent> userSet) {
        this.location = location;
        this.tripPrice = tripPrice;
        this.userSet = userSet;
    }

    public Trip() {
    }
}








