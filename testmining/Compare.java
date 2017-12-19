/*
 * Author:  Aayush Kapar, akapar@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 22 textmining
 */

//package testmining;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public final class Compare {
    private Compare () {}
    public static final int BIGNUMBER = 999999999;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    private static Scanner sc;

    public static void main (final String[] args) throws IOException {
        final int kkk = Integer.parseInt(args[0]);  // k-grams
        final int dimension = Integer.parseInt(args[1]);  // dimension
        final String stringFile = args[2];
        final File file = new File(stringFile);

        sc = new Scanner(file);
        final int amount = sc.nextInt();
        final String[] files = new String[amount];
        final Document[] docs = new Document[amount];

        for (int count = ZERO; count < amount; count++) {
            files[count] = sc.next();
        }

        for (int count = ZERO; count < amount; count++) {
            docs[count] = Document.createDocument(files[count], kkk, dimension);
            final int index = files[count].lastIndexOf('/');
            files[count] = files[count].substring(index + ONE);
        }

        for (int count = ZERO; count < amount; count++) {
            double min = BIGNUMBER;
            int minIndex = ZERO;
            for (int count2 = ZERO; count2 < amount; count2++) {
                final double compare =
                        Document.euclideanDistance(docs[count], docs[count2]);
                if (!files[count].equals(files[count2]) && compare < min) {
                    minIndex = count2;
                    min = compare;
                }
            }
            System.out.print(files[count] + " " + files[minIndex] + " ");
            System.out.printf("%.2f %n", min);
        }
    }
}
