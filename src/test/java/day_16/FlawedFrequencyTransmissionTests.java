package day_16;

import org.junit.Assert;
import org.junit.Test;

public class FlawedFrequencyTransmissionTests {

    private FlawedFrequencyTransmission fft;

    @Test
    public void testCaseOne() {
        fft = new FlawedFrequencyTransmission("12345678");
        fft.iteratePhase();
        Assert.assertEquals("48226158", fft.getOutput());
    }

    @Test
    public void testCaseTwo() {
        fft = new FlawedFrequencyTransmission("12345678");
        fft.iteratePhases(2);
        Assert.assertEquals("34040438", fft.getOutput());
    }

    @Test
    public void testCaseThree() {
        fft = new FlawedFrequencyTransmission("12345678");
        fft.iteratePhases(3);
        Assert.assertEquals("03415518", fft.getOutput());
    }

    @Test
    public void testCaseFour() {
        fft = new FlawedFrequencyTransmission("12345678");
        fft.iteratePhases(4);
        Assert.assertEquals("01029498", fft.getOutput());
    }

    @Test
    public void testCaseFive() {
        fft = new FlawedFrequencyTransmission("80871224585914546619083218645595");
        fft.iteratePhases(100);
        Assert.assertEquals("24176176", fft.getOutput(8));
    }

    @Test
    public void testCaseSix() {
        fft = new FlawedFrequencyTransmission("19617804207202209144916044189917");
        fft.iteratePhases(100);
        Assert.assertEquals("73745418", fft.getOutput(8));
    }

    @Test
    public void testCaseSeven() {
        fft = new FlawedFrequencyTransmission("69317163492948606335995924319873");
        fft.iteratePhases(100);
        Assert.assertEquals("52432133", fft.getOutput(8));
    }

    @Test
    public void testCaseEight() {
        fft = new FlawedFrequencyTransmission("03036732577212944063491565474664", 10000);
        fft.fastIterate(100);
        Assert.assertEquals("84462026", fft.getOutputWithOffset());
    }

    @Test
    public void testCaseNine() {
        fft = new FlawedFrequencyTransmission("02935109699940807407585447034323", 10000);
        fft.fastIterate(100);
        Assert.assertEquals("78725270", fft.getOutputWithOffset());
    }

    @Test
    public void testCaseTen() {
        fft = new FlawedFrequencyTransmission("03081770884921959731165446850517", 10000);
        fft.fastIterate(100);
        Assert.assertEquals("53553731", fft.getOutputWithOffset());
    }

}
