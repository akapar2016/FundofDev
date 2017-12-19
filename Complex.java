package Newton;

import java.util.Scanner;



public class Complex {

    public final double re;   // the real part

    public final double im;   // the imaginary part



    public Complex (final double real, final double imag) {

        re = real;

        im = imag;

    }



    public final String toString () {

        return String.format ("%.2f %c %.2fi",

            re, im >=0 ? '+':'-', Math.abs (im) );

    }



    public final double abs () { // Math.sqrt(re*re + im*im)

        return Math.hypot (re, im);

    }



    public final Complex plus (final Complex b) {

        double real = re + b.re;

        double imag = im + b.im;

        return new Complex (real, imag);

    }



    public final Complex times (final Complex b) {

        double real = re * b.re - im * b.im;

        double imag = re * b.im + im * b.re;

        return new Complex (real, imag);

    }

    

    public double theta(){

        if(re > 0 ){

            return Math.atan(im/re);

        }

        else if(re < 0 && im >= 0){

            return Math.atan(im/re) + Math.PI;

        }

        else{

            return Math.atan(im/re) - Math.PI;

        }

    }

    

    public Complex minus(final Complex b){

        return new Complex(re - b.re, im - b.im);

    }

    

    public Complex conjugate(){

        return new Complex(re, -im);

    }

    

    public Complex divides(final Complex b){

        final double divisor = (b.re * b.re) + (b.im * b.im);

        return new Complex(((re * b.re) + (im * b.im)) / divisor, ((im * b.re) - (re * b.im)) / divisor);

    }

    

    public Complex power(final int b){

        Complex product = new Complex(re, im);
        
        if (b == 0) {
            
            return new Complex(1, 0);
            
        } else {
            
            for (int i = 1; i < b; i++) {
                
                product = product.times(this);
                
            }
        }

        return product;

    }

    

   public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        final Complex a = new Complex(input.nextDouble(), input.nextDouble());

        final Complex b = new Complex(input.nextDouble(), input.nextDouble());

        System.out.println(a.theta());

        System.out.println(b.theta());

        System.out.println(a.minus(b));

        System.out.println(a.conjugate());

        System.out.println(a.divides(b));

        System.out.println(a.power(2));

        System.out.println(a.power(3));

    }

}

