package day_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoonSimulator {

    private List<Moon> moons;
    private Integer[][] initialPositions = new Integer[3][];
    private Integer[][] initialVelocities = new Integer[3][];

    public MoonSimulator(final List<Moon> moons) {
        this.moons = moons;
        initialPositions[0] = moons.stream().map(m -> m.getPosition().getX()).toArray(Integer[]::new);
        initialPositions[1] = moons.stream().map(m -> m.getPosition().getY()).toArray(Integer[]::new);
        initialPositions[2] = moons.stream().map(m -> m.getPosition().getZ()).toArray(Integer[]::new);
        initialVelocities[0] = moons.stream().map(m -> m.getVelocity().getX()).toArray(Integer[]::new);
        initialVelocities[1] = moons.stream().map(m -> m.getVelocity().getY()).toArray(Integer[]::new);
        initialVelocities[2] = moons.stream().map(m -> m.getVelocity().getZ()).toArray(Integer[]::new);
    }

    public void iterate(final int steps) {
        for (int i = 0; i < steps; i++) {
            iterate();
        }
    }

    public long findStepsUntilRepetition() {
        int numStates = 0;
        int cycleTimeX = 0;
        int cycleTimeY = 0;
        int cycleTimeZ = 0;

        while (cycleTimeX == 0 || cycleTimeY == 0 || cycleTimeZ == 0) {
            iterate();
            numStates++;
            if (Arrays.equals(initialPositions[0], moons.stream().map(m -> m.getPosition().getX()).toArray(Integer[]::new)) &&
                    Arrays.equals(initialVelocities[0], moons.stream().map(m -> m.getVelocity().getX()).toArray(Integer[]::new))) {
                cycleTimeX = numStates;
            }
            if (Arrays.equals(initialPositions[1], moons.stream().map(m -> m.getPosition().getY()).toArray(Integer[]::new)) &&
                    Arrays.equals(initialVelocities[1], moons.stream().map(m -> m.getVelocity().getY()).toArray(Integer[]::new))) {
                cycleTimeY = numStates;
            }
            if (Arrays.equals(initialPositions[2], moons.stream().map(m -> m.getPosition().getZ()).toArray(Integer[]::new)) &&
                    Arrays.equals(initialVelocities[2], moons.stream().map(m -> m.getVelocity().getZ()).toArray(Integer[]::new))) {
                cycleTimeZ = numStates;
            }
        }

        return lcm(cycleTimeX, cycleTimeY, cycleTimeZ) / 2;
    }

    private static long gcd(final long x, final long y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

    private long lcm(final long x, final long y, final long z) {
        return lcm(x, lcm(y, z));
    }

    private long lcm(final long x, final long y) {
        return x * (y / gcd(x, y));
    }

    private void iterate() {
        applyGravity();
        applyMovement();
    }

    private void applyGravity() {
        final List<int[]> speedDeltas = new ArrayList<>();
        for (final Moon moon : moons) {
            speedDeltas.add(new int[3]);
        }

        for (int i = 0; i < moons.size(); i++) {
            for (int j = i + 1; j < moons.size(); j++) {
                final Coordinate position1 = moons.get(i).getPosition();
                final Coordinate position2 = moons.get(j).getPosition();
                final int[] speedDelta1 = speedDeltas.get(i);
                final int[] speedDelta2 = speedDeltas.get(j);

                if (position1.getX() > position2.getX()) {
                    speedDelta1[0]--;
                    speedDelta2[0]++;
                } else if (position1.getX() < position2.getX()) {
                    speedDelta1[0]++;
                    speedDelta2[0]--;
                }

                if (position1.getY() > position2.getY()) {
                    speedDelta1[1]--;
                    speedDelta2[1]++;
                } else if (position1.getY() < position2.getY()) {
                    speedDelta1[1]++;
                    speedDelta2[1]--;
                }

                if (position1.getZ() > position2.getZ()) {
                    speedDelta1[2]--;
                    speedDelta2[2]++;
                } else if (position1.getZ() < position2.getZ()) {
                    speedDelta1[2]++;
                    speedDelta2[2]--;
                }
            }
        }

        for (int i = 0; i < moons.size(); i++) {
            final int[] speedDelta = speedDeltas.get(i);
            final Coordinate velocity = moons.get(i).getVelocity();
            velocity.setX(velocity.getX() + speedDelta[0]);
            velocity.setY(velocity.getY() + speedDelta[1]);
            velocity.setZ(velocity.getZ() + speedDelta[2]);
        }
    }

    private void applyMovement() {
        for (final Moon moon : moons) {
            final Coordinate position = moon.getPosition();
            final Coordinate velocity = moon.getVelocity();
            position.setX(position.getX() + velocity.getX());
            position.setY(position.getY() + velocity.getY());
            position.setZ(position.getZ() + velocity.getZ());
        }
    }

    public int calculateEnergy() {
        int totalEnergy = 0;
        for (final Moon moon : moons) {
            final Coordinate position = moon.getPosition();
            final Coordinate velocity = moon.getVelocity();
            final int potentialEnergy = Math.abs(position.getX()) + Math.abs(position.getY()) + Math.abs(position.getZ());
            final int kineticEnergy = Math.abs(velocity.getX()) + Math.abs(velocity.getY()) + Math.abs(velocity.getZ());
            totalEnergy += potentialEnergy * kineticEnergy;
        }
        return totalEnergy;
    }

    public static void main(String[] args) {
        // Step One
        final MoonSimulator moonSimulator = new MoonSimulator(Arrays.asList(
                new Moon(14, 15, -2),
                new Moon(17, -3, 4),
                new Moon(6, 12, -13),
                new Moon(-2, 10, -8)));
        moonSimulator.iterate(1000);
        System.out.println(moonSimulator.calculateEnergy());

        // Step Two
        final MoonSimulator moonSimulator2 = new MoonSimulator(Arrays.asList(
                new Moon(14, 15, -2),
                new Moon(17, -3, 4),
                new Moon(6, 12, -13),
                new Moon(-2, 10, -8)));
        System.out.println(moonSimulator2.findStepsUntilRepetition());
    }
}
