package day_05;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ImprovedIntcodeExecutorTests {

    private ImprovedIntcodeExecutor ie;

    @Test
    public void testCaseOne() {
        final long[] program = new long[]{1, 0, 0, 0, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new long[]{2, 0, 0, 0, 99}, program);
    }

    @Test
    public void testCaseTwo() {
        final long[] program = new long[]{2, 3, 0, 3, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new long[]{2, 3, 0, 6, 99}, program);
    }

    @Test
    public void testCaseThree() {
        final long[] program = new long[]{2, 4, 4, 5, 99, 0};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new long[]{2, 4, 4, 5, 99, 9801}, program);
    }

    @Test
    public void testCaseFour() {
        final long[] program = new long[]{1, 1, 1, 4, 99, 5, 6, 0, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new long[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, program);
    }

    @Test
    public void testCaseFive() {
        final long[] program = new long[]{3, 0, 4, 0, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(123)), program);
        ie.executeProgram();
        final long output = ie.getOutput().remove();
        Assert.assertEquals(123, output);
    }

    @Test
    public void testCaseSix() {
        final long[] program = new long[]{1002, 4, 3, 4, 33};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(0)), program);
        ie.executeProgram();
        Assert.assertArrayEquals(new long[]{1002, 4, 3, 4, 99}, program);
    }

    @Test
    public void testCaseSeven() {
        final long[] program = new long[]{109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99};
        final long[] expectedOutput = Arrays.copyOf(program, program.length);
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(), program, 1000);
        ie.executeProgram();
        final List<Long> output = new ArrayList<>(ie.getOutput());
        Assert.assertArrayEquals(expectedOutput, output.stream().mapToLong(i -> i).toArray());
    }

    @Test
    public void testCaseEight() {
        final long[] program = new long[]{1102, 34915192, 34915192, 7, 4, 7, 99, 0};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(), program);
        ie.executeProgram();
        Assert.assertEquals(16, ie.getOutput().remove().toString().length());
    }

    @Test
    public void testCaseNine() {
        final long[] program = new long[]{104, 1125899906842624L, 99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(), program);
        ie.executeProgram();
        final long output = ie.getOutput().remove();
        Assert.assertEquals(1125899906842624L, output);
    }
}
