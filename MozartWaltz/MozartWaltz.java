/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Fall 2017
 * Project: MozartWaltz
 */

//Array for minute and trio from
//https://introcs.cs.princeton.edu/java/15inout/Mozart.java

//package MozartWaltz;
//import Percolation.StdOut;

import java.util.ArrayList;
import java.util.Random;

public final class MozartWaltz {
    private MozartWaltz () {}
    public static final int ZERO = 0;
    public static final int SIX = 6;
    public static final int SIXTEEN = 16;
    private static final Random RNG = new Random
            (Long.getLong ("seed", System.nanoTime()));

    public static void main (final String[] args) {

        final int[][] minuet = {
            {96,   32,  69,  40, 148, 104, 152, 119,  98,   3,  54 },
            {22,    6,  95,  17,  74, 157,  60,  84, 142,  87, 130 },
            {141, 128, 158, 113, 163,  27, 171, 114,  42, 165,  10 },
            {41,   63,  13,  85,  45, 167,  53,  50, 156,  61, 103 },
            {105, 146, 153, 161,  80, 154,  99, 140,  75, 135,  28 },
            {122,  46,  55,   2,  97,  68, 133,  86, 129,  47,  37 },
            {11,  134, 110, 159,  36, 118,  21, 169,  62, 147, 106 },
            {30,   81,  24, 100, 107,  91, 127,  94, 123,  33,   5 },
            {70,  117,  66,  90,  25, 138,  16, 120,  65, 102,  35 },
            {121,  39, 139, 176, 143,  71, 155,  88,  77,   4,  20 },
            {26,  126,  15,   7,  64, 150,  57,  48,  19,  31, 108 },
            {9,    56, 132,  34, 125,  29, 175, 166,  82, 164,  92 },
            {112, 174,  73,  67,  76, 101,  43,  51, 137, 144,  12 },
            {49,   18,  58, 160, 136, 162, 168, 115,  38,  59, 124 },
            {109, 116, 145,  52,   1,  23,  89,  72, 149, 173,  44 },
            {14,   83,  79, 170,  93, 151, 172, 111,   8,  78, 131 },

        };

        final int[][] trio = {
            {72, 56, 75, 40, 83, 18 },
            {6,  82, 39, 73,  3, 45 },
            {59, 42, 54, 16, 28, 62 },
            {25, 74,  1, 68, 53, 38 },
            {81, 14, 65, 29, 37,  4 },
            {41,  7, 43, 55, 17, 27 },
            {89, 26, 15,  2, 44, 52 },
            {13, 71, 80, 61, 70, 94 },
            {36, 76,  9, 22, 63, 11 },
            {5,  20, 34, 67, 85, 92 },
            {46, 64, 93, 49, 32, 24 },
            {79, 84, 48, 77, 96, 86 },
            {30,  8, 69, 57, 12, 51 },
            {95, 35, 58, 87, 23, 60 },
            {19, 47, 90, 33, 50, 78 },
            {66, 88, 21, 10, 91, 31 }
        };

        final ArrayList<Double> output = new ArrayList<Double>();
        System.out.print("Minuets:");
        int size = ZERO;

        for (int measure = ZERO; measure < SIXTEEN; measure++) {

            final int sum = RNG.nextInt(SIX) + RNG.nextInt(SIX);

            StdOut.print(" " + minuet[measure][sum]);
            final String filename = "wav/M" + minuet[measure][sum] + ".wav";

            final double[] data = StdAudio.read(filename);
            size += data.length;
            for (int count = ZERO; count < data.length; count++) {
                output.add(data[count]);
            }
        }

        System.out.println();
        System.out.print("Trio:");

        for (int measure = ZERO; measure < SIXTEEN; measure++) {

            final int die = RNG.nextInt(SIX);

            final  String filename = "wav/T" + trio[measure][die] + ".wav";
            StdOut.print(" " + trio[measure][die]);

            final double[] data = StdAudio.read(filename);
            size += data.length;
            for (int count = ZERO; count < data.length; count++) {
                output.add(data[count]);
            }
        }

        final double[] toFile = new double[output.size()];
        for (int count = 0; count < output.size(); count++) {
            toFile[count] = output.get(count);
        }
        System.out.println();
        System.out.println("Music size: " + size);
        StdAudio.save("mozart.wav", toFile);

    }
}

