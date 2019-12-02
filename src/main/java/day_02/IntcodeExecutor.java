package day_02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class IntcodeExecutor {
    public void excuteProgram(final String input) {
        final String[] values = input.split(",");
        executeProgram(Arrays.asList(values).stream().mapToInt(Integer::parseInt).toArray());
    }

    public void executeProgram(final int[] input) {
        int position = 0;
        boolean executing = true;

        while (executing) {
            executing = readOpcode(position, input);
            position += 4;
        }
    }

    private boolean readOpcode(final int startIndex, final int[] input) {
        final int instruction = input[startIndex];
        switch (instruction) {
            case 1:
                executeAddition(input, input[startIndex + 1], input[startIndex + 2], input[startIndex + 3]);
                break;
            case 2:
                executeMultiplication(input, input[startIndex + 1], input[startIndex + 2], input[startIndex + 3]);
                break;
            case 99:
                return false;
            default:
                System.out.println("Uh oh");
                break;
        }
        return true;
    }

    private void executeAddition(final int[] input, final int num1, final int num2, final int destination) {
        input[destination] = input[num1] + input[num2];
    }

    private void executeMultiplication(final int[] input, final int num1, final int num2, final int destination) {
        input[destination] = input[num1] * input[num2];
    }

    public static void main(String[] args) throws IOException {
        // Part One
        final String input = FileUtils.readFileToString(new File("src/main/resources/02_input.txt"), "UTF-8");
        final int[] inputArray = Arrays.asList(input.trim().split(",")).stream().mapToInt(Integer::parseInt).toArray();
        final int[] originalInput = inputArray.clone();
        inputArray[1] = 12;
        inputArray[2] = 2;

        final IntcodeExecutor ie = new IntcodeExecutor();
        ie.executeProgram(inputArray);

        System.out.println(inputArray[0]);

        // Part Two
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                final int[] memory = originalInput.clone();
                memory[1] = i;
                memory[2] = j;
                ie.executeProgram(memory);
                if (memory[0] == 19690720) {
                    System.out.println(100*i + j);
                }
            }
        }

    }
}
