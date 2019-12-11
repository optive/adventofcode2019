package day_11;

import day_05.ImprovedIntcodeExecutor;
import day_10.Position;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PaintingRobot {

    private final ImprovedIntcodeExecutor intcodeExecutor;
    private final Queue<Integer> input;
    private int[][] hull;
    private int size;

    public PaintingRobot(final String program, final int size) {
        this.input = new LinkedList<>();
        intcodeExecutor = new ImprovedIntcodeExecutor(input, program, 10000);
        this.hull = new int[size][size];
        this.size = size;
    }

    public int getPaintedPanelsCount() {
        final Queue<Long> output = intcodeExecutor.getOutput();
        final Set<Position> paintedPanels = new HashSet<>();
        Direction direction = Direction.UP;
        Position position = new Position(size/2, size/2);

        while (!intcodeExecutor.isTerminated()) {
            input.add(hull[position.getY()][position.getX()]);
            intcodeExecutor.executeProgram();

            // Paint the panel
            hull[position.getY()][position.getX()] = output.remove().intValue();
            paintedPanels.add(position);

            //Move
            direction = newDirection(direction, output.remove().intValue());
            position = newPosition(direction, position);
        }

        return paintedPanels.size();
    }

    public void paintPositionWhite(final Position position) {
        hull[position.getY()][position.getX()] = 1;
    }


    private void printHull() {
        for (int y = hull.length - 1; y>=0; y--) {
            for (int x = 0; x<hull[y].length; x++) {
                System.out.print(hull[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private Direction newDirection(final Direction currentDirection, final int turn) {
        Direction newDirection = currentDirection;
        switch (currentDirection) {
            case UP:
                newDirection = (turn == 0) ? Direction.LEFT : Direction.RIGHT;
                break;
            case DOWN:
                newDirection = (turn == 0) ? Direction.RIGHT : Direction.LEFT;
                break;
            case LEFT:
                newDirection = (turn == 0) ? Direction.DOWN : Direction.UP;
                break;
            case RIGHT:
                newDirection = (turn == 0) ? Direction.UP : Direction.DOWN;
                break;
            default:
                System.out.println("Unexpected direction");
                break;
        }
        return newDirection;
    }

    private Position newPosition(final Direction direction, final Position position) {
        switch (direction) {
            case UP:
                return position.up();
            case DOWN:
                return position.down();
            case LEFT:
                return position.left();
            case RIGHT:
                return position.right();
            default:
                System.out.println("How did we get here?");
                return position;
        }
    }


    public static void main(String[] args) throws IOException {
        // Part One
        final String program = FileUtils.readFileToString(new File("src/main/resources/11_input.txt"), "UTF-8");
        final PaintingRobot paintingRobot = new PaintingRobot(program, 300);
        System.out.println(paintingRobot.getPaintedPanelsCount());
        paintingRobot.printHull();

        //Part Two
        final PaintingRobot paintingRobot2 = new PaintingRobot(program, 100);
        paintingRobot2.paintPositionWhite(new Position(50,50));
        paintingRobot2.getPaintedPanelsCount();
        paintingRobot2.printHull();

    }
}
