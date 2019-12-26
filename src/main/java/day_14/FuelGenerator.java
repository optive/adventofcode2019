package day_14;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FuelGenerator {

    final List<ChemicalReaction> reactions = new ArrayList<>();
    private Map<String, Long> overSupply = new HashMap<>();
    private Map<String, Long> chemicalMap = new HashMap<>();

    public FuelGenerator(final List<String> reactions) {
        for (final String reaction : reactions) {
            this.reactions.add(new ChemicalReaction(reaction));
        }
    }

    public long getOreRequirement() {
        return getOreRequirement(1);
    }

    public long getOreRequirement(final long fuelQuantity) {
        overSupply = new HashMap<>();
        chemicalMap = new HashMap<>();
        final ChemicalReaction fuelReaction = getReactionWhichProduces("FUEL");

        for (int i = 0; i < fuelReaction.getInputs().size(); i++) {
            final String ingredient = fuelReaction.getInputs().get(i);
            final long quantity = fuelReaction.getInputQuantities().get(i) * fuelQuantity;
            chemicalMap.put(ingredient, quantity);
        }

        // Generate the list of chemicals which can be generated from other chemicals
        final List<String> chemicals = reactions.stream()
                .filter(r -> !r.getInputs().contains("ORE"))
                .map(ChemicalReaction::getOutput)
                .collect(Collectors.toList());

        // Simplify the chemical map back to only chemicals which can be generated from ORE
        while (!Collections.disjoint(chemicalMap.keySet(), chemicals)) {
            for (final String chemical : chemicals) {
                if (chemicalMap.containsKey(chemical)) {
                    final ChemicalReaction reaction = getReactionWhichProduces(chemical);
                    populateChemicalMap(reaction);
                    chemicalMap.remove(chemical);
                }
            }
        }

        // Calculate the amount of ORE required for these base chemicals
        long totalOre = 0;
        for (final Map.Entry<String, Long> chemical : chemicalMap.entrySet()) {
            totalOre += getOreToProduce(chemical.getKey(), chemical.getValue());
        }

        return totalOre;
    }

    private long getOreToProduce(final String chemical, final long requiredQuantity) {
        final ChemicalReaction reaction = getReactionWhichProduces(chemical);
        final long outputQuantity = reaction.getOutputQuantity();
        return reaction.getInputQuantities().get(0) * getReactionMultiple(requiredQuantity, outputQuantity, chemical);
    }

    private ChemicalReaction getReactionWhichProduces(final String output) {
        return reactions.stream()
                .filter(r -> r.getOutput().equals(output))
                .collect(Collectors.toList()).get(0);
    }

    private void populateChemicalMap(final ChemicalReaction reaction) {
        final String output = reaction.getOutput();
        final long reactionMultiple = getReactionMultiple(chemicalMap.get(output), reaction.getOutputQuantity(), output);
        for (int i = 0; i < reaction.getInputs().size(); i++) {
            final String input = reaction.getInputs().get(i);
            long quantity = reaction.getInputQuantities().get(i) * reactionMultiple;
            if (chemicalMap.containsKey(input)) {
                quantity += chemicalMap.get(input);
            }
            chemicalMap.put(input, quantity);
        }
    }

    private long getReactionMultiple(final long requiredQuantity, final long quantityProduced, final String chemical) {
        final long remainder = requiredQuantity % quantityProduced;
        final long excess = quantityProduced - remainder;
        if (requiredQuantity % quantityProduced != 0) {
            if (overSupply.containsKey(chemical)) {
                final long spare = overSupply.get(chemical);
                if (remainder <= spare) {
                    overSupply.put(chemical, spare - remainder);
                    return requiredQuantity / quantityProduced;
                }
                overSupply.put(chemical, spare + excess);
            } else {
                overSupply.put(chemical, excess);
            }
            return requiredQuantity / quantityProduced + 1;
        } else {
            return requiredQuantity / quantityProduced;
        }
    }

    public long getFuelForTrillionOre() {
        final long oneTrillion = 1000000000000L;
        long fuel = (long) (oneTrillion / getOreRequirement());
        while (true) {
            final long oreRequired = getOreRequirement(fuel);
            if (oreRequired > oneTrillion) {
                break;
            }
            fuel++;
        }
        return fuel - 1;
    }


    public static void main(String[] args) throws IOException {
        // Part One
        final List<String> reactions = FileUtils.readLines(new File("src/main/resources/14_input.txt"), "UTF-8");
        final FuelGenerator fuelGenerator = new FuelGenerator(reactions);
        System.out.println(fuelGenerator.getOreRequirement());

        // Part Two
        System.out.println(fuelGenerator.getFuelForTrillionOre());
    }
}
