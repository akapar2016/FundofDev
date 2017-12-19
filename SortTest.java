/*
 * Author:  Aayush Kapar, akapar@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 20 Peformance
 */

// help from Jiaqi Yang
//package SortTest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class SortTest {
    private SortTest () {}

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWOFIVE = 25;
    private static Random rnd = new Random
            (Long.getLong("seed", System.nanoTime()));

    public static void sort (final List<Integer> data) {
        int min, temp = Integer.MAX_VALUE;  // DELETE
        for (int count = ZERO; count < data.size(); count++) {
            /*
            find the min element in the unsorted data[i, i+1, .., n-1]
             assume initially that min is the first element in the range
            */
            min = count;

            for (int count2 = count + ONE; count2 < data.size(); count2++) {
                /*if the 'j'th element is less, then it is the new minimum*/
                if (data.get(min) > data.get(count2)) {
                    /* found new minimum; remember its index88*/
                    min = count2;
                }
            }
            /* swapping */
            temp = data.get(min);
            data.set(min, data.get(count));
            data.set(count, temp);
        }
    }

    public static void main (final String[] args) throws ClassNotFoundException,
        InstantiationException, IllegalAccessException {

        final int valueOfN = Integer.parseInt(args[0]);

        //copied
        final Class<?> clazz = Class.forName(args[1]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list
            = (java.util.List<Integer>) clazz.newInstance();

        for (int count = ZERO; count < valueOfN; count++) {
          list.add(count);
        }

        long startTime, endTime, total = ZERO;

        for (int count = ZERO; count < TWOFIVE; count++) {
          // Shuffle
          Collections.shuffle(list, rnd);
          startTime = System.nanoTime();
          sort(list);
          endTime = System.nanoTime();
          total += (endTime - startTime);
      }
      final long averageTime = total / TWOFIVE;
      System.out.println(averageTime);
    }
}
