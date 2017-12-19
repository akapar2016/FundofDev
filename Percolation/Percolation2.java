/*
 * Author: Aayush Kapar, akapar2016@my.fit.edu
 * Course: CSE 1002, Section 01, Fall 2017
 * Project: Percolation2
 */


//Helped by Jiaqi Yang

public final class Percolation2 {
    private Percolation2 () {}

    public static final double POINTFIVE = 0.5;
    public static final int ZERO = 0;
    public static final int ONE = 1;

    // given an n-by-n matrix of open sites, return an n-by-n matrix
    // of sites reachable from the top
    public static boolean[][] flow (final boolean[][] isOpen) {
        final int size = isOpen.length;
        final boolean[][] isFull = new boolean[size][size];
        for (int count = ZERO; count < size; count++) {
            flow(isOpen, isFull, ZERO, count);
        }
        return isFull;
    }

    // determine set of full sites using depth first search
    public static void flow (final boolean[][] isOpen,
        final boolean[][] isFull, final int xCord, final int yCord) {
        final int size = isOpen.length;

        // base cases
        if (xCord < ZERO || xCord >= size) {
            return;
        } // invalid row
        if (yCord < ZERO || yCord >= size) {
            return;
        } // invalid column
        if (!isOpen[xCord][yCord]) {
            return;
        } // not an open site
        if (isFull[xCord][yCord]) {
            return;
        } // already marked as full

        // mark i-j as full
        isFull[xCord][yCord] = true;

        flow(isOpen, isFull, xCord + ONE, yCord);   // down
        flow(isOpen, isFull, xCord, yCord + ONE);   // right
        flow(isOpen, isFull, xCord, yCord - ONE);   // left
    }


    // does the system percolate?
    public static boolean percolates (final boolean[][] isOpen) {
        final int size = isOpen.length;
        final boolean[][] isFull = flow(isOpen);
        for (int count = ZERO; count < size; count++) {
            if (isFull[size - ONE][count]) {
                return true;
            }
        }
        return false;
    }

    // draw the n-by-n boolean matrix to standard draw
    public static void show (final boolean[][] array, final boolean which) {
        final int size = array.length;
        StdDraw.setXscale(-ONE, size);
        StdDraw.setYscale(-ONE, size);
        for (int xxCord = ONE; xxCord < size; xxCord++) {
            for (int yyCord = ZERO; yyCord < size; yyCord++) {
                if (array[xxCord][yyCord] == which) {
                    StdDraw.filledSquare(yyCord, size - xxCord - ONE, POINTFIVE);
                }
            }
        }
    }

    // return a random n-by-n boolean matrix, where each entry is
    // true with probability p
    public static boolean[][] random (final int n, final double p) {
        final boolean[][] a = new boolean[n][n];
        for (int i = ZERO; i < n; i++) {
            for (int j = ZERO; j < n; j++) {
                a[i][j] = StdRandom.bernoulli(p);
            }
        }
        return a;
    }


    // test client
    public static void main (final String[] args) {
        final boolean[][] isOpen = StdArrayIO.readBoolean2D();
        StdArrayIO.print(flow(isOpen));
        StdOut.println(percolates(isOpen));
    }

}
