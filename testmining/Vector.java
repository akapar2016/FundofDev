/*************************************************************************
 *
 *  Implementation of a vector of real numbers.
 *
 *  This class is implemented to be immutable: once the client program
 *  initializes a Vector, it cannot change any of its fields
 *  (N or data[i]) either directly or indirectly. Immutability is a
 *  very desirable feature of a data type.
 *
 *  % java Vector
 *  x        = ( 1.0 2.0 3.0 4.0 )
 *  y        = ( 5.0 2.0 4.0 1.0 )
 *  x + y    = ( 6.0 4.0 7.0 5.0 )
 *  10x      = ( 10.0 20.0 30.0 40.0 )
 *  |x|      = 5.477225575051661
 *  <x, y>   = 25.0
 *  |x - y|  = 5.0990195135927845
 *
 *
 *  Note that Vector is also the name of an unrelated Java library class.
 *
 *************************************************************************/
import java.util.Arrays;

public final class Vector {
   final int length;              // length of the vector
   private final double[] data;   // array of vector's components

   // create the zero vector of length n
   public Vector (final int l) {
      this.length = l;
      this.data = new double[l];
   }

   // create a vector from the array d
   public Vector (final double[] d) {
      this.length = d.length;
      this.data = Arrays.copyOf (d, d.length);
   }

   // return the inner product of this Vector a and b
    public double dot (final Vector b) {
        if (this.length != b.length)  { throw new RuntimeException("Dimensions don't agree"); }
        double sum = 0.0;
        for (int i = 0; i < this.length; i++) {
            sum = sum + (this.data[i] * b.data[i]);
        }
        return sum;
    }

    // return the Euclidean norm of this Vector a
    public double magnitude() {
        return Math.sqrt (this.dot(this));
    }

    // return the corresponding unit vector
    public Vector direction() {
       final Vector a = this;
       return a.times(1.0 / a.magnitude());
    }

    // return a + b
    public Vector plus (final Vector b) {
       final Vector a = this;
       if (a.length != b.length) { throw new RuntimeException("Dimensions don't agree"); }
       final Vector c = new Vector (a.length);
       for (int i = 0; i < c.length; i++) {
            c.data[i] = a.data[i] + b.data[i];
       }
       return c;
    }

    // return a - b
    public Vector minus (final Vector b) {
       final Vector a = this;
       if (a.length != b.length) { throw new RuntimeException("Dimensions don't agree"); }
       final Vector c = new Vector (a.length);
       for (int i = 0; i < c.length; i++) {
          c.data[i] = a.data[i] - b.data[i];
       }
       return c;
    }

    // create and return a new object whose value is (this * factor)
    public Vector times (final double factor) {
       final Vector c = new Vector (this.length);
       for (int i = 0; i < c.length; i++) {
          c.data[i] = factor * data[i];
       }
       return c;
    }


   // In statistics, G-tests are likelihood-ratio or maximum likelihood statistical significance tests 
   private static final double EPS = 1E-8D;
   public double gTest (Vector b) {
      final Vector a = this;
      double sum = 0.0;
      for  (int i = 0; i < b.length; i++) {
         if ( Math.abs(a.data[i]) < EPS || Math.abs(b.data[i]) < EPS) continue;
         sum += a.data[i] * Math.log (a.data[i] / b.data[i]);
      }
      return 2 * sum;
   }


    // return the corresponding unit vector
    public double cartesian (final int i) {
        return data[i];
    }

   private static final String FORMAT = "%.2f";

   // return a string representation of the vector
   public String toString() {
      final StringBuilder s = new StringBuilder ();
      s.append ('(');
      for (int i = 0; i < this.length-1; i++) {
         s.append (String.format (FORMAT, data[i]));
         s.append (',');
      }
      if (data.length > 0) s.append (String.format (FORMAT, data[data.length-1]));
      s.append (')');
      return s.toString();
   }

    // unit test client
    public static void main (final String[] args) {
       final double[] xdata = { 1.0, 2.0, 3.0, 4.0 };
       final double[] ydata = { 5.0, 2.0, 4.0, 1.0 };

       final Vector x = new Vector (xdata);
       final Vector y = new Vector (ydata);

       System.out.println("x        = " + x);
       System.out.println("y        = " + y);
       System.out.println("x + y    = " + x.plus(y));
       System.out.println("10x      = " + x.times(10.0));
       System.out.println("|x|      = " + x.magnitude());
       System.out.println("<x, y>   = " + x.dot(y));
       System.out.println("|x - y|  = " + x.minus(y).magnitude());
    }
}
