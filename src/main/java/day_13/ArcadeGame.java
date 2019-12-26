package day_13;

import day_05.ImprovedIntcodeExecutor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ArcadeGame {
    private final ImprovedIntcodeExecutor intcodeExecutor;
    private final Queue<Integer> gameInput;
    private int[][] board = new int[24][42];
    private int score;

    public ArcadeGame(final String program) {
        this.gameInput = new LinkedList<>();
        this.intcodeExecutor = new ImprovedIntcodeExecutor(gameInput, program.replaceFirst("1", "2"), 100);
    }

    public void printDisplay() {
        for (final int[] row : board) {
            for (final int tile : row) {
                System.out.print(getTileType(tile) + " ");
            }
            System.out.println();
        }
    }

    public void playBall() {
        intcodeExecutor.executeProgram();
        final Queue<Long> output = intcodeExecutor.getOutput();
        while (output.peek() != null) {
            final int x = output.remove().intValue();
            final int y = output.remove().intValue();
            if (x == -1 && y == 0) {
                score = output.remove().intValue();
            } else {
                final int id = output.remove().intValue();
                board[y][x] = id;
            }
        }
    }

    public char getTileType(final int tileId) {
        switch (tileId) {
            case 0:
                return (' ');
            case 1:
                return ('X');
            case 2:
                return ('=');
            case 3:
                return ('_');
            case 4:
                return ('o');
            default:
                System.out.println("Uh oh");
                return ' ';
        }
    }

    public int getNumberOfTiles(final int tileId) {
        int tileCount = 0;
        for (final int[] row : board) {
            for (final int tile : row) {
                if (tile == tileId) tileCount++;
            }
        }
        return tileCount;
    }

    public int getBallPosition() {
        for (final int[] row : board) {
            for (int x = 0; x < row.length; x++) {
                if (row[x] == 4) return x;
            }
        }
        return 0;
    }

    public int getPaddlePosition() {
        for (final int[] row : board) {
            for (int x = 0; x < row.length; x++) {
                if (row[x] == 3) return x;
            }
        }
        return 0;
    }

    public boolean isCompleted() {
        return getNumberOfTiles(2) == 0;
    }

    public int getScore() {
        return score;
    }

    public void input(final int input) {
        gameInput.add(input);
    }


    public static void main(String[] args) throws IOException {
        // Step One
        final String program = FileUtils.readFileToString(new File("src/main/resources/13_input.txt"), "UTF-8");
        final ArcadeGame arcadeGame = new ArcadeGame(program);
        arcadeGame.playBall();
        System.out.println(arcadeGame.getNumberOfTiles(2));

        // Step Two
        while (!arcadeGame.isCompleted()) {
            if (arcadeGame.getPaddlePosition() < arcadeGame.getBallPosition()) {
                arcadeGame.input(1);
            } else if (arcadeGame.getPaddlePosition() > arcadeGame.getBallPosition()) {
                arcadeGame.input(-1);
            } else {
                arcadeGame.input(0);
            }
            arcadeGame.playBall();
        }
        System.out.println("Score:" + arcadeGame.getScore());
    }
}
