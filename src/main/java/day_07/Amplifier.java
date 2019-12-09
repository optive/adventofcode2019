package day_07;

import day_05.ImprovedIntcodeExecutor;

import java.util.Queue;

public class Amplifier {

    private final ImprovedIntcodeExecutor ie;

    public Amplifier(final String program, final Queue<Integer> input) {
        this.ie = new ImprovedIntcodeExecutor(input, program);
    }

    public boolean isTerminated() {
        return ie.isTerminated();
    }

    public Long getOutput() {
        ie.executeProgram();
        final Queue<Long> output = ie.getOutput();
        return output.poll();
    }
}
