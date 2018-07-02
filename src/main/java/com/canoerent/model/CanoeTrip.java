package com.canoerent.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "canoeTrip")
public class CanoeTrip {

//@JsonInclude(JsonInclude.Include.NON_NULL)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long canoeTripId;
    private String canoeTripType;
    private double canoeForTripPrice;

    @OneToMany(cascade = CascadeType.REFRESH)
    Set<RentOfTrips> rentSetOfTrips;


    public CanoeTrip(String canoeTripType, double canoeForTripPrice, Set<RentOfTrips> rentSetOfTrips) {
        this.canoeTripType = canoeTripType;
        this.canoeForTripPrice = canoeForTripPrice;
        this.rentSetOfTrips = rentSetOfTrips;
    }

    public Long getCanoeTripId() {
        return canoeTripId;
    }

    public void setCanoeTripId(Long canoeTripId) {
        this.canoeTripId = canoeTripId;
    }

    public String getCanoeTripType() {
        return canoeTripType;
    }

    public void setCanoeTripType(String canoeTripType) {
        this.canoeTripType = canoeTripType;
    }

    public double getCanoeForTripPrice() {
        return canoeForTripPrice;
    }

    public void setCanoeForTripPrice(double canoeForTripPrice) {
        this.canoeForTripPrice = canoeForTripPrice;
    }

    public Set<RentOfTrips> getRentSetOfTrips() {
        return rentSetOfTrips;
    }

    public void setRentSetOfTrips(Set<RentOfTrips> rentSetOfTrips) {
        this.rentSetOfTrips = rentSetOfTrips;
    }

    public CanoeTrip() {

    }
}
