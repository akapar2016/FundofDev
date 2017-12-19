/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 27 door
 */
//package compare;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;

public final class Door {
    private Door () {}
    static final int ZERO = 0;
    static final int ONE = 1;
    private static Scanner input;
    static final class Student implements Comparable<Student> {
        private String firstN;
        private String lastN;
        private String email;
        private int id;

        Student(final String first, final String last,
                final String emailS, final String idS) {
            this.firstN = first;
            this.lastN = last;
            this.email = emailS;
            this.id = Integer.parseInt(idS);
        }
        public int compareTo (final Student o) {
        if (this.id == o.id) {
            return ZERO;
        }
        return this.id > o.id ? ONE : -ONE;
        }
    }

    // Use binary search to find 'v' from 'first' to 'last-1' (inclusive)
    public static <T extends Comparable<T>> int search
            (final T v, final ArrayList<T> data, final int first, final int last) {
        int index = ZERO;
        boolean isThere = false;
        int indF = first;
        int indL = last - ONE;
        while (indF <= indL) {
            final int mid = (indF + indL) / (ONE + ONE);
            if (data.get(mid).compareTo(v) < ZERO) {
                indF = mid + ONE;
            } else if (data.get(mid).compareTo(v) > ZERO) {
                indL = mid - ONE;
            } else if (data.get(mid).compareTo(v) == ZERO) {
                index = mid;
                isThere = true;
                break;
            }
        }

        if (isThere) {
            return index;
        } else {
            return -ONE;
        }
    }

    public static void main (final String[] args) throws IOException {
        final ArrayList<Door.Student> students = new ArrayList<Door.Student>();
        try (
            final Scanner data =
                new Scanner(new BufferedReader
                        (new InputStreamReader(new URL(args[0]).openStream())));
        ) {
            while (data.hasNextLine()) {
                final String[] fields = data.nextLine().split ("\\s*,\\s*");
                final Door.Student stu = new Student
                        (fields[ONE], fields[ONE + ONE],
                                fields[ONE + ONE + ONE],
                                fields[ONE + ONE + ONE + ONE + ONE + ONE]);
                students.add(stu);
            }

        }
        input = new Scanner(System.in);
        while (input.hasNextLine()) {
            final String idVal = input.nextLine();
            final Door.Student searchStu = new Student(null, null, null, idVal);
            final int index = search(searchStu, students, ZERO, students.size() - 1);
            //System.out.println("Index = " + index);
            if (index == -ONE) {
                final int idV = Integer.parseInt(idVal);
                System.out.printf("%09d", idV);
                System.out.println(" not found");
            } else {
                System.out.printf("%09d", students.get(index).id);
                System.out.print("; " + students.get(index).firstN
                        + " " + students.get(index).lastN
                        + "; " + students.get(index).email);
                System.out.println("");
            }
        }
    }
}
