/*
 * Author:  Aayush Kapar, akapar@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 22 textmining
 */

//package testmining;

/*************************************************************************
 *  Program 3.3.4, page 441
 *
 *  % java Document genomeA.txt 2 16
 *
 *
 *************************************************************************/

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.BiFunction;

public final class Document {
   private final Vector profile;     // unit vector (normalized)

   private Document (final Vector v) {
      profile = v;
   }

   public static Document createDocument
       (final String name, final int k, final int dimension) throws IOException {
      final byte[] a = IOUtils.readAllBytes (name);
      final String text = new String(a, StandardCharsets.UTF_8);
      return createDocumentFrom (text, k, dimension);
   }

   public static Document createDocumentFrom
       (final String text, final int k, final int dimension) {
      assert 0 < k; assert 0 < dimension;
      final int length = text.length();
      final double[] frequency = new double[dimension];
      for (int count = 0; count < dimension; count++) {
          frequency[count] = 0;
      }

      int index = k;
      //System.out.println ("length: " + length);
      while (index < length) {
          final String sub = text.substring(index - k, index);
          int hashSub = sub.hashCode();
          hashSub = (hashSub < 0) ? -hashSub : hashSub;
          if (hashSub > dimension) {
              hashSub = hashSub % dimension;
          }
          //System.out.println (hashSub);
          frequency[hashSub]++;
          index = index + 1;
          //System.out.println (index);
      }

      return new Document ((new Vector(frequency)).direction());
   }

   // Rename BiFunction interface
   public interface Metric extends BiFunction<Document, Document, Double> { }

   public static final Metric COS    = (a, b) -> a.profile.dot (b.profile);
   public static final Metric EUCLID = (a, b) -> a.profile.minus (b.profile).magnitude();
   public static final Metric G_TEST = (a, b) -> a.profile.gTest (b.profile);

   public static double cosineSimilarityMeasure (final Document a, final Document b) {
      return a.profile.dot (b.profile);
   }

   public static double euclideanDistance (final Document a, final Document b) {
       final Vector difference = a.profile.minus(b.profile);
       return difference.magnitude();
   }

   public static void main (final String[] args) throws IOException {
       final byte[] a = IOUtils.readAllBytes (args[0]);
       final int k = Integer.parseInt(args[1]);  // k-grams
       final int d = Integer.parseInt(args[2]);  // dimension
       final String text = new String(a, StandardCharsets.UTF_8);
       final Document doc = Document.createDocument (text, k, d);
       System.out.println (doc.profile);
    }
}
