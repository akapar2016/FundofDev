/*
 * Author:  Aayush Kapar, akapar@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 21 Josephus
 */

//package SortTest;

import java.util.List;
import java.util.ListIterator;

public final class Josephus {

    private Josephus () {}

    public static final int TEN = 10;
    public static final int ZERO = 0;
    public static final int ONE = 1;

    public static void main (final String[] args) throws ClassNotFoundException,
     InstantiationException, IllegalAccessException {

         final int valueOfN = Integer.parseInt(args[0]);
         final int count = Integer.parseInt(args[1]);
         final Class<?> clazz = Class.forName(args[2]);
         @SuppressWarnings("unchecked")

         final java.util.List<Integer> list
             = (java.util.List<Integer>) clazz.newInstance();

         for (int counter = ONE; counter <= valueOfN; counter++) {
           list.add(counter);
         }
         long startTime, endTime, total = ZERO;
         int result = ZERO;
         for (int counter = ZERO; counter < TEN; counter++) {
             startTime = System.nanoTime();
             result = josephus(list, count);
             endTime = System.nanoTime();
             total += (endTime - startTime);
         }
         final long averageTime = total / TEN;
         System.out.println("Last Soilder: " + result);
         System.out.println("Average Running Time: " + averageTime);

    }

    public static int josephus (final List<Integer> list, final int count) {
        int counter = ZERO;
        while (list.size() > ONE) {

            final ListIterator<Integer> listIte = list.listIterator();
            while (listIte.hasNext()) {
                listIte.next();
                counter++;
                if ((counter % count) == ZERO) {
                    listIte.remove();
                }
            }
        }
        return list.get(0);
    }
}
