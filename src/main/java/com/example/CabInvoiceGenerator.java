package com.example;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class CabInvoiceGenerator {
    static final double NORMAL_COST_PER_MINUTE = 1.0;
    static final double NORMAL_COST_PER_KM = 10.0;
    static final double NORMAL_MINIMUM_FARE = 5.0;

    static final double PREMIUM_COST_PER_MINUTE = 2.0;
    static final double PREMIUM_COST_PER_KM = 15.0;
    static final double PREMIUM_MINIMUM_FARE = 20.0;

    private Map<String, ArrayList<Ride>> ridesRepo;

    public CabInvoiceGenerator() {
        ridesRepo = new HashMap<>();
    }

    public void addRide(String key, Ride ride) {
        if (!ridesRepo.containsKey(key)) {
            ridesRepo.put(key, new ArrayList<Ride>());
        }
        ridesRepo.get(key).add(ride);
    }

    public double calculateNormalFare(double distance, double time) {
        double totalFare = distance * NORMAL_COST_PER_KM + time * NORMAL_COST_PER_MINUTE;
        return Math.max(totalFare, NORMAL_MINIMUM_FARE);
    }

    public double calculatePremiumFare(double distance, double time) {
        double totalFare = distance * PREMIUM_COST_PER_KM + time * PREMIUM_COST_PER_MINUTE;
        return Math.max(totalFare, PREMIUM_MINIMUM_FARE);
    }

    public double aggregateFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            if (ride.type == "normal")
                totalFare += calculateNormalFare(ride.distance, ride.time);
            else
                totalFare += calculatePremiumFare(ride.distance, ride.time);
        }
        return totalFare;
    }

    public InvoiceReport getInvoiceReport(Ride[] rides) {
        int totalRides = rides.length;

        double totalFare = 0.0;
        for (Ride ride : rides) {
            if (ride.type == "normal")
                totalFare += calculateNormalFare(ride.distance, ride.time);
            else
                totalFare += calculatePremiumFare(ride.distance, ride.time);
        }

        return new InvoiceReport(totalRides, totalFare);
    }

    public InvoiceReport getInvoiceReport(String userID) {
        if (!ridesRepo.containsKey(userID)) {
            return new InvoiceReport(0, 0);
        }

        ArrayList<Ride> rides = ridesRepo.get(userID);

        int totalRides = rides.size();

        double totalFare = 0.0;
        for (Ride ride : rides) {
            if (ride.type == "normal")
                totalFare += calculateNormalFare(ride.distance, ride.time);
            else
                totalFare += calculatePremiumFare(ride.distance, ride.time);
        }

        return new InvoiceReport(totalRides, totalFare);
    }
}