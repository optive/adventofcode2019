package day_06;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UniversalOrbitMapTests {

    private UniversalOrbitMap uom;

    @Test
    public void testOrbitCount() {
        final List<String> input = Arrays.asList(
                "COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L");
        uom = new UniversalOrbitMap(input);
        Assert.assertEquals(42, uom.calculateTotalOrbits());
    }

    @Test
    public void testOrbitalTransfers() {
        final List<String> input = Arrays.asList(
                "COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN");
        uom = new UniversalOrbitMap(input);
        Assert.assertEquals(4, uom.calculateOrbitalTransfers());
    }
}
