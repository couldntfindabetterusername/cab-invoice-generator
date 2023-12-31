package com.example;

public class InvoiceReport {
    public int ridesCount;
    public double totalFare;
    public double averageFare;

    public InvoiceReport(int ridesCount, double totalFare) {
        this.ridesCount = ridesCount;
        this.totalFare = totalFare;
        this.averageFare = totalFare / ridesCount;
    }
}
