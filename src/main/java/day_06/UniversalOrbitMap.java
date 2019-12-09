package day_06;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class UniversalOrbitMap {

    private Map<String, Node> orbitMap;

    public UniversalOrbitMap(final List<String> input) {
        orbitMap = initialiseOrbitMap(input);
    }

    private Map<String, Node> initialiseOrbitMap(final List<String> input) {
        final Map<String, Node> nodes = new HashMap<>();
        for (final String i : input) {
            final String[] planets = i.split("\\)");
            final String p1 = planets[0];
            final String p2 = planets[1];
            if (nodes.containsKey(p1) && nodes.containsKey(p2)) {
                nodes.get(p1).addChild(nodes.get(p2));
            } else if (nodes.containsKey(p1) && !nodes.containsKey(p2)) {
                final Node planet = new Node(p2);
                nodes.get(p1).addChild(planet);
                nodes.put(planet.getName(), planet);
            } else if (!nodes.containsKey(p1) && nodes.containsKey(p2)) {
                final Node planet = new Node(p1);
                planet.addChild(nodes.get(p2));
                nodes.put(planet.getName(), planet);
            } else {
                final Node planet1 = new Node(p1);
                final Node planet2 = new Node(p2);
                planet1.addChild(planet2);
                nodes.put(planet1.getName(), planet1);
                nodes.put(planet2.getName(), planet2);
            }
        }
        return nodes;
    }

    public int calculateTotalOrbits() {
        final Node centre = orbitMap.get("COM");
        final int totalOrbits = traverseNodes(0, centre);
        return totalOrbits;
    }

    public int calculateOrbitalTransfers() {
        final Node node1 = orbitMap.get("YOU");
        final Node node2 = orbitMap.get("SAN");
        final List<String> orbits1 = getOrbits(node1);
        final List<String> orbits2 = getOrbits(node2);
        final List<String> transfers = new ArrayList<>();
        transfers.addAll(orbits1.stream()
                .filter(p -> !orbits2.contains(p))
                .collect(Collectors.toList()));
        transfers.addAll(orbits2.stream()
                .filter(p -> !orbits1.contains(p))
                .collect(Collectors.toList()));
        return transfers.size();
    }

    private List<String> getOrbits(final Node node) {
        Node parent = node.getParent();
        final List<String> orbits = new ArrayList<>();
        while (null != parent) {
            orbits.add(parent.getName());
            parent = parent.getParent();
        }
        return orbits;
    }

    private int traverseNodes(final int depth, final Node node) {
        int childOrbitCount = 0;
        for (final Node child : node.getChildren()) {
            childOrbitCount += traverseNodes(depth + 1, child);
        }
        return depth + childOrbitCount;
    }


    public static void main(String[] args) throws IOException {
        // Part One
        final List<String> input = FileUtils.readLines(new File("src/main/resources/06_input.txt"), "UTF-8");
        final UniversalOrbitMap uom = new UniversalOrbitMap(input);
        System.out.println(uom.calculateTotalOrbits());

        //Part Two
        System.out.println(uom.calculateOrbitalTransfers());
    }
}
