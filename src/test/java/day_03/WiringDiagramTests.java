package day_03;

import org.junit.Assert;
import org.junit.Test;

public class WiringDiagramTests {

    private WiringDiagram wd;

    @Test
    public void testCaseOne() {
        wd = new WiringDiagram("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83");
        Assert.assertEquals(159, wd.calculateCrossingDistance());
        Assert.assertEquals(610, wd.shortestSteps());
    }

    @Test
    public void testCaseTwo() {
        wd = new WiringDiagram("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
        Assert.assertEquals(135, wd.calculateCrossingDistance());
        Assert.assertEquals(410, wd.shortestSteps());
    }

    @Test
    public void testCaseThree() {
        wd = new WiringDiagram("R8,U5,L5,D3", "U7,R6,D4,L4");
        Assert.assertEquals(6, wd.calculateCrossingDistance());
        Assert.assertEquals(30, wd.shortestSteps());
    }
}
