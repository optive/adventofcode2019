package day_07;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ThrustMaximizer {

    public long calculateOutputSignal(final List<Integer> phaseSequence, final String program) {
        long input = 0;
        for (final int phase : phaseSequence) {
            final Amplifier amplifier = new Amplifier(program, new LinkedList<>(Arrays.asList(phase, (int) input)));
            input = amplifier.getOutput();
        }
        return input;
    }

    public long calculateFeedbackSignal(final List<Integer> phaseSequence, final String program) {
        long input = 0;
        final List<Queue<Integer>> inputs = new ArrayList<>();
        final List<Amplifier> amplifiers = new ArrayList<>();

        // Initialise the arrays
        for (int i = 0; i < phaseSequence.size(); i++) {
            final Queue<Integer> inputQueue = new LinkedList<>(Arrays.asList(phaseSequence.get(i), (int) input));
            inputs.add(inputQueue);
            final Amplifier amplifier = new Amplifier(program, inputQueue);
            amplifiers.add(amplifier);
            input = amplifier.getOutput();
        }

        // Iterate until there are no more outputs
        boolean isLooping = true;
        while (isLooping) {
            for (int i = 0; i < amplifiers.size(); i++) {
                if (amplifiers.get(i).isTerminated()) {
                    isLooping = false;
                    break;
                }

                inputs.get(i).add((int) input);
                input = amplifiers.get(i).getOutput();
            }
        }

        return input;
    }

    public static void main(String[] args) throws IOException {
        // Part One
        final String program = FileUtils.readFileToString(new File("src/main/resources/07_input.txt"), "UTF-8");
        final ThrustMaximizer tm = new ThrustMaximizer();
        final List<List<Integer>> possiblePhases = Arrays.asList(Arrays.asList(0, 1, 2, 3, 4), Arrays.asList(1, 0, 2, 3, 4), Arrays.asList(2, 0, 1, 3, 4), Arrays.asList(0, 2, 1, 3, 4), Arrays.asList(1, 2, 0, 3, 4), Arrays.asList(2, 1, 0, 3, 4), Arrays.asList(2, 1, 3, 0, 4), Arrays.asList(1, 2, 3, 0, 4), Arrays.asList(3, 2, 1, 0, 4), Arrays.asList(2, 3, 1, 0, 4), Arrays.asList(1, 3, 2, 0, 4), Arrays.asList(3, 1, 2, 0, 4), Arrays.asList(3, 0, 2, 1, 4), Arrays.asList(0, 3, 2, 1, 4), Arrays.asList(2, 3, 0, 1, 4), Arrays.asList(3, 2, 0, 1, 4), Arrays.asList(0, 2, 3, 1, 4), Arrays.asList(2, 0, 3, 1, 4), Arrays.asList(1, 0, 3, 2, 4), Arrays.asList(0, 1, 3, 2, 4), Arrays.asList(3, 1, 0, 2, 4), Arrays.asList(1, 3, 0, 2, 4), Arrays.asList(0, 3, 1, 2, 4), Arrays.asList(3, 0, 1, 2, 4), Arrays.asList(4, 0, 1, 2, 3), Arrays.asList(0, 4, 1, 2, 3), Arrays.asList(1, 4, 0, 2, 3), Arrays.asList(4, 1, 0, 2, 3), Arrays.asList(0, 1, 4, 2, 3), Arrays.asList(1, 0, 4, 2, 3), Arrays.asList(1, 0, 2, 4, 3), Arrays.asList(0, 1, 2, 4, 3), Arrays.asList(2, 1, 0, 4, 3), Arrays.asList(1, 2, 0, 4, 3), Arrays.asList(0, 2, 1, 4, 3), Arrays.asList(2, 0, 1, 4, 3), Arrays.asList(2, 4, 1, 0, 3), Arrays.asList(4, 2, 1, 0, 3), Arrays.asList(1, 2, 4, 0, 3), Arrays.asList(2, 1, 4, 0, 3), Arrays.asList(4, 1, 2, 0, 3), Arrays.asList(1, 4, 2, 0, 3), Arrays.asList(0, 4, 2, 1, 3), Arrays.asList(4, 0, 2, 1, 3), Arrays.asList(2, 0, 4, 1, 3), Arrays.asList(0, 2, 4, 1, 3), Arrays.asList(4, 2, 0, 1, 3), Arrays.asList(2, 4, 0, 1, 3), Arrays.asList(3, 4, 0, 1, 2), Arrays.asList(4, 3, 0, 1, 2), Arrays.asList(0, 3, 4, 1, 2), Arrays.asList(3, 0, 4, 1, 2), Arrays.asList(4, 0, 3, 1, 2), Arrays.asList(0, 4, 3, 1, 2), Arrays.asList(0, 4, 1, 3, 2), Arrays.asList(4, 0, 1, 3, 2), Arrays.asList(1, 0, 4, 3, 2), Arrays.asList(0, 1, 4, 3, 2), Arrays.asList(4, 1, 0, 3, 2), Arrays.asList(1, 4, 0, 3, 2), Arrays.asList(1, 3, 0, 4, 2), Arrays.asList(3, 1, 0, 4, 2), Arrays.asList(0, 1, 3, 4, 2), Arrays.asList(1, 0, 3, 4, 2), Arrays.asList(3, 0, 1, 4, 2), Arrays.asList(0, 3, 1, 4, 2), Arrays.asList(4, 3, 1, 0, 2), Arrays.asList(3, 4, 1, 0, 2), Arrays.asList(1, 4, 3, 0, 2), Arrays.asList(4, 1, 3, 0, 2), Arrays.asList(3, 1, 4, 0, 2), Arrays.asList(1, 3, 4, 0, 2), Arrays.asList(2, 3, 4, 0, 1), Arrays.asList(3, 2, 4, 0, 1), Arrays.asList(4, 2, 3, 0, 1), Arrays.asList(2, 4, 3, 0, 1), Arrays.asList(3, 4, 2, 0, 1), Arrays.asList(4, 3, 2, 0, 1), Arrays.asList(4, 3, 0, 2, 1), Arrays.asList(3, 4, 0, 2, 1), Arrays.asList(0, 4, 3, 2, 1), Arrays.asList(4, 0, 3, 2, 1), Arrays.asList(3, 0, 4, 2, 1), Arrays.asList(0, 3, 4, 2, 1), Arrays.asList(0, 2, 4, 3, 1), Arrays.asList(2, 0, 4, 3, 1), Arrays.asList(4, 0, 2, 3, 1), Arrays.asList(0, 4, 2, 3, 1), Arrays.asList(2, 4, 0, 3, 1), Arrays.asList(4, 2, 0, 3, 1), Arrays.asList(3, 2, 0, 4, 1), Arrays.asList(2, 3, 0, 4, 1), Arrays.asList(0, 3, 2, 4, 1), Arrays.asList(3, 0, 2, 4, 1), Arrays.asList(2, 0, 3, 4, 1), Arrays.asList(0, 2, 3, 4, 1), Arrays.asList(1, 2, 3, 4, 0), Arrays.asList(2, 1, 3, 4, 0), Arrays.asList(3, 1, 2, 4, 0), Arrays.asList(1, 3, 2, 4, 0), Arrays.asList(2, 3, 1, 4, 0), Arrays.asList(3, 2, 1, 4, 0), Arrays.asList(3, 2, 4, 1, 0), Arrays.asList(2, 3, 4, 1, 0), Arrays.asList(4, 3, 2, 1, 0), Arrays.asList(3, 4, 2, 1, 0), Arrays.asList(2, 4, 3, 1, 0), Arrays.asList(4, 2, 3, 1, 0), Arrays.asList(4, 1, 3, 2, 0), Arrays.asList(1, 4, 3, 2, 0), Arrays.asList(3, 4, 1, 2, 0), Arrays.asList(4, 3, 1, 2, 0), Arrays.asList(1, 3, 4, 2, 0), Arrays.asList(3, 1, 4, 2, 0), Arrays.asList(2, 1, 4, 3, 0), Arrays.asList(1, 2, 4, 3, 0), Arrays.asList(4, 2, 1, 3, 0), Arrays.asList(2, 4, 1, 3, 0), Arrays.asList(1, 4, 2, 3, 0), Arrays.asList(4, 1, 2, 3, 0));
        final List<Long> outputs = new ArrayList<>();
        for (final List<Integer> phase : possiblePhases) {
            outputs.add(tm.calculateOutputSignal(phase, program));
        }
        System.out.println(Collections.max(outputs));

        // Part Two
        final List<List<Integer>> secondPossiblePhases = Arrays.asList(Arrays.asList(5, 6, 7, 8, 9), Arrays.asList(6, 5, 7, 8, 9), Arrays.asList(7, 5, 6, 8, 9), Arrays.asList(5, 7, 6, 8, 9), Arrays.asList(6, 7, 5, 8, 9), Arrays.asList(7, 6, 5, 8, 9), Arrays.asList(7, 6, 8, 5, 9), Arrays.asList(6, 7, 8, 5, 9), Arrays.asList(8, 7, 6, 5, 9), Arrays.asList(7, 8, 6, 5, 9), Arrays.asList(6, 8, 7, 5, 9), Arrays.asList(8, 6, 7, 5, 9), Arrays.asList(8, 5, 7, 6, 9), Arrays.asList(5, 8, 7, 6, 9), Arrays.asList(7, 8, 5, 6, 9), Arrays.asList(8, 7, 5, 6, 9), Arrays.asList(5, 7, 8, 6, 9), Arrays.asList(7, 5, 8, 6, 9), Arrays.asList(6, 5, 8, 7, 9), Arrays.asList(5, 6, 8, 7, 9), Arrays.asList(8, 6, 5, 7, 9), Arrays.asList(6, 8, 5, 7, 9), Arrays.asList(5, 8, 6, 7, 9), Arrays.asList(8, 5, 6, 7, 9), Arrays.asList(9, 5, 6, 7, 8), Arrays.asList(5, 9, 6, 7, 8), Arrays.asList(6, 9, 5, 7, 8), Arrays.asList(9, 6, 5, 7, 8), Arrays.asList(5, 6, 9, 7, 8), Arrays.asList(6, 5, 9, 7, 8), Arrays.asList(6, 5, 7, 9, 8), Arrays.asList(5, 6, 7, 9, 8), Arrays.asList(7, 6, 5, 9, 8), Arrays.asList(6, 7, 5, 9, 8), Arrays.asList(5, 7, 6, 9, 8), Arrays.asList(7, 5, 6, 9, 8), Arrays.asList(7, 9, 6, 5, 8), Arrays.asList(9, 7, 6, 5, 8), Arrays.asList(6, 7, 9, 5, 8), Arrays.asList(7, 6, 9, 5, 8), Arrays.asList(9, 6, 7, 5, 8), Arrays.asList(6, 9, 7, 5, 8), Arrays.asList(5, 9, 7, 6, 8), Arrays.asList(9, 5, 7, 6, 8), Arrays.asList(7, 5, 9, 6, 8), Arrays.asList(5, 7, 9, 6, 8), Arrays.asList(9, 7, 5, 6, 8), Arrays.asList(7, 9, 5, 6, 8), Arrays.asList(8, 9, 5, 6, 7), Arrays.asList(9, 8, 5, 6, 7), Arrays.asList(5, 8, 9, 6, 7), Arrays.asList(8, 5, 9, 6, 7), Arrays.asList(9, 5, 8, 6, 7), Arrays.asList(5, 9, 8, 6, 7), Arrays.asList(5, 9, 6, 8, 7), Arrays.asList(9, 5, 6, 8, 7), Arrays.asList(6, 5, 9, 8, 7), Arrays.asList(5, 6, 9, 8, 7), Arrays.asList(9, 6, 5, 8, 7), Arrays.asList(6, 9, 5, 8, 7), Arrays.asList(6, 8, 5, 9, 7), Arrays.asList(8, 6, 5, 9, 7), Arrays.asList(5, 6, 8, 9, 7), Arrays.asList(6, 5, 8, 9, 7), Arrays.asList(8, 5, 6, 9, 7), Arrays.asList(5, 8, 6, 9, 7), Arrays.asList(9, 8, 6, 5, 7), Arrays.asList(8, 9, 6, 5, 7), Arrays.asList(6, 9, 8, 5, 7), Arrays.asList(9, 6, 8, 5, 7), Arrays.asList(8, 6, 9, 5, 7), Arrays.asList(6, 8, 9, 5, 7), Arrays.asList(7, 8, 9, 5, 6), Arrays.asList(8, 7, 9, 5, 6), Arrays.asList(9, 7, 8, 5, 6), Arrays.asList(7, 9, 8, 5, 6), Arrays.asList(8, 9, 7, 5, 6), Arrays.asList(9, 8, 7, 5, 6), Arrays.asList(9, 8, 5, 7, 6), Arrays.asList(8, 9, 5, 7, 6), Arrays.asList(5, 9, 8, 7, 6), Arrays.asList(9, 5, 8, 7, 6), Arrays.asList(8, 5, 9, 7, 6), Arrays.asList(5, 8, 9, 7, 6), Arrays.asList(5, 7, 9, 8, 6), Arrays.asList(7, 5, 9, 8, 6), Arrays.asList(9, 5, 7, 8, 6), Arrays.asList(5, 9, 7, 8, 6), Arrays.asList(7, 9, 5, 8, 6), Arrays.asList(9, 7, 5, 8, 6), Arrays.asList(8, 7, 5, 9, 6), Arrays.asList(7, 8, 5, 9, 6), Arrays.asList(5, 8, 7, 9, 6), Arrays.asList(8, 5, 7, 9, 6), Arrays.asList(7, 5, 8, 9, 6), Arrays.asList(5, 7, 8, 9, 6), Arrays.asList(6, 7, 8, 9, 5), Arrays.asList(7, 6, 8, 9, 5), Arrays.asList(8, 6, 7, 9, 5), Arrays.asList(6, 8, 7, 9, 5), Arrays.asList(7, 8, 6, 9, 5), Arrays.asList(8, 7, 6, 9, 5), Arrays.asList(8, 7, 9, 6, 5), Arrays.asList(7, 8, 9, 6, 5), Arrays.asList(9, 8, 7, 6, 5), Arrays.asList(8, 9, 7, 6, 5), Arrays.asList(7, 9, 8, 6, 5), Arrays.asList(9, 7, 8, 6, 5), Arrays.asList(9, 6, 8, 7, 5), Arrays.asList(6, 9, 8, 7, 5), Arrays.asList(8, 9, 6, 7, 5), Arrays.asList(9, 8, 6, 7, 5), Arrays.asList(6, 8, 9, 7, 5), Arrays.asList(8, 6, 9, 7, 5), Arrays.asList(7, 6, 9, 8, 5), Arrays.asList(6, 7, 9, 8, 5), Arrays.asList(9, 7, 6, 8, 5), Arrays.asList(7, 9, 6, 8, 5), Arrays.asList(6, 9, 7, 8, 5), Arrays.asList(9, 6, 7, 8, 5));
        final List<Long> secondOutputs = new ArrayList<>();
        for (final List<Integer> phaseSequence : secondPossiblePhases) {
            secondOutputs.add(tm.calculateFeedbackSignal(phaseSequence, program));
        }
        System.out.println(Collections.max(secondOutputs));
    }
}

