package com.canoerent.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "rent")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Canoe canoe;


    private double rentPrice;
    private double canoeAmount;
    private double hours;
    private String rentPerHourDate;
    private String rentPerHourTime;
    private String creationTs;

    public String getRentPerHourTime() {
        return rentPerHourTime;
    }

    public void setRentPerHourTime(String rentPerHourTime) {
        this.rentPerHourTime = rentPerHourTime;
    }

    public String getCreationTs() {
        return creationTs;
    }

    public void setCreationTs(String creationTs) {
        this.creationTs = creationTs;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getCanoeAmount() {
        return canoeAmount;
    }

    public void setCanoeAmount(double canoeAmount) {
        this.canoeAmount = canoeAmount;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Canoe getCanoe() {
        return canoe;
    }

    public void setCanoe(Canoe canoe) {
        this.canoe = canoe;
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }


    public String getRentPerHourDate() {
        return rentPerHourDate;
    }

    public void setRentPerHourDate(String rentPerHourDate) {
        this.rentPerHourDate = rentPerHourDate;
    }


    public Rent(User user, Canoe canoe,  double rentPrice, double canoeAmount, double hours, String rentPerHourDate, String creationTs , String rentPerHourTime) {
        this.user = user;
        this.canoe = canoe;
        this.rentPrice = rentPrice;
        this.canoeAmount = canoeAmount;
        this.hours=hours;
        this.rentPerHourDate=rentPerHourDate;
        this.creationTs=creationTs;
        this.rentPerHourTime=rentPerHourTime;
    }

    public Rent() {
    }
}
