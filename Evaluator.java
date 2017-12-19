/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Evaluator
 */

//ref Jiaqi Yang
//package Evaluator;
import java.util.Scanner;
import java.util.LinkedList;

public final class Evaluator {
    private Evaluator () {}
    public static final LinkedList<String> OPERATORS  = new LinkedList<String>();
    public static final LinkedList<Double> OPERANDS = new LinkedList<Double>();

    public static void calculate () {
        final String ope = OPERATORS.pop();
        double result = OPERANDS.pop();
        if (ope.equals("+")) {
            result = OPERANDS.pop() + result;
        } else if (ope.equals("-")) {
            result = OPERANDS.pop() - result;
        } else if (ope.equals("*")) {
            result = OPERANDS.pop() * result;
        } else if (ope.equals("/")) {
            result = OPERANDS.pop() / result;
        } else if (ope.equals("^")) {
            result = Math.pow(OPERANDS.pop(), result);
        } else if (ope.equals("sqrt")) {
            result = Math.sqrt(result);
        } else if (ope.equals("abs")) {
            result = -result;
        }
        OPERANDS.push(result);
    }

    public static void main (final String[] args) {
        final Scanner fullInput = new Scanner(System.in);

        while (fullInput.hasNextLine()) {
            final String inputed = fullInput.nextLine();
            final Scanner readInput = new Scanner(inputed);

            while (readInput.hasNext()) {
                final String inputValue = readInput.next();
                if (inputValue.equals("(")) {
                    continue;
                } else if (inputValue.equals("+") || inputValue.equals("-")
                        || inputValue.equals("*") || inputValue.equals("/")
                        || inputValue.equals("^") || inputValue.equals("sqrt")
                        || inputValue.equals("abs")) {
                    OPERATORS.push(inputValue);
                } else if (inputValue.equals(")")) {
                    calculate();
                } else {
                    OPERANDS.push(Double.parseDouble(inputValue));
                }
            }
            System.out.printf("%.2f%n", OPERANDS.pop());
        }
    }
}
