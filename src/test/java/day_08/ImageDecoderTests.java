package day_08;

import org.junit.Assert;
import org.junit.Test;

public class ImageDecoderTests {

    private ImageDecoder imageDecoder;

    @Test
    public void testFewestZeroes() {
        imageDecoder = new ImageDecoder("123456789012", 3, 2);
        Assert.assertEquals(1, imageDecoder.getFewestZeroesLayer());
    }

    @Test
    public void testMultiplication() {
        imageDecoder = new ImageDecoder("123456789012", 3, 2);
        Assert.assertEquals(1, imageDecoder.getMultiplicationValue());
    }
}
