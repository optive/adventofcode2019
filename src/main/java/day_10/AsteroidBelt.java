package day_10;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AsteroidBelt {
    private final List<Position> positions = new ArrayList<>();

    public AsteroidBelt(final List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            final char[] line = input.get(i).toCharArray();
            for (int j = 0; j < line.length; j++) {
                if ('#' == line[j]) {
                    positions.add(new Position(j, i));
                }
            }
        }
    }

    public Map.Entry<Position, Integer> getBestLineOfSight() {
        final Map<Position, Integer> lineOfSightCount = new HashMap<>();
        for (final Position position : positions) {
            lineOfSightCount.put(position, getVisibleAsteroids(position).size());
        }
        return Collections.max(lineOfSightCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue));
    }

    public List<Position> getVaporizedAsteroids(final Position asteroid) {
        final List<Position> vaporizedAsteroids = new ArrayList<>();
        while (positions.size() > 1) {
            vaporizedAsteroids.addAll(getVaporizedAsteroidsOneRotation(asteroid));
            positions.removeAll(vaporizedAsteroids);
        }
        return vaporizedAsteroids;
    }

    private List<Position> getVaporizedAsteroidsOneRotation(final Position asteroid) {
        final Map<Position, int[]> visibleAsteroids = getVisibleAsteroids(asteroid);
        final Map<Position, Double> asteroidsByAngle = visibleAsteroids.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> getAngle(e.getValue())
                ));

        return asteroidsByAngle.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double getAngle(final int[] vector) {
        final double angleFromHorizontal = Math.toDegrees(Math.atan2(vector[1], vector[0]));
        final double angleFromVertical = angleFromHorizontal + 90;
        if (angleFromVertical < 0) {
            return 360 + angleFromVertical;
        } else {
            return angleFromVertical;
        }
    }

    private Map<Position, int[]> getVisibleAsteroids(final Position position) {
        // Generate the vectors from this asteroid to every other.
        final Map<Position, int[]> asteroidMap = new HashMap<>();
        for (final Position position2 : positions) {
            if (position.equals(position2)) continue;
            asteroidMap.put(position2, getVector(position, position2));
        }

        // Build the list of asteroids which are hidden behind others.
        final Set<Position> hiddenAsteroids = new HashSet<>();
        final Set<Map.Entry<Position, int[]>> asteroids = asteroidMap.entrySet();
        for (final Map.Entry<Position, int[]> asteroid1 : asteroids) {
            for (final Map.Entry<Position, int[]> asteroid2 : asteroids) {
                if (asteroid1.getKey().equals(asteroid2.getKey())) continue;
                final int[] vector1 = asteroid1.getValue();
                final int[] vector2 = asteroid2.getValue();

                // Check if the vectors are collinear and in the same direction.
                if (getCrossProduct(vector1, vector2) == 0 && getDotProduct(vector1, vector2) > 0) {
                    final int[] largestScalarVector = getLargerMagnitude(vector1, vector2);
                    if (Arrays.equals(largestScalarVector, asteroid1.getValue())) {
                        hiddenAsteroids.add(asteroid1.getKey());
                    } else {
                        hiddenAsteroids.add(asteroid2.getKey());
                    }
                }
            }
        }

        for (final Position asteroid : hiddenAsteroids) {
            asteroidMap.remove(asteroid);
        }

        return asteroidMap;
    }

    private int getCrossProduct(final int[] vector1, final int[] vector2) {
        return (vector1[0] * vector2[1] - vector1[1] * vector2[0]);
    }

    private int getDotProduct(final int[] vector1, final int[] vector2) {
        return (vector1[0] * vector2[0] + vector1[1] * vector2[1]);
    }

    private int[] getLargerMagnitude(final int[] vector1, final int[] vector2) {
        return Math.sqrt(Math.pow(vector1[0], 2) + Math.pow(vector1[1], 2)) >
                Math.sqrt(Math.pow(vector2[0], 2) + Math.pow(vector2[1], 2)) ? vector1 : vector2;
    }

    private int[] getVector(final Position position1, final Position position2) {
        return new int[]{position2.getX() - position1.getX(), position2.getY() - position1.getY()};
    }


    public static void main(String[] args) throws IOException {
        // Part One
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/10_input.txt"), "UTF-8");
        final AsteroidBelt asteroidBelt = new AsteroidBelt(lines);
        final Map.Entry<Position, Integer> bestLineOfSight = asteroidBelt.getBestLineOfSight();
        System.out.println(bestLineOfSight.getValue());
        System.out.println(bestLineOfSight.getKey().getX() + "," + bestLineOfSight.getKey().getY());

        // Part Two
        final List<Position> vaporizedAsteroids = asteroidBelt.getVaporizedAsteroids(bestLineOfSight.getKey());
        final Position twoHundredthAsteroid = vaporizedAsteroids.get(199);
        System.out.println(twoHundredthAsteroid.getX() * 100 + twoHundredthAsteroid.getY());
    }
}
