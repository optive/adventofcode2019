package day_09;

import day_05.ImprovedIntcodeExecutor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class SensorBooster {

    public static void main(String[] args) throws IOException {
        // Part One
        final String program = FileUtils.readFileToString(new File("src/main/resources/09_input.txt"), "UTF-8");
        final ImprovedIntcodeExecutor ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(1)), program, 10000);
        ie.executeProgram();
        System.out.println(ie.getOutput().remove());

        // Part Two
        final ImprovedIntcodeExecutor ie2 = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(2)), program, 10000);
        ie2.executeProgram();
        System.out.println(ie2.getOutput().remove());
    }
}
