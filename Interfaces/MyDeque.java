/*
 * Author:  Aayush Kapar, akapar@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 24 Stack
 */
//package Interfaces;

import java.util.Scanner;

public final class MyDeque<T> implements Deque<T> {
    private int firstIndex;
    private int lastIndex;
    private int numElements;
    T[] ring;

    @SuppressWarnings("unchecked")
    public MyDeque (final int size) {
        numElements = size;
        ring = (T[]) new Object[size];
    }
    public T removeLast () throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        final int last = (lastIndex - 1) % numElements;
        final T element = ring[last];
        ring[last] = null;
        lastIndex--;
        return element;
    }

    public T removeFirst () throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        final int first = firstIndex % numElements;
        final T element = ring[first];
        ring[first] = null;
        firstIndex++;
        return element;
    }
    public void addFirst (final T item) throws BufferFullException {
        if ((lastIndex - firstIndex) == ring.length) {
            throw new BufferFullException();
        }
        final int first = firstIndex % numElements;
        for (int count = lastIndex; count > firstIndex; count--) {
            ring[count % numElements] = ring[(count - 1) % numElements];
        }
        ring[first] = item;
        lastIndex++;
    }

    public void addLast (final T item) throws BufferFullException {
        if ((lastIndex - firstIndex) == ring.length) {
            throw new BufferFullException();
        }
        final int last = lastIndex % numElements;
        ring[last] = item;
        lastIndex++;
    }
    public boolean isEmpty () {
        return firstIndex == lastIndex;
    }
    public static void main (final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final MyDeque<Integer> theDeque = new MyDeque<Integer>(Integer.parseInt(args[0]));

        while (scanner.hasNext()) {
            final String myString = scanner.next();
            try {
                if (myString.equalsIgnoreCase("removefirst")) {
                    System.out.println("removefirst " + theDeque.removeFirst());
                }
                if (myString.equalsIgnoreCase("removelast")) {
                    System.out.println("removelast " + theDeque.removeLast());
                }
                if (myString.equalsIgnoreCase("addfirst")) {
                    final int elements = scanner.nextInt();
                    theDeque.addFirst(elements);
                    System.out.println("addfirst " + elements);
                 }
                if (myString.equalsIgnoreCase("addlast")) {
                    final int elements = scanner.nextInt();
                    theDeque.addLast(elements);
                    System.out.println("addlast " + elements);
                 }
            } catch (final BufferEmptyException ex) {
                System.out.println("Deque is empty");

            } catch (final BufferFullException ex) {
                System.out.println("Deque is Full");
            }
        }
    }
}
