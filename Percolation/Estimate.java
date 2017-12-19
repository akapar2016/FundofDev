/*
 * Author: Aayush Kapar, akapar2016@my.fit.edu
 * Course: CSE 1002, Section 01, Fall 2017
 * Project: Percolation2 Estimate
 */

public final class Estimate {
    private Estimate () {}

    public static final int ZERO = 0;
    public static final int ONE = 1;

    // do specified number of trials and return fraction that percolate
    public static double evaluate (final int n, final double p, final int trials) {
        int count = ZERO;
        for (int repeat = ZERO; repeat < trials; repeat++) {
            final boolean[][] isOpen = Percolation.random(n, p);
            final boolean[][] isOpen2 = Percolation2.random(n, p);
            if (Percolation.percolates(isOpen)) {
                count++;
            }
            if (Percolation2.percolates(isOpen2)) {
                count--;
            }
        }
        return (double) count / trials;
    }

    public static void main (final String[] args) {
        final int n = Integer.parseInt(args[0]);
        final double p = Double.parseDouble(args[1]);
        final int trials = Integer.parseInt(args[2]);
        final double q = evaluate(n, p, trials);
        StdOut.println(q);
    }
}
