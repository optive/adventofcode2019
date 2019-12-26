package day_12;

import java.util.Objects;

public class Moon {

    private final Coordinate position;
    private final Coordinate velocity;

    public Moon(final int x, final int y, final int z) {
        this(new Coordinate(x,y,z));
    }

    public Moon(final Coordinate position) {
        this(position, new Coordinate(0,0,0));
    }

    public Moon (final Coordinate position, final Coordinate velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public Coordinate getPosition() {
        return position;
    }

    public Coordinate getVelocity() {
        return velocity;
    }

    @Override
    public String toString() {
        return "pos=<x=" + position.getX() + ", y=" + position.getY() + ", z=" + position.getZ() +
                ">, vel<x=" + velocity.getX() + ", y=" + velocity.getY() + ", z=" + velocity.getZ() + ">";
    }
}
