import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.CabInvoiceGenerator;
import com.example.Ride;

public class TestCabInvoice {
    public static CabInvoiceGenerator cabInvoiceGenerator;

    @BeforeAll
    public static void setup() {
        cabInvoiceGenerator = new CabInvoiceGenerator();
    }

    @Test
    public void testCalculateFare() {
        double fare = cabInvoiceGenerator.calculateFare(5, 15);
        assertEquals(65.0, fare);
    }

    @Test
    public void testMinimumFare() {
        double fare = cabInvoiceGenerator.calculateFare(0.2, 2);
        assertEquals(5.0, fare);
    }

    @Test
    public void testAggregateFare() {
        double fare = cabInvoiceGenerator.aggregateFare(new Ride[] { new Ride(5, 15), new Ride(10, 30) });
        assertEquals(195.0, fare);
    }
}