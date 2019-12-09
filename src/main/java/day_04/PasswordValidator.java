package day_04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordValidator {

    public boolean isValid(final int password) {
        return isValid(Integer.toString(password).chars().map(c -> c - '0').toArray());
    }

    public boolean isValid(final int[] password) {
        boolean containsDouble = false;
        if (password.length != 6) return false;
        for (int i = 1; i < password.length; i++) {
            if (password[i] < password[i - 1]) {
                return false;
            } else if (password[i] == password[i - 1]) {
                containsDouble = true;
            }
        }
        return containsDouble;
    }

    public boolean isReallyValid(final int password) {
        return isReallyValid(Integer.toString(password).chars().map(c -> c - '0').toArray());
    }

    public boolean isReallyValid(final int[] password) {
        int[] isDouble = new int[6];
        int[] isTriple = new int[6];
        if (password.length != 6) return false;
        for (int i = 1; i < password.length; i++) {
            if (password[i] < password[i - 1]) {
                return false;
            } else if (password[i] == password[i - 1]) {
                isDouble[i] = 1;
            }
        }

        for (int i=1; i< isTriple.length; i++) {
            if (isDouble[i] + isDouble[i-1] == 2) {
                isTriple[i] = 1;
                isTriple[i-1] = 1;
            }
        }

        final int doubleCount = IntStream.range(0, isDouble.length)
                .mapToObj(i -> isDouble[i] - isTriple[i])
                .collect(Collectors.summingInt(Integer::intValue));

        return doubleCount > 0;
    }

    public static void main(String[] args) {
        final PasswordValidator pv = new PasswordValidator();
        int validPasswords = 0;
        int reallyValidPasswords = 0;
        for (int input = 137683; input <= 596253; input++) {
            if (pv.isValid(input)) validPasswords++;
            if (pv.isReallyValid(input)) reallyValidPasswords++;
        }

        System.out.println(validPasswords);
        System.out.println(reallyValidPasswords);
    }
}
