package day_15;

import day_10.Position;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RepairDroidProgram {

    final private String program;
    final private int[][] area = new int[50][50];
    final private List<RepairDroid> repairDroids = new ArrayList<>();
    final private Map<Position, TreeNode> nodeMap = new HashMap<>();
    private Set<Position> visitedPositions = new HashSet<>();
    private Position startingPosition;
    private Position oxygenSystem;

    public RepairDroidProgram(final String program) {
        this.program = program;
        this.startingPosition = new Position(25, 25);
        nodeMap.put(startingPosition, new TreeNode(startingPosition));
    }

    public int getMinimumStepsToOxygenSystem() {
        int pathLength = 0;
        TreeNode node = nodeMap.get(oxygenSystem);
        while (node.getParent() != null) {
            node = node.getParent();
            pathLength++;
        }

        return pathLength;
    }

    public void exploreArea() {
        // Initialise the first repair droid
        final RepairDroid repairDroid = new RepairDroid(program, startingPosition);
        repairDroids.add(repairDroid);
        visitedPositions.add(startingPosition);

        while (repairDroids.size() > 0) {
            exploreDirections(repairDroids.get(0));
        }
    }

    private void exploreDirections(final RepairDroid repairDroid) {
        for (int d = 1; d < 5; d++) {
            // Clone the repair droid for each possible direction
            final RepairDroid subDroid = new RepairDroid(repairDroid);
            final Position newPosition = subDroid.positionOfDirection(d);

            if (visitedPositions.contains(newPosition)) {
                continue; // Don't bother going back the way we came
            }
            visitedPositions.add(newPosition);
            final int status = subDroid.attemptMoveInDirection(d);
            area[newPosition.getY()][newPosition.getX()] = status;

            if (status != 0) {
                // We successfully moved to a new position, so add to the list of active droids
                repairDroids.add(subDroid);

                // Build the node tree
                final TreeNode newNode = new TreeNode(newPosition);
                nodeMap.put(newPosition, newNode);
                final TreeNode currentNode = nodeMap.get(repairDroid.getPosition());
                currentNode.addChild(newNode);

                if (status == 2) {
                    oxygenSystem = newPosition;
                }
            }
        }
        // This repair droid has finished exploring - kill it off
        repairDroids.remove(repairDroid);
    }

    public int getOxygenFillTime() {
        int fillTime = 0;
        return fillTime;
    }

    public void printArea() {
        for (int y = area.length - 1; y >= 0; y--) {
            for (int x = 0; x < area[y].length; x++) {
                if (area[y][x] == 0) {
                    System.out.print("# ");
                } else if (area[y][x] == 1) {
                    System.out.print(". ");
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
        final RepairDroidProgram repairDroidProgram = new RepairDroidProgram(program);
        repairDroidProgram.exploreArea();
        repairDroidProgram.printArea();
        System.out.println(repairDroidProgram.getMinimumStepsToOxygenSystem());

        // Part Two
        System.out.println(repairDroidProgram.getOxygenFillTime());
    }
}
