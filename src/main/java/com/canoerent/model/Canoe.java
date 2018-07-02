package com.canoerent.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "canoe")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Canoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String canoeType;
    private double price;

    @OneToMany(cascade = CascadeType.REFRESH)
    Set<Rent> rentSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCanoeType() {
        return canoeType;
    }

    public void setCanoeType(String canoeType) {
        this.canoeType = canoeType;
    }

    public Set<Rent> getRentSet() {
        return rentSet;
    }

    public void setRentSet(Set<Rent> rentSet) {
        this.rentSet = rentSet;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Canoe(String canoeType, double price, double canoeForTripPrice, Set<Rent> rentSet) {
        this.canoeType = canoeType;
        this.price = price;
        this.rentSet = rentSet;
    }

    public Canoe() {

    }
}
