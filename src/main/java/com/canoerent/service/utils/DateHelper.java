package com.canoerent.service.utils;

import com.canoerent.model.Rent;
import com.canoerent.model.RentOfTrips;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DateHelper {

    public static long days;

    public static long getDifferenceDays(Date startDate, Date endDate) {
        long differenceDays = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(differenceDays, TimeUnit.MILLISECONDS);
    }

    public static Optional<Rent> priceCalculation(Optional<Rent> rent) throws ParseException {

        rent.get().setRentPrice((rent.get().getHours() * rent.get().getCanoe().getPrice()) * rent.get().getCanoeAmount());

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = localDateTime.format(formatter);
        rent.get().setCreationTs(formattedDate);

        return rent;
    }

    public static Optional<RentOfTrips> priceCalculationRentOfTrips(Optional<RentOfTrips> rentOfTrips) throws ParseException {
        String startDate = rentOfTrips.get().getStartDate();
        String endDate = rentOfTrips.get().getEndDate();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        days = DateHelper.getDifferenceDays(simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate));

        if (days == 0) {
            days = 1;
        }

        rentOfTrips.get().setRentOfTripPrice(
                (days * rentOfTrips.get().getCanoeTrip().getCanoeForTripPrice() * rentOfTrips.get().getCanoeTripAmount())
                        + (rentOfTrips.get().getTrip().getTripPrice() * rentOfTrips.get().getTripAmount()));


        if (days < 0) {
            rentOfTrips.get().setRentOfTripPrice(0);
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = localDateTime.format(formatter);
        rentOfTrips.get().setRentOfTripCreationTs(formattedDate);

        return rentOfTrips;
    }
}
