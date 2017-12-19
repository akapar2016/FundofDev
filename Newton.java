/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 17 Newton
 */
//package Newton;
//import Walk.StdDraw;

public final class Newton {
    private Newton () {}

    private static final double ONEZEROZERO = 100;
    private static final double ONE = 1;
    private static final double NONE = -1;
    private static final double ZERO = 0;
    private static final double FOUR = 4;
    private static final double ZZONE = .001;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int IFOUR = 4;


    public static void main (final String[] args) {
        StdDraw.show(0);
        final int size = Integer.parseInt(args[0]);
        StdDraw.setCanvasSize(size, size);
        StdDraw.setXscale(-ONE, ONE);
        StdDraw.setYscale(-ONE, ONE);
        //StdDraw.setPenRadius(ONE / size);


        for (double iyCount = 0; iyCount < size; iyCount++) {
            for (double ixCount = 0; ixCount < size; ixCount++) {

                Complex izVal = new Complex(-ONE + ixCount / (size / TWO),
                        -ONE + iyCount / (size / TWO));
                Complex endXVal;
                final Complex one = new Complex(ONE, ZERO);
                final Complex four = new Complex(FOUR, ZERO);

                for (double count = ZERO; count < ONEZEROZERO; count++) {
                    final Complex function = izVal.power(IFOUR).minus(one);
                    final Complex derFunction = four.times(izVal.power(THREE));
                    endXVal = izVal.minus(function.divides(derFunction));
                    izVal = endXVal;
                }

                if (izVal.re - ONE <= ZZONE &&  izVal.re - ONE >= -ZZONE
                        && izVal.im <= ZZONE && izVal.im >= -ZZONE) {
                    StdDraw.setPenColor(StdDraw.YELLOW);

                } else if (izVal.re - NONE <= ZZONE &&  izVal.re - NONE >= -ZZONE
                        && izVal.im <= ZZONE && izVal.im >= -ZZONE) {
                    StdDraw.setPenColor(StdDraw.BLUE);

                } else if (izVal.re <= ZZONE &&  izVal.re >= -ZZONE
                        && izVal.im - ONE <= ZZONE && izVal.im - ONE >= -ZZONE) {
                    StdDraw.setPenColor(StdDraw.RED);

                } else if (izVal.re <= ZZONE &&  izVal.re >= -ZZONE
                        && izVal.im - NONE <= ZZONE && izVal.im - NONE >= -ZZONE) {
                    StdDraw.setPenColor(StdDraw.GREEN);
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                StdDraw.point(-ONE + ixCount / (size / TWO),
                        -ONE + iyCount / (size / TWO));
            }
        }
        StdDraw.show();
    }
}
