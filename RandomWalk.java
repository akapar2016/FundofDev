package Walk;
/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Fall 2017
 * Project: 11 RandomWalk
 */


/******************************************************************************


 *  Compilation:  javac SelfAvoidingWalk.java
 *  Execution:    java -Dseed=5463785 SelfAvoidingWalk n trials
 *
 *  Generate trials self-avoiding walks of length n.
 *  Report the fraction of time the random walk is non self-intersecting.
 *
 ******************************************************************************/

import java.util.Random;

public final class RandomWalk {
    private RandomWalk () {}
    private static final Random RNG = new Random
            (Long.getLong ("seed", System.nanoTime()));

    private static final int ZERO = 0;
    private static final double ZEROZEROTWO = .002;
    private static final double ZEROONE = .01;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int ONEZEROZERO = 100;
    private static final double POINTTWOFIVE = .25;
    private static final double POINTFIVE = .5;
    private static final double POINTSEVENFIVE = .75;
    private static final double ONEPOINT = 1.0;

    public static void main (final String[] args) {
        final int gridSize = Integer.parseInt(args[0]);   //  size
        final int trials = Integer.parseInt(args[1]);     // number of trials
        int deadEnds = 0;   // trials resulting in a dead end

        // simulate trials self-avoiding walks
        for (int count = 0; count < trials; count++) {
            final boolean[][] beenThere =
                    new boolean[gridSize][gridSize]; // intersections visited
            int valueXEnd = gridSize / TWO;
            int valueYEnd = gridSize / TWO; // current position
            int valueXBefore, valueYBefore;

            StdDraw.clear(); //clear everything before after each trial

            //grid settings
            StdDraw.setPenRadius(ZEROZEROTWO);    //set grid line width = 0.002
            StdDraw.setPenColor(StdDraw.GRAY);  //set grid line color = GRAY

            //print grid
            StdDraw.setScale(0, gridSize + ONE);
            for (int draw = 1; draw <= gridSize; draw++) {
                StdDraw.line(0, draw, gridSize, draw); // horizontal
                StdDraw.line(draw, 0, draw, gridSize); // vertical
            }

            //dog drawing settings
            StdDraw.setPenRadius(ZEROONE);    //set grid line width = 0.01
            StdDraw.setPenColor(StdDraw.RED);  //set grid line color = RED

            // repeatedly take a random step, unless you've already escaped
            while (valueXEnd > ZERO && valueXEnd < gridSize - ONE
                    && valueYEnd > ZERO && valueYEnd < gridSize - ONE)  {

                // dead-end, so break out of loop
                if (beenThere[valueXEnd - ONE][valueYEnd]
                        && beenThere[valueXEnd + ONE][valueYEnd]
                        && beenThere[valueXEnd][valueYEnd - ONE]
                        && beenThere[valueXEnd][valueYEnd + ONE]) {
                    deadEnds++;
                    break;
                }

                // mark (x, y) as visited
                beenThere[valueXEnd][valueYEnd] = true;

                // take a random step to unvisited neighbor
                final double randomValue = RNG.nextDouble();
                valueXBefore = valueXEnd;
                valueYBefore = valueYEnd;

                if (randomValue < POINTTWOFIVE) {
                    if (!beenThere[valueXEnd + 1][valueYEnd]) {
                        valueXEnd++;
                    }
                } else if (randomValue < POINTFIVE) {
                    if (!beenThere[valueXEnd - ONE][valueYEnd]) {
                        valueXEnd--;
                    }
                } else if (randomValue < POINTSEVENFIVE) {
                    if (!beenThere[valueXEnd][valueYEnd + ONE]) {
                        valueYEnd++;
                    }
                } else if (randomValue < ONEPOINT) {
                    if (!beenThere[valueXEnd][valueYEnd - ONE]) {
                        valueYEnd--;
                    }
                }
                StdDraw.line(valueXBefore, valueYBefore, valueXEnd, valueYEnd);
            }
        }

        //StdDraw.save("path.png");
        System.out.println(ONEZEROZERO * deadEnds / trials + "% dead ends");
    }
}
