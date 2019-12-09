package day_05;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ImprovedIntcodeExecutor {

    private final Queue<Long> output = new LinkedList<>();
    private final Queue<Integer> input;
    private final long[] program;
    private int pointer;
    private int relativeBase;
    private boolean isTerminated;

    public boolean isTerminated() {
        return isTerminated;
    }

    public ImprovedIntcodeExecutor(final Queue<Integer> input, final long[] program) {
        this.input = input;
        this.program = program;
    }

    public ImprovedIntcodeExecutor(final Queue<Integer> input, final long[] program, final int padding) {
        this(input, Arrays.copyOf(program, program.length + padding));
    }

    public ImprovedIntcodeExecutor(final Queue<Integer> input, final String program) {
        this.input = input;
        final String[] values = program.split(",");
        this.program = Arrays.asList(values).stream().mapToLong(Long::parseLong).toArray();
    }

    public ImprovedIntcodeExecutor(final Queue<Integer> input, final String program, final int padding) {
        this.input = input;
        final String[] values = program.split(",");
        final long[] programArray = Arrays.asList(values).stream().mapToLong(Long::parseLong).toArray();
        this.program = Arrays.copyOf(programArray, programArray.length + padding);
    }

    public Queue<Long> getOutput() {
        return output;
    }

    public void executeProgram() {
        boolean executing = true;
        while (executing) {
            executing = readOpcode(pointer, program);
        }
    }

    private boolean readOpcode(final int startIndex, final long[] program) {
        final int instruction = (int) program[startIndex];
        final int opcode = instruction % 100;
        final int mode1 = (instruction / 100) % 10;
        final int mode2 = (instruction / 1000) % 10;
        final int mode3 = (instruction / 10000) % 10;
        switch (opcode) {
            case 1:
                program[resolveIndex(program[startIndex + 3], mode3)] = resolveParameter(program, program[startIndex + 1], mode1) +
                        resolveParameter(program, program[startIndex + 2], mode2);
                pointer += 4;
                break;
            case 2:
                program[resolveIndex(program[startIndex + 3], mode3)] = resolveParameter(program, program[startIndex + 1], mode1) *
                        resolveParameter(program, program[startIndex + 2], mode2);
                pointer += 4;
                break;
            case 3:
                final Integer value = input.poll();
                if (null != value) {
                    program[resolveIndex(program[startIndex + 1], mode1)] = value;
                    pointer += 2;
                } else {
                    // Pause executing for now - we will wait for a new input signal before resuming.
                    return false;
                }
                break;
            case 4:
                output.add(resolveParameter(program, program[startIndex + 1], mode1));
                pointer += 2;
                break;
            case 5:
                if (resolveParameter(program, program[startIndex + 1], mode1) != 0) {
                    pointer = (int) resolveParameter(program, program[startIndex + 2], mode2);
                } else {
                    pointer += 3;
                }
                break;
            case 6:
                if (resolveParameter(program, program[startIndex + 1], mode1) == 0) {
                    pointer = (int) resolveParameter(program, program[startIndex + 2], mode2);
                } else {
                    pointer += 3;
                }
                break;
            case 7:
                if (resolveParameter(program, program[startIndex + 1], mode1) < resolveParameter(program, program[startIndex + 2], mode2)) {
                    program[resolveIndex(program[startIndex + 3], mode3)] = 1;
                } else {
                    program[resolveIndex(program[startIndex + 3], mode3)] = 0;
                }
                pointer += 4;
                break;
            case 8:
                if (resolveParameter(program, program[startIndex + 1], mode1) == resolveParameter(program, program[startIndex + 2], mode2)) {
                    program[resolveIndex(program[startIndex + 3], mode3)] = 1;
                } else {
                    program[resolveIndex(program[startIndex + 3], mode3)] = 0;
                }
                pointer += 4;
                break;
            case 9:
                relativeBase += resolveParameter(program, program[startIndex + 1], mode1);
                pointer += 2;
                break;
            case 99:
                isTerminated = true;
                return false;
            default:
                System.out.println("Uh oh");
                return false;
        }
        return true;
    }

    private long resolveParameter(final long[] program, final long value, final int mode) {
        if (mode == 0) {
            // Position Mode
            return program[(int) value];
        } else if (mode == 1) {
            // Immediate Mode
            return value;
        } else if (mode == 2) {
            // Relative Mode
            return program[relativeBase + (int) value];
        } else {
            System.out.println("Unknown parameter mode: " + mode);
            return 0;
        }
    }

    private int resolveIndex(final long value, final int mode) {
        if (mode == 2) {
            // Relative Mode
            return relativeBase + (int) value;
        } else {
            // Only position mode is otherwise permitted
            return (int) value;
        }
    }

    public static void main(String[] args) throws IOException {
        // Part One
        final String program = FileUtils.readFileToString(new File("src/main/resources/05_input.txt"), "UTF-8");
        final ImprovedIntcodeExecutor ie = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(1)), program);
        ie.executeProgram();
        System.out.println(((LinkedList) ie.getOutput()).removeLast());

        // Part Two
        final ImprovedIntcodeExecutor ie2 = new ImprovedIntcodeExecutor(new LinkedList<>(Arrays.asList(5)), program);
        ie2.executeProgram();
        System.out.println(((LinkedList) ie2.getOutput()).removeLast());
    }
}
