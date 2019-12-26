package day_15;

import day_05.ImprovedIntcodeExecutor;
import day_10.Position;

import java.util.LinkedList;
import java.util.Queue;

public class RepairDroid {

    final private ImprovedIntcodeExecutor intcodeExecutor;
    final private Queue<Integer> input;
    private Position position;

    public RepairDroid(final String program, final Position position) {
        this.position = position;
        this.input = new LinkedList<>();
        this.intcodeExecutor = new ImprovedIntcodeExecutor(input, program);
    }

    public RepairDroid(final RepairDroid repairDroid) {
        this.position = repairDroid.position;
        this.input = new LinkedList<>();
        this.intcodeExecutor = new ImprovedIntcodeExecutor(repairDroid.intcodeExecutor, input);
    }

    public Position getPosition() {
        return position;
    }

    public Position positionOfDirection(final int direction) {
        switch (direction) {
            case 1:
                return position.up();
            case 2:
                return position.down();
            case 3:
                return position.left();
            case 4:
                return position.right();
            default:
                System.out.println("Uh oh");
                return null;
        }
    }

    public int attemptMoveInDirection(final int direction) {
        input.add(direction);
        intcodeExecutor.executeProgram();
        final int status = intcodeExecutor.getOutput().remove().intValue();
        if (status != 0) {
            position = positionOfDirection(direction);
        }
        return status;
    }
}
