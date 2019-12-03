package day_03;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WiringDiagram {

    final List<int[]> path1;
    final List<int[]> path2;

    public WiringDiagram(final String wire1, final String wire2) {
        path1 = plotPath(wire1.split(","));
        path2 = plotPath(wire2.split(","));
    }

    public int calculateCrossingDistance() {
        final Set<int[]> intersectingPoints = path1.stream()
                .filter(p -> intersects(p, path2))
                .skip(1)
                .collect(Collectors.toSet());

        return calculateClosestIntersectionDistance(intersectingPoints);
    }

    public int shortestSteps() {
        final List<Integer> intersectionSteps = new ArrayList<>();
        for (int i = 1; i < path1.size(); i++) {
            for (int j = 1; j < path2.size(); j++) {
                if (Arrays.equals(path1.get(i), path2.get(j))) {
                    intersectionSteps.add(i + j);
                }
            }
        }
        return Collections.min(intersectionSteps);
    }

    private boolean intersects(final int[] position, List<int[]> path) {
        for (final int[] p : path) {
            if (Arrays.equals(position, p)) return true;
        }
        return false;
    }

    private int calculateClosestIntersectionDistance(final Set<int[]> points) {
        final List<Integer> intersectionDistances = new ArrayList<>();
        for (final int[] point : points) {
            intersectionDistances.add(Math.abs(point[0] + Math.abs(point[1])));
        }
        return Collections.min(intersectionDistances);
    }


    private void plotUp(final List<int[]> path, final int distance, final int x, final int y) {
        for (int i = 0; i < distance; i++) {
            path.add(new int[]{x, y + i});
        }
    }

    private void plotDown(final List<int[]> path, final int distance, final int x, final int y) {
        for (int i = 0; i < distance; i++) {
            path.add(new int[]{x, y - i});
        }
    }

    private void plotRight(final List<int[]> path, final int distance, final int x, final int y) {
        for (int i = 0; i < distance; i++) {
            path.add(new int[]{x + i, y});
        }
    }

    private void plotLeft(final List<int[]> path, final int distance, final int x, final int y) {
        for (int i = 0; i < distance; i++) {
            path.add(new int[]{x - i, y});
        }
    }

    private List<int[]> plotPath(final String[] instructions) {
        final List<int[]> path = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (final String instruction : instructions) {
            final int distance = Integer.parseInt(instruction.substring(1));
            switch (instruction.charAt(0)) {
                case 'U':
                    plotUp(path, distance, x, y);
                    y += distance;
                    break;
                case 'D':
                    plotDown(path, distance, x, y);
                    y -= distance;
                    break;
                case 'R':
                    plotRight(path, distance, x, y);
                    x += distance;
                    break;
                case 'L':
                    plotLeft(path, distance, x, y);
                    x -= distance;
                    break;
                default:
                    System.out.println("Error parsing direction:" + instruction.charAt(0));
                    break;
            }
        }
        return path;
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/03_input.txt"), "UTF-8");

        final WiringDiagram wd = new WiringDiagram(lines.get(0), lines.get(1));
        System.out.println(wd.calculateCrossingDistance());
        System.out.println(wd.shortestSteps());
    }
}
