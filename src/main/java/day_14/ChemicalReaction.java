package day_14;

import java.util.ArrayList;
import java.util.List;

public class ChemicalReaction {

    private final String output;
    private final int outputQuantity;
    private final List<String> inputs = new ArrayList<>();
    private final List<Integer> inputQuantities = new ArrayList<>();

    public ChemicalReaction(final String reaction) {
        final String[] parts = reaction.split(" => ");

        final String[] out = parts[1].split(" ");
        this.outputQuantity = Integer.parseInt(out[0]);
        this.output = out[1];

        final String[] ingredients = parts[0].split(", ");
        for (final String ingredient : ingredients) {
            final String[] in = ingredient.split(" ");
            this.inputQuantities.add(Integer.parseInt(in[0]));
            this.inputs.add(in[1]);
        }
    }

    public String getOutput() {
        return output;
    }

    public int getOutputQuantity() {
        return outputQuantity;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public List<Integer> getInputQuantities() {
        return inputQuantities;
    }
}
