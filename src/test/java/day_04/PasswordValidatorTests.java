package day_04;

import org.junit.Assert;
import org.junit.Test;

public class PasswordValidatorTests {
    private PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    public void testCaseOne() {
        Assert.assertTrue(passwordValidator.isValid(111111));
    }

    @Test
    public void testCaseTwo() {
        Assert.assertFalse(passwordValidator.isValid(223450));
    }

    @Test
    public void testCaseThree() {
        Assert.assertFalse(passwordValidator.isValid(123789));
    }

    @Test
    public void testCaseFour() {
        Assert.assertTrue(passwordValidator.isReallyValid(112233));
    }

    @Test
    public void testCaseFive() {
        Assert.assertFalse(passwordValidator.isReallyValid(123444));
    }

    @Test
    public void testCaseSix() {
        Assert.assertTrue(passwordValidator.isReallyValid(111122));
    }

}
