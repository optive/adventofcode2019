package day_15;

import day_05.ImprovedIntcodeExecutor;
import day_10.Position;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RepairDroid {

    final private ImprovedIntcodeExecutor intcodeExecutor;
    final private Queue<Integer> input;
    final private int[][] area = new int[50][50];
    private Set<Position> visitedPositions = new HashSet<>();
    private Position position;
    private TreeNode currentNode;

    public RepairDroid(final String program) {
        this.position = new Position(25, 25);
        this.currentNode = new TreeNode(position);
        this.input = new LinkedList<>();
        this.intcodeExecutor = new ImprovedIntcodeExecutor(input, program);
    }

    public int getMinimumStepsToOxygenSystem() {

        Position oldPosition = position;
        int direction = 1;

        while (oxygenSystemNotFound()) {
            moveInDirection(direction);
            if (position.equals(oldPosition)) {
                final List<Integer> availableDirections = getAvailableDirections();
                final List<Integer> newDirections = getNewDirections();

                // Prefer to go in a new direction if possible.
                if (newDirections.size() > 0) {
                    direction = newDirections.get(0);
                } else {
                    direction = availableDirections.get(0);
                }
            } else {
                if (currentNode.getParent() != null && currentNode.getParent().getPosition().equals(position)) {
                    currentNode = currentNode.getParent();
                } else {
                    final TreeNode newNode = new TreeNode(position);
                    currentNode.addChild(newNode);
                    currentNode = newNode;
                }

                oldPosition = position;
            }
        }

        int pathLength = 0;
        while (currentNode.getParent() != null) {
            currentNode = currentNode.getParent();
            pathLength++;
        }

        return pathLength;
    }

    public int getOxygenFillTime() {
        return 0;
    }

    private List<Integer> getAvailableDirections() {
        final List<Integer> availableDirections = new ArrayList<>();
        final Position up = position.up();
        final Position down = position.down();
        final Position left = position.left();
        final Position right = position.right();

        if (area[up.getY()][up.getX()] == 0) availableDirections.add(1);
        if (area[down.getY()][down.getX()] == 0) availableDirections.add(2);
        if (area[left.getY()][left.getX()] == 0) availableDirections.add(3);
        if (area[right.getY()][right.getX()] == 0) availableDirections.add(4);

        return availableDirections;
    }

    private List<Integer> getNewDirections() {
        final List<Integer> newDirections = new ArrayList<>();
        final Position up = position.up();
        final Position down = position.down();
        final Position left = position.left();
        final Position right = position.right();

        if (!visitedPositions.contains(up)) newDirections.add(1);
        if (!visitedPositions.contains(down)) newDirections.add(2);
        if (!visitedPositions.contains(left)) newDirections.add(3);
        if (!visitedPositions.contains(right)) newDirections.add(4);

        return newDirections;
    }

    private boolean oxygenSystemNotFound() {
        for (final int[] row : area) {
            for (final int cell : row) {
                if (cell == 2) return false;
            }
        }
        return true;
    }

    private void moveInDirection(final int direction) {
        input.add(direction);

        switch (direction) {
            case 1:
                attemptMovement(position.up());
                break;
            case 2:
                attemptMovement(position.down());
                break;
            case 3:
                attemptMovement(position.left());
                break;
            case 4:
                attemptMovement(position.right());
                break;
            default:
                System.out.println("Uh oh");
        }
    }

    private void attemptMovement(final Position newPosition) {
        intcodeExecutor.executeProgram();
        visitedPositions.add(newPosition);
        final int status = intcodeExecutor.getOutput().remove().intValue();
        if (status == 0) {
            area[newPosition.getY()][newPosition.getX()] = 1;
        } else if (status == 1) {
            position = newPosition;
        } else {
            area[newPosition.getY()][newPosition.getX()] = 2;
            position = newPosition;
        }
    }

    public void printArea() {
        for (int y = area.length - 1; y >= 0; y--) {
            for (int x = 0; x < area[y].length; x++) {
                if (position.getX() == x && position.getY() == y) {
                    System.out.print("D ");
                } else if (area[y][x] == 0) {
                    System.out.print(". ");
                } else if (area[y][x] == 1) {
                    System.out.print("X ");
                } else if (area[y][x] == 2) {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        // Part One
        final String program = FileUtils.readFileToString(new File("src/main/resources/15_input.txt"), "UTF-8");
        final RepairDroid repairDroid = new RepairDroid(program);
        System.out.println(repairDroid.getMinimumStepsToOxygenSystem());
        repairDroid.printArea();

        // Part Two
        System.out.println(repairDroid.getOxygenFillTime());
    }
}
