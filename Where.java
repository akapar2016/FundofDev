/*
 * Author:  Aayush Kapar, akapar@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Where
 */
//package where;
import java.util.ArrayDeque;
import java.util.Scanner;
public final class Where {
    private Where () {}
    private static final int ZERO = 0;
    public static void main (final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Integer nnn = scanner.nextInt();
        final Integer kkk = scanner.nextInt();
        final Integer qqq = scanner.nextInt();
        final ArrayDeque<Integer> arr = new ArrayDeque<Integer>(nnn);
        for (int count = ZERO; count < nnn; count++) {
            arr.add(scanner.nextInt());
        }
        for (int count = ZERO; count < kkk; count++) {
            final Integer temp = arr.getLast();
            arr.removeLast();
            arr.addFirst(temp);
        }
        final Integer[] arra = new Integer[nnn];
        arr.toArray(arra);
        for (int count = ZERO; count < qqq; count++) {
            final int index = scanner.nextInt();
            System.out.println(arra[index]);
        }
    }
}
