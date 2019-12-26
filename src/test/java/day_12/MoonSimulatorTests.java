package day_12;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MoonSimulatorTests {

    public MoonSimulator moonSimulator;

    @Test
    public void testCaseOne() {
        moonSimulator = new MoonSimulator(Arrays.asList(
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)));
        moonSimulator.iterate(10);
        Assert.assertEquals(179, moonSimulator.calculateEnergy());
    }

    @Test
    public void testCaseTwo() {
        moonSimulator = new MoonSimulator(Arrays.asList(
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)));
        moonSimulator.iterate(100);
        Assert.assertEquals(1940, moonSimulator.calculateEnergy());
    }

    @Test
    public void testCaseThree() {
        moonSimulator = new MoonSimulator(Arrays.asList(
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)));
        Assert.assertEquals(2772, moonSimulator.findStepsUntilRepetition());
    }

    @Test
    public void testCaseFour() {
        moonSimulator = new MoonSimulator(Arrays.asList(
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)));
        Assert.assertEquals(4686774924L, moonSimulator.findStepsUntilRepetition());
    }
}
