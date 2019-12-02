package day_02;

import org.junit.Assert;
import org.junit.Test;

public class IntcodeExecutorTests {
    private IntcodeExecutor ie = new IntcodeExecutor();

    @Test
    public void testCaseOne() {
        final int[] input = new int[]{1, 0, 0, 0, 99};
        ie.executeProgram(input);
        Assert.assertArrayEquals(new int[]{2, 0, 0, 0, 99}, input);
    }

    @Test
    public void testCaseTwo() {
        final int[] input = new int[]{2,3,0,3,99};
        ie.executeProgram(input);
        Assert.assertArrayEquals(new int[]{2,3,0,6,99}, input);
    }

    @Test
    public void testCaseThree() {
        final int[] input = new int[]{2,4,4,5,99,0};
        ie.executeProgram(input);
        Assert.assertArrayEquals(new int[]{2,4,4,5,99,9801}, input);
    }

    @Test
    public void testCaseFour() {
        final int[] input = new int[]{1,1,1,4,99,5,6,0,99};
        ie.executeProgram(input);
        Assert.assertArrayEquals(new int[]{30,1,1,4,2,5,6,0,99}, input);
    }
}
