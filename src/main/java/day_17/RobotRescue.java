package day_17;

import day_05.ImprovedIntcodeExecutor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RobotRescue {

    private ImprovedIntcodeExecutor intcodeExecutor;
    private Queue<Integer> input = new LinkedList<>();
    private Character[][] scaffold;

    public RobotRescue(final String program) {
        intcodeExecutor = new ImprovedIntcodeExecutor(input, program, 10000);
    }

    private void parseVideoFeed() {
        final Queue<Long> output = intcodeExecutor.getOutput();
        final List<Character> row = new ArrayList<>();
        final List<Character[]> grid = new ArrayList<>();

        while (grid.size() < 39) {
            final Long value = output.poll();
            if (value == 10 && row.size() > 0) {
                final Character[] newRow = new Character[row.size()];
                grid.add(row.toArray(newRow));
                row.clear();
            } else {
                row.add((char) value.intValue());
            }
        }

        final Character[][] newGrid = new Character[grid.size()][];
        scaffold = grid.toArray(newGrid);
    }

    public int getScaffoldAlightmentParameterSum() {
        intcodeExecutor.executeProgram();
        parseVideoFeed();

        int alignmentparameters = 0;
        for (int y = 1; y < scaffold.length - 1; y++) {
            for (int x = 1; x < scaffold[y].length - 1; x++) {
                if (scaffold[y][x] == '#' && isIntersection(x, y)) {
                    alignmentparameters += x * y;
                }
            }
        }

        return alignmentparameters;
    }

    private boolean isIntersection(final int x, final int y) {
        return scaffold[y][x - 1] == '#' && scaffold[y + 1][x] == '#' && scaffold[y][x + 1] == '#' && scaffold[y - 1][x] == '#';
    }

    private void printScaffold() {
        for (int y = 0; y < scaffold.length; y++) {
            for (int x = 0; x < scaffold[y].length; x++) {
                System.out.print(scaffold[y][x]);
            }
            System.out.println();
        }
    }

    public long rescueRobots() {
        addInput("A,B,B,C,C,A,A,B,B,C");

        // A
        addInput("L,12,R,4,R,4");

        // B
        addInput("R,12,R,4,L,12");

        // C
        addInput("R,12,R,4,L,6,L,8,L,8");

        // Live video feed?
        addInput("n");

        intcodeExecutor.executeProgram();
        final Iterator<Long> output = intcodeExecutor.getOutput().iterator();
        Long dust = 0L;
        while (output.hasNext()) {
            dust = output.next();
        }

        return dust;
    }

    private void addInput(final String str) {
        for (final char c : str.toCharArray()) {
            input.add((int) c);
        }
        input.add(10); // New line
    }

    public static void main(String[] args) throws IOException {
        // Part One
        final String program = FileUtils.readFileToString(new File("src/main/resources/17_input.txt"), "UTF-8");
        final RobotRescue robotRescue = new RobotRescue(program);
        System.out.println(robotRescue.getScaffoldAlightmentParameterSum());

        // Part Two
        final RobotRescue activeRobot = new RobotRescue(program.replaceFirst("1", "2"));
        System.out.println(activeRobot.rescueRobots());
    }
}
