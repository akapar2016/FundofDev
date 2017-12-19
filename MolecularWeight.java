
/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Fall 2017
 * Project: 15 Molecular Weight
 */



import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public final class MolecularWeight {

    private MolecularWeight () {}
    private static final int ZERO = 0;
    private static Scanner stdInput;

    static class Entry {
        String name;
        int number;
        String symbol;
        float weight;
        Entry (final String iName, final int iNumber,
                final String iSym, final float iWeight) {
            name = iName;
            number = iNumber;
            symbol = iSym;
            weight = iWeight;
        }
    }

    public static float findWeight (final ArrayList<Entry> all, final String sym) {
        float tempWeight = ZERO;
        for (int count = ZERO; count < all.size(); count++) {
            final MolecularWeight.Entry entryTemp = all.get(count);
            if (entryTemp.symbol.equals(sym)) {
                tempWeight = entryTemp.weight;
                //System.out.println(entryTemp.name + entryTemp.number);
            }
        }
        return tempWeight;
    }

    public static void main (final String[] args) throws IOException {

        final ArrayList<Entry> all = new ArrayList<Entry>();
        final String doc = args[ZERO];
        final Scanner file = new Scanner(Paths.get(doc));

        String name = null;
        int number = ZERO;
        String symbol = null;
        float weight = ZERO;

        file.nextLine();
        file.useDelimiter(",");

        while (file.hasNextLine()) {
            name = file.next();
            number = file.nextInt();
            symbol = file.next();
            weight = file.nextFloat();
            file.nextLine();
            final MolecularWeight.Entry entry =
                    new MolecularWeight.Entry(name, number, symbol, weight);
            all.add(entry);
        }
        file.close();

        stdInput = new Scanner(System.in);

        while (true) {
            float fullW = ZERO;    //full weight
            String fullI = "";     //full input
            boolean valid = true;

            while (true) {
                String sym = "";
                sym = stdInput.next(); // take in first input

                //Condition that breaks the first loop
                if (sym.equals(".")) {
                    break;
                }

                //add input to  full input
                fullI = fullI + sym + " ";

                //assign tempWeight the weight of the first element
                final float tempWeight = findWeight(all, sym);

                // if incorrect sym is inputed, set valid bool to false
                if (tempWeight == ZERO) {
                    valid = false;
                }

                //figure out if the next value is an int
                if (stdInput.hasNextInt()) {
                    final int mult = stdInput.nextInt();
                    //add the int to full input
                    fullI = fullI + String.valueOf(mult) + " ";
                    //add the tempWeight multiplied by int
                    fullW = fullW + tempWeight * mult;
                } else {
                //add tempWeight to full weight if no int
                fullW = fullW + tempWeight;
                }
            }

            if (!valid) {
                System.out.println("Unknown Molecular equation");
            } else {
            System.out.printf("Molecular weight of " + fullI + "= " + "%.2f%n", fullW);
            }
        }
    }
}
