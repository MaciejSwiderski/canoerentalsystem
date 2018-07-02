package com.canoerent.service.utils;

import com.canoerent.model.Rent;
import com.canoerent.model.RentOfTrips;

import static com.canoerent.service.utils.DateHelper.days;

public class EmailContent {

    public static String emailContentToSend(Rent rent) {

        String emailContent = "Welcome " + rent.getUser().getName() + " " + rent.getUser().getSurname()
                + "\nYou have ordered below services:\n"
                + "\nORDER PLACED:    " + rent.getCreationTs()
                + "\nCANOES/TRIP PER DAYS ORDER SUMMARY:"
                + "\n--------------------------------------------------"
                + "\nCANOES PER HOURS ORDER SUMMARY: "
                + "\n--------------------------------------------------"
                + "\nRENT DAY:         " + rent.getRentPerHourDate()
                + "\nBOOKED CANOE:     " + rent.getCanoe().getCanoeType()
                + "\nCANOE PRICE PER HOUR:     " + rent.getCanoe().getPrice() + " zł"
                + "\nAMOUNT OF CANOES:      " + rent.getCanoeAmount()
                + "\nHOURS:     " + rent.getHours()
                + "\nCANOE RENT PER HOUR COST:     " + (rent.getHours() * rent.getCanoe().getPrice()
                * rent.getCanoeAmount()) + " zł"
                + "\n==========================="
                + "\nTOTAL COST:     " + rent.getRentPrice() + " zł\n"
                + "\nPLEASE MAKE A PAYMENT:"
                + "\nREFER TO YOUR ORDER NUMBER:      " + rent.getRentId()
                + "\nhttps://www.ipko.pl/"
                + "\nhttps://www.payu.pl/"
                + "\nhttps://www.paypal.com/pl/";
        return emailContent;
    }

    public static String emailContentToSendRentOfTrips(RentOfTrips rentOfTrips) {

        String emailContent = "Welcome " + rentOfTrips.getUser().getName() + " " + rentOfTrips.getUser().getSurname()
                + "\nYou have ordered below services:\n"
                + "\nORDER PLACED:    " + rentOfTrips.getRentOfTripCreationTs()
                + "\nCANOES/TRIP PER DAYS ORDER SUMMARY:"
                + "\n--------------------------------------------------"
                + "\nSTART DATE:     " + rentOfTrips.getStartDate()
                + "\nEND DATE:     " + rentOfTrips.getEndDate()
                + "\nBOOKED CANOE:     " + rentOfTrips.getCanoeTrip().getCanoeTripType()
                + "\nCANOE PRICE PER DAY:     " + rentOfTrips.getCanoeTrip().getCanoeForTripPrice() + " zł"
                + "\nAMOUNT OF CANOES PER DAY:     " + rentOfTrips.getCanoeTripAmount()
                + "\nTRIP:     " + rentOfTrips.getTrip().getLocation()
                + "\nAMOUNT OF TRIPS:     " + rentOfTrips.getTripAmount()
                + "\nTRIP PRICE:     " + rentOfTrips.getTrip().getTripPrice() + " zł"
                + "\nCANOES RENT PER DAYS COST: " + rentOfTrips.getCanoeTrip().getCanoeForTripPrice()
                * days * rentOfTrips.getCanoeTripAmount() + " zł" + "\n"
                + "\n==========================="
                + "\nTOTAL COST:     " + rentOfTrips.getRentOfTripPrice() + " zł\n"
                + "\nPLEASE MAKE A PAYMENT:"
                + "\nREFER TO YOUR ORDER NUMBER:      " + rentOfTrips.getRentOfTripPrice()
                + "\nhttps://www.ipko.pl/"
                + "\nhttps://www.payu.pl/"
                + "\nhttps://www.paypal.com/pl/";

        return emailContent;
    }
}
