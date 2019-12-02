package day_01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FuelCalculator {
    public long calculateFuel(final long mass) {
        return mass / 3 - 2;
    }

    public long calculateTotalFuel(final long mass) {
        long totalFuel = 0;
        long currentMass = mass;
        while(calculateFuel(currentMass) > 0) {
            final long currentFuel = calculateFuel(currentMass);
            totalFuel += currentFuel;
            currentMass = currentFuel;
        }
        return totalFuel;
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/01_input.txt"), "UTF-8");

        final FuelCalculator fc = new FuelCalculator();
        long fuelRequiredPartOne = 0;
        for (final String line : lines) {
            fuelRequiredPartOne += fc.calculateFuel(Long.valueOf(line));
        }

        System.out.println(fuelRequiredPartOne);

        long fuelRequiredPartTwo = 0;
        for (final String line : lines) {
            fuelRequiredPartTwo += fc.calculateTotalFuel(Long.valueOf(line));
        }

        System.out.println(fuelRequiredPartTwo);
    }
}
