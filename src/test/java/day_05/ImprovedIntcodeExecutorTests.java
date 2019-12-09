package day_05;

import org.junit.Assert;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.util.*;

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

    @Test
    public void testCaseSeven() {
        final int[] program = new int[]{109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99};
        final int[] expectedOutput = Arrays.copyOf(program, program.length);
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(), program, 1000);
        ie.executeProgram();
        final List<Integer> output = new ArrayList<>(ie.getOutput());
        Assert.assertArrayEquals(expectedOutput, output.stream().mapToInt(i -> i).toArray());
    }

    @Test
    public void testCaseEight() {
        final int[] program = new int[]{1102,34915192,34915192,7,4,7,99,0};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(), program);
        ie.executeProgram();
        Assert.assertEquals(16, ie.getOutput().remove().toString().length());
    }

    @Test
    public void testCaseNine() {
        final int[] program = new int[]{104,1125899906842624,99};
        ie = new ImprovedIntcodeExecutor(new LinkedList<>(), program);
        ie.executeProgram();
        Assert.assertEquals(1125899906842624, ie.getOutput().remove());
    }
}
