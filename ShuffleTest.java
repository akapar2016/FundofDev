
/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Fall 2017
 * Project: 07 ShuffleTest
 */


import java.util.Random;

public final class ShuffleTest {
    private ShuffleTest () {}

    private static final Random RNG = new Random
            (Long.getLong("seed", System.nanoTime()));

    public static final int ZERO = 0;

    public static void main (final String[] args) {

        //inputs
        final int numberOfShuffles = Integer.parseInt(args[0]);
        final int arrayLength = Integer.parseInt(args[1]);

        //the array used to locate which position to add 1 to on result 2d array
        final int[] perm = new int[arrayLength];

        //the final 2d array that will be printed
        final int[][] result = new int[arrayLength][arrayLength];

        //repeat all according to input
        for (int count = ZERO; count < numberOfShuffles; count++) {

            //initialize perm array before every shuffle
            for (int initial = ZERO; initial < arrayLength; initial++) {
                perm[initial] = initial;
            }

            //shuffle perm array
            for (int shuffle = ZERO; shuffle < arrayLength; shuffle++) {
                final int r = shuffle + RNG.nextInt(arrayLength - shuffle);
                final int temp = perm[shuffle];
                perm[shuffle] = perm[r];
                perm[r] = temp;
            }

            //change result 2d array according to shuffled perm array
            for (int countArray = ZERO; countArray < arrayLength; countArray++) {
                result[perm[countArray]][countArray]++;
            }
        }

        //print 2d result array
        for (int countX = 0; countX < arrayLength; countX++) {
            for (int countY = 0; countY < arrayLength; countY++) {
                System.out.print(result[countX][countY] + " ");
            }
            System.out.println("");

        }
    }
}
