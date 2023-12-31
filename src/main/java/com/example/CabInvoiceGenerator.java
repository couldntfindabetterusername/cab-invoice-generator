package com.example;

public class CabInvoiceGenerator {
    static final double COST_PER_MINUTE = 1.0;
    static final double COST_PER_KM = 10.0;
    static final double MINIMUM_FARE = 5.0;

    public double calculateFare(double distance, double time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MINUTE;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public double aggregateFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }

    public InvoiceReport getInvoiceReport(Ride[] rides) {
        int totalRides = rides.length;

        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }

        return new InvoiceReport(totalRides, totalFare);
    }
}