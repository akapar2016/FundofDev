package hammingDistance;
/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Fall 2017
 * Project: 13 recursion
 */

public final class HammingDistance {
   // private HammingDistance () {}
    

    private static final int ZERO = 0;
  
    public static void main (final String[] args) {
        final int kValue = Integer.parseInt(args[0]);
        final int sValue = Integer.parseInt(args[1],2);
        final String sBinary = args[1];
        String largestBinary = "";
        for (int count = ZERO; count < sBinary.length(); count++){
            largestBinary = largestBinary + "1";
        }
        final int largestValue = Integer.parseInt(largestBinary,2);
        final int smallestValue = ZERO;
        
        System.out.println(kValue);
        System.out.println(sValue);
        System.out.println(sBinary);
        System.out.println(largestBinary);
        System.out.println(largestValue);
        System.out.println(smallestValue);
        
        hamming(kValue, sBinary, sValue, largestValue, smallestValue);
        
    }
    
    public static String intToBinary (int input, int sValueLength) {
        String binary = "";
        for(int count = ZERO; count < sValueLength; ++count, input/=2) {
           switch (input % 2) {
              case 0:
                 binary = "0" + binary;
              case 1:
                 binary = "1" + binary;
           }
        }
        return binary;
     }
    
    public static int getHammingDistance(String binary1, String binary2) {
        int distance = 0;
        for (int x = 0; x < binary1.length(); x++) {
            for (int y = 0; y < binary2.length(); y++) {
                if (binary1.charAt(x) != binary2.charAt(y)) {
                    distance ++;
                }
            }
        }
        return distance;
    }

    public static void hamming(int kValue, String sBinary,int sValue, int largestValue, int smallestValue) {
        if (smallestValue < largestValue) {
            String valueBinary = intToBinary(smallestValue, sBinary.length());
            int hammingDistance = getHammingDistance(valueBinary, sBinary);
            if (hammingDistance <= kValue){
                System.out.println(valueBinary);
            }
            hamming(kValue, sBinary, sValue, largestValue, smallestValue + 1);
        }

    }
}