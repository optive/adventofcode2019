package day_19;

import day_05.ImprovedIntcodeExecutor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TractorBeam {

    private final Queue<Integer> input;
    private final String program;
    private int[][] area;
    private Map<Integer, Integer> leftmost = new HashMap<>();
    private Map<Integer, Integer> rightmost = new HashMap<>();
    private ImprovedIntcodeExecutor intcodeExecutor;

    public TractorBeam(final String program) {
        this.input = new LinkedList<>();
        this.program = program;
    }

    public int getAffectedPositionCount(final int size) {
        populateAreaMap(size);
        int sum = 0;
        for (final int[] row : area) {
            for (final int p : row) {
                sum += p;
            }
        }
        return sum;
    }

    private void populateAreaMap(final int size) {
        area = new int[size][size];
        int leftmost = 0;
        for (int y = 0; y < size; y++) {
            boolean foundBeam = false;
            for (int x = leftmost; x < size; x++) {
                input.addAll(Arrays.asList(x, y));
                intcodeExecutor = new ImprovedIntcodeExecutor(input, program, 100);
                intcodeExecutor.executeProgram();
                final int isPulled = intcodeExecutor.getOutput().poll().intValue();
                if (isPulled == 1 && !foundBeam) {
                    foundBeam = true;
                    leftmost = x;
                    this.leftmost.put(y, x);
                } else if (isPulled == 0 && foundBeam) {
                    rightmost.put(y, x - 1);
                    break; // No need to run the program on the remaining lines;
                }

                area[y][x] = isPulled;
            }
        }
    }

    public int getClosestShipPosition(final int shipSize) {
        final int areaWidth = 2000;
        populateAreaMap(areaWidth);

        for (int y = 50; y < areaWidth - shipSize; y++) {
            if (rightmost.get(y) == leftmost.get(y + shipSize - 1) + shipSize - 1) {
                int x = rightmost.get(y) - shipSize + 1;
                return x * 10000 + y;
            }
        }

        return 0;
    }


    private void printArea() {
        for (final int[] row : area) {
            for (final int p : row) {
                System.out.print(p + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        // Part One
        final String program = FileUtils.readFileToString(new File("src/main/resources/19_input.txt"), "UTF-8");
        final TractorBeam tractorBeam = new TractorBeam(program);
        System.out.println(tractorBeam.getAffectedPositionCount(50));

        // Part Two
        System.out.println(tractorBeam.getClosestShipPosition(100));

    }
}
