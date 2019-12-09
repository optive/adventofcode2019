package day_05;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class ImprovedIntcodeExecutorTests {

    private ImprovedIntcodeExecutor ie;

    @Test
    public void testCaseOne() {
        final int[] program = new int[]{1, 0, 0, 0, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new int[]{2, 0, 0, 0, 99}, program);
    }

    @Test
    public void testCaseTwo() {
        final int[] program = new int[]{2, 3, 0, 3, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new int[]{2, 3, 0, 6, 99}, program);
    }

    @Test
    public void testCaseThree() {
        final int[] program = new int[]{2, 4, 4, 5, 99, 0};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new int[]{2, 4, 4, 5, 99, 9801}, program);
    }

    @Test
    public void testCaseFour() {
        final int[] program = new int[]{1, 1, 1, 4, 99, 5, 6, 0, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new int[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, program);
    }

    @Test
    public void testCaseFive() {
        final int[] program = new int[]{3, 0, 4, 0, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(123)), program);
        ie.executeProgram();
        final int output = ie.getOutput().remove();
        Assert.assertEquals(123, output);
    }

    @Test
    public void testCaseSix() {
        final int[] program = new int[]{1002, 4, 3, 4, 33};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new int[]{1002, 4, 3, 4, 99}, program);
    }
}
