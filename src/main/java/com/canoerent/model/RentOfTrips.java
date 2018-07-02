package com.canoerent.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name = "rentOfTrips")
public class RentOfTrips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentOfTripId;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Trip trip;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private  CanoeTrip canoeTrip;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String endDate;

    private double rentOfTripPrice;

    private double canoeTripAmount;
    private double tripAmount;

    private String rentOfTripCreationTs;

    public Long getRentOfTripId() {
        return rentOfTripId;
    }

    public void setRentOfTripId(Long rentOfTripId) {
        this.rentOfTripId = rentOfTripId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public CanoeTrip getCanoeTrip() {
        return canoeTrip;
    }

    public void setCanoeTrip(CanoeTrip canoeTrip) {
        this.canoeTrip = canoeTrip;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getRentOfTripPrice() {
        return rentOfTripPrice;
    }

    public void setRentOfTripPrice(double rentOfTripPrice) {
        this.rentOfTripPrice = rentOfTripPrice;
    }

    public double getCanoeTripAmount() {
        return canoeTripAmount;
    }

    public void setCanoeTripAmount(double canoeTripAmount) {
        this.canoeTripAmount = canoeTripAmount;
    }

    public double getTripAmount() {
        return tripAmount;
    }

    public void setTripAmount(double tripAmount) {
        this.tripAmount = tripAmount;
    }

    public String getRentOfTripCreationTs() {
        return rentOfTripCreationTs;
    }

    public void setRentOfTripCreationTs(String rentOfTripCreationTs) {
        this.rentOfTripCreationTs = rentOfTripCreationTs;
    }

    public RentOfTrips(User user, Trip trip, CanoeTrip canoeTrip, String startDate, String endDate, double rentOfTripPrice, double canoeTripAmount, double tripAmount, String rentOfTripCreationTs) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.trip = trip;
        this.canoeTrip = canoeTrip;
        this.canoeTripAmount = canoeTripAmount;
        this.tripAmount = tripAmount;
        this.rentOfTripCreationTs = rentOfTripCreationTs;
        this.rentOfTripPrice = rentOfTripPrice;
    }

    public RentOfTrips(){
    }
}
