package day_16;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlawedFrequencyTransmission {

    private final List<Integer> pattern;
    private final int offset;
    private List<Integer> input;
    private Integer[] output;

    public FlawedFrequencyTransmission(final String input) {
        this(input, 1);
    }

    public FlawedFrequencyTransmission(final String input, final int repeat) {
        this.pattern = Arrays.asList(0, 1, 0, -1);

        final String offset = input.substring(0, 7);
        this.offset = Integer.valueOf(offset);

        final char[] chars = input.toCharArray();
        final List<Integer> nums = new ArrayList<>();
        for (int i=0; i<repeat; i++) {
            for (final char c : chars) {
                nums.add(Integer.valueOf(String.valueOf(c)));
            }
        }
        this.input = nums;
        this.output = new Integer[input.length()];
    }

    public void iteratePhases(final int phases) {
        for (int i = 0; i < phases; i++) {
            output = new Integer[input.size()];
            iteratePhase();
            input = Arrays.asList(output);
        }
    }

    public void iteratePhase() {

        for (int i = 1; i <= input.size(); i++) {
            final List<Integer> repeatedPattern = new ArrayList<>();
            int index = 0;
            int sum = 0;
            while (repeatedPattern.size() < input.size() + 1) {
                for (int j = 0; j < i; j++) {
                    repeatedPattern.add(pattern.get(index));
                }
                index = (index + 1) % 4;
            }

            for (int j = 0; j < input.size(); j++) {
                sum += repeatedPattern.get(j + 1) * input.get(j);
            }
            output[i-1] = Math.abs(sum % 10);
        }
    }

    public void fastIterate() {
        int total = 0;
        for (int i=input.size()-1; i>input.size()/2; i--) {
            total += input.get(i);
            output[i] = Math.abs(total % 10);
        }
    }

    public void fastIterate(final int phases) {
        for (int i = 0; i < phases; i++) {
            output = new Integer[input.size()];
            fastIterate();
            input = Arrays.asList(output);
        }
    }

    public String getOutput() {
        return getOutput(output.length);
    }

    public String getOutput(final int digits) {
        return getOutput(digits, 0);
    }

    public String getOutput(final int digits, final int offset) {
        final StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset + digits; i++) {
            sb.append(output[i]);
        }
        return sb.toString();
    }

    public String getOutputWithOffset() {
        return getOutput(8, offset);
    }

    public static void main(String[] args) throws IOException {
        // Part One
        final String input = FileUtils.readFileToString(new File("src/main/resources/16_input.txt"), "UTF-8");
        final FlawedFrequencyTransmission fft = new FlawedFrequencyTransmission(input);
        fft.iteratePhases(100);
        System.out.println("Part One: " + fft.getOutput(8));

        // Part Two
        final FlawedFrequencyTransmission fft2 = new FlawedFrequencyTransmission(input, 10000);
        fft2.fastIterate(100);
        System.out.println("Part Two: " + fft2.getOutputWithOffset());
    }
}