import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.CabInvoiceGenerator;
import com.example.InvoiceReport;
import com.example.Ride;

public class TestCabInvoice {
    public static CabInvoiceGenerator cabInvoiceGenerator;

    @BeforeAll
    public static void setup() {
        cabInvoiceGenerator = new CabInvoiceGenerator();
    }

    @Test
    public void testCalculateFare() {
        double fare = cabInvoiceGenerator.calculateNormalFare(5, 15);
        assertEquals(65.0, fare);
    }

    @Test
    public void testMinimumFare() {
        double fare = cabInvoiceGenerator.calculateNormalFare(0.2, 2);
        assertEquals(5.0, fare);
    }

    @Test
    public void testAggregateFare() {
        double fare = cabInvoiceGenerator
                .aggregateFare(new Ride[] { new Ride(5, 15, "normal"), new Ride(10, 30, "normal") });
        assertEquals(195.0, fare);
    }

    @Test
    public void testAverageFare() {
        Ride[] rides = {
                new Ride(2.0, 5, "normal"),
                new Ride(1.0, 1, "normal"),
                new Ride(1.0, 2, "normal"),
        };

        InvoiceReport report = cabInvoiceGenerator.getInvoiceReport(rides);
        InvoiceReport expectedReport = new InvoiceReport(3, 48);

        assertEquals(expectedReport.ridesCount, report.ridesCount);
        assertEquals(expectedReport.totalFare, report.totalFare);
        assertEquals(expectedReport.averageFare, report.averageFare);
    }

    @Test
    public void testInvoiceReportByUser() {
        cabInvoiceGenerator.addRide("user1", new Ride(2.0, 5, "normal"));
        cabInvoiceGenerator.addRide("user1", new Ride(1.0, 1, "normal"));
        cabInvoiceGenerator.addRide("user2", new Ride(1.0, 2, "normal"));

        InvoiceReport user1Report = cabInvoiceGenerator.getInvoiceReport("user1");
        InvoiceReport user1ExpectedReport = new InvoiceReport(2, 36);

        InvoiceReport user2Report = cabInvoiceGenerator.getInvoiceReport("user2");
        InvoiceReport user2ExpectedReport = new InvoiceReport(1, 12);

        // user 1 check
        assertEquals(user1ExpectedReport.ridesCount, user1Report.ridesCount);
        assertEquals(user1ExpectedReport.totalFare, user1Report.totalFare);
        assertEquals(user1ExpectedReport.averageFare, user1Report.averageFare);

        // user 2 check
        assertEquals(user2ExpectedReport.ridesCount, user2Report.ridesCount);
        assertEquals(user2ExpectedReport.totalFare, user2Report.totalFare);
        assertEquals(user2ExpectedReport.averageFare, user2Report.averageFare);
    }

    @Test
    public void testInvoiceReportForPremiumRides() {
        cabInvoiceGenerator.addRide("user3", new Ride(2.0, 5, "premium"));
        cabInvoiceGenerator.addRide("user3", new Ride(1.0, 1, "normal"));
        cabInvoiceGenerator.addRide("user3", new Ride(1.0, 2, "premium"));

        InvoiceReport user3Report = cabInvoiceGenerator.getInvoiceReport("user3");
        InvoiceReport user3ExpectedReport = new InvoiceReport(3, 71);

        assertEquals(user3ExpectedReport.ridesCount, user3Report.ridesCount);
        assertEquals(user3ExpectedReport.totalFare, user3Report.totalFare);
        assertEquals(user3ExpectedReport.averageFare, user3Report.averageFare);
    }
}