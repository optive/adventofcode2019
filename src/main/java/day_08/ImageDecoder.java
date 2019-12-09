package day_08;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ImageDecoder {

    private final List<int[]> layers = new ArrayList<>();

    public ImageDecoder(final int[] imageData, final int width, final int height) {

        final int layerSize = width * height;
        final int numLayers = imageData.length / layerSize;

        for (int i = 0; i < numLayers; i++) {
            final int[] layer = Arrays.copyOfRange(imageData, 0 + layerSize * i, layerSize * (i + 1));
            layers.add(layer);
        }
    }

    public ImageDecoder(final String imageData, final int width, final int height) {
        this(imageData.chars().map(x -> x - '0').toArray(), width, height);
    }


    public int getFewestZeroesLayer() {
        final Map<Integer, Integer> zeroesCount = new HashMap<>();
        for (int i = 0; i < layers.size(); i++) {
            int sumZeroes = 0;
            final int[] layer = layers.get(i);
            for (int j = 0; j < layer.length; j++) {
                if (layer[j] == 0) sumZeroes++;
            }
            zeroesCount.put(i + 1, sumZeroes);
        }

        final int layer = Collections.min(zeroesCount.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();
        return layer;
    }

    public int getMultiplicationValue() {
        final int[] layer = layers.get(getFewestZeroesLayer() - 1);
        int sumOnes = 0;
        int sumTwos = 0;
        for (final int pixel : layer) {
            if (pixel == 1) sumOnes++;
            if (pixel == 2) sumTwos++;
        }

        return sumOnes * sumTwos;
    }

    public int[] getVisibleLayer() {
        int[] layer = layers.get(0);

        for (int i=1; i<layers.size(); i++) {
            for (int j=0; j<layer.length; j++) {
                if (layer[j] == 2) layer[j] = layers.get(i)[j];
            }
        }

        return layer;
    }

    public static void main(String[] args) throws IOException {
        // Part One
        final String imageData = FileUtils.readFileToString(new File("src/main/resources/08_input.txt"), "UTF-8");
        final ImageDecoder imageDecoder = new ImageDecoder(imageData, 25, 6);
        System.out.println(imageDecoder.getMultiplicationValue());

        // Part Two
        final int[] visibleLayer = imageDecoder.getVisibleLayer();
        for (int i= 0; i< 6; i++) {
            for (int j=0 + 25*i; j<25*(i+1); j++) {
                System.out.print(visibleLayer[j] + " ");
            }
            System.out.println();
        }
    }
}
