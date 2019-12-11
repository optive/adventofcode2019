package day_10;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AsteroidBeltTests {

    private AsteroidBelt asteroidBelt;

    @Test
    public void testCaseOne() {
        asteroidBelt = new AsteroidBelt(new ArrayList<>(Arrays.asList(
                ".#..#",
                ".....",
                "#####",
                "....#",
                "...##"
                )));
        final Map.Entry<Position, Integer> best = asteroidBelt.getBestLineOfSight();
        Assert.assertEquals(3, best.getKey().getX());
        Assert.assertEquals(4, best.getKey().getY());
        Assert.assertEquals(8, best.getValue().intValue());
    }

    @Test
    public void testCaseTwo() {
        asteroidBelt = new AsteroidBelt(new ArrayList<>(Arrays.asList(
        "......#.#.",
        "#..#.#....",
        "..#######.",
        ".#.#.###..",
        ".#..#.....",
        "..#....#.#",
        "#..#....#.",
        ".##.#..###",
        "##...#..#.",
        ".#....####")));

        final Map.Entry<Position, Integer> best = asteroidBelt.getBestLineOfSight();
        Assert.assertEquals(5, best.getKey().getX());
        Assert.assertEquals(8, best.getKey().getY());
        Assert.assertEquals(33, best.getValue().intValue());
    }

    @Test
    public void testCaseThree() {
        asteroidBelt = new AsteroidBelt(new ArrayList<>(Arrays.asList(
                "#.#...#.#.",
                ".###....#.",
                ".#....#...",
                "##.#.#.#.#",
                "....#.#.#.",
                ".##..###.#",
                "..#...##..",
                "..##....##",
                "......#...",
                ".####.###.")));

        final Map.Entry<Position, Integer> best = asteroidBelt.getBestLineOfSight();
        Assert.assertEquals(1, best.getKey().getX());
        Assert.assertEquals(2, best.getKey().getY());
        Assert.assertEquals(35, best.getValue().intValue());
    }

    @Test
    public void testCaseFour() {
        asteroidBelt = new AsteroidBelt(new ArrayList<>(Arrays.asList(
                ".#..#..###",
                "####.###.#",
                "....###.#.",
                "..###.##.#",
                "##.##.#.#.",
                "....###..#",
                "..#.#..#.#",
                "#..#.#.###",
                ".##...##.#",
                ".....#.#..")));

        final Map.Entry<Position, Integer> best = asteroidBelt.getBestLineOfSight();
        Assert.assertEquals(6, best.getKey().getX());
        Assert.assertEquals(3, best.getKey().getY());
        Assert.assertEquals(41, best.getValue().intValue());
    }

    @Test
    public void testCaseFive() {
        asteroidBelt = new AsteroidBelt(new ArrayList<>(Arrays.asList(
        ".#..##.###...#######",
        "##.############..##.",
        ".#.######.########.#",
        ".###.#######.####.#.",
        "#####.##.#.##.###.##",
        "..#####..#.#########",
        "####################",
        "#.####....###.#.#.##",
        "##.#################",
        "#####.##.###..####..",
        "..######..##.#######",
        "####.##.####...##..#",
        ".#####..#.######.###",
        "##...#.##########...",
        "#.##########.#######",
        ".####.#.###.###.#.##",
        "....##.##.###..#####",
        ".#.#.###########.###",
        "#.#.#.#####.####.###",
        "###.##.####.##.#..##")));

        final Map.Entry<Position, Integer> best = asteroidBelt.getBestLineOfSight();
        Assert.assertEquals(11, best.getKey().getX());
        Assert.assertEquals(13, best.getKey().getY());
        Assert.assertEquals(210, best.getValue().intValue());
    }

    @Test
    public void testCaseSix() {
        asteroidBelt = new AsteroidBelt(new ArrayList<>(Arrays.asList(
                ".#..##.###...#######",
                "##.############..##.",
                ".#.######.########.#",
                ".###.#######.####.#.",
                "#####.##.#.##.###.##",
                "..#####..#.#########",
                "####################",
                "#.####....###.#.#.##",
                "##.#################",
                "#####.##.###..####..",
                "..######..##.#######",
                "####.##.####...##..#",
                ".#####..#.######.###",
                "##...#.##########...",
                "#.##########.#######",
                ".####.#.###.###.#.##",
                "....##.##.###..#####",
                ".#.#.###########.###",
                "#.#.#.#####.####.###",
                "###.##.####.##.#..##")));

        final List<Position> vaporizedAsteroids = asteroidBelt.getVaporizedAsteroids(new Position(11,13));
        Assert.assertEquals(new Position(11,12), vaporizedAsteroids.get(0));
        Assert.assertEquals(new Position(12,1), vaporizedAsteroids.get(1));
        Assert.assertEquals(new Position(12,2), vaporizedAsteroids.get(2));
        Assert.assertEquals(new Position(12,8), vaporizedAsteroids.get(9));
        Assert.assertEquals(new Position(16,0), vaporizedAsteroids.get(19));
        Assert.assertEquals(new Position(16,9), vaporizedAsteroids.get(49));
        Assert.assertEquals(new Position(10,16), vaporizedAsteroids.get(99));
        Assert.assertEquals(new Position(9,6), vaporizedAsteroids.get(198));
        Assert.assertEquals(new Position(8,2), vaporizedAsteroids.get(199));
        Assert.assertEquals(new Position(10,9), vaporizedAsteroids.get(200));
        Assert.assertEquals(new Position(11,1), vaporizedAsteroids.get(298));
    }
}
