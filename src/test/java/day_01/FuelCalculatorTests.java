package day_01;

import org.junit.Assert;
import org.junit.Test;

public class FuelCalculatorTests {

    public FuelCalculator fuelCalculator = new FuelCalculator();

    @Test
    public void testCaseOne() {
        Assert.assertEquals(2, fuelCalculator.calculateFuel(12));
    }

    @Test
    public void testCaseTwo() {
        Assert.assertEquals(2, fuelCalculator.calculateFuel(14));
    }

    @Test
    public void testCaseThree() {
        Assert.assertEquals(654, fuelCalculator.calculateFuel(1969));
    }

    @Test
    public void testCaseFour() {
        Assert.assertEquals(33583, fuelCalculator.calculateFuel(100756));
    }

    @Test
    public void testCaseFive() {
        Assert.assertEquals(2, fuelCalculator.calculateTotalFuel(14));
    }

    @Test
    public void testCaseSix() {
        Assert.assertEquals(966, fuelCalculator.calculateTotalFuel(1969));
    }

    @Test
    public void testCaseSeven() {
        Assert.assertEquals(50346, fuelCalculator.calculateTotalFuel(100756));
    }


}
