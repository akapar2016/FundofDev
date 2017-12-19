/*
 * Author:  Aayush Kapar, akapar@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 24 Stack
 */
//package Interfaces;

import java.util.Scanner;


public final class MyStack<T> implements Stack<T> {
    int pointer = 0;
    T[] ring;
    int numElements = 0;

    @SuppressWarnings ({"unchecked"})
    public MyStack (final int size) {
        ring = (T[]) new Object[size];
    }


    public void push (final T item) throws BufferFullException {
        if (pointer == ring.length) {
            throw new BufferFullException();
        } else {
            ring[pointer] = item;
            pointer++;
            numElements++;
            //pointer = pointer % ring.length;
        }

    }

    public T pop () throws BufferEmptyException {
        if (this.isEmpty()) {
            throw new BufferEmptyException();
        } else {
            pointer--;
            numElements--;
            return ring[pointer];
        }
    }
    public boolean isEmpty () {
        return (numElements == 0);
    }

    public static void main (final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final MyStack<Integer> stack1 = new MyStack<Integer>(Integer.parseInt(args[0]));
        while (scanner.hasNext()) {
            try {
                final String myString = scanner.next();
                if (myString.equalsIgnoreCase("pop")) {
                    System.out.print("pop ");
                    System.out.println(stack1.pop());
                }
                if (myString.equalsIgnoreCase("push")) {
                    final int myInt = scanner.nextInt();
                    stack1.push(myInt);
                    System.out.println("push " + myInt);
                }
            } catch (final BufferEmptyException ex) {
                System.out.println("Stack is empty");

            } catch (final BufferFullException ex) {
                System.out.println("Stack is full");
            }
        }
    }
}
