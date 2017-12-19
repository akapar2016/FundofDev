package testmining;

import java.net.URL;
import java.net.MalformedURLException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

public final class IOUtils {
   private IOUtils () {}

   public static byte[] readAllBytes (final String name) throws IOException {
      byte[] bytes = null;
      try {
         bytes = IOUtils.readAllBytesFromURL (name);
      } catch (final MalformedURLException x) {
         // ignore
      }
      if (bytes==null) {
         // Bad URL; try local file system path
         return IOUtils.readAllBytesFromPath (name);
      } else {
         return bytes;
      }
   }

   public static byte[] readAllBytesFromPath (final String name) throws IOException {
      final Path path = Paths.get (name);
      return readAllBytesFromPath (path);
   }

   public static byte[] readAllBytesFromPath (final Path path) throws IOException {
      return Files.readAllBytes (path);
   }

   public static byte[] readAllBytesFromURL (final String name) throws MalformedURLException {
      final URL url = new URL(name);
      byte[] bytes = null;
      try (final InputStream is = url.openStream()) {
            bytes = readAllBytes (is);
      } catch (final IOException e) {
         // Ignore
      }
      return bytes;
   }

   private static final int BUFFER_SIZE = 5_000;
   private static final byte[] BUFFER = new byte[BUFFER_SIZE];

   private static byte[] readAllBytes (final InputStream inputStream) throws IOException {
      final ByteArrayOutputStream baos = new ByteArrayOutputStream();
      for (;;) {
         final int read = inputStream.read(BUFFER, 0, BUFFER.length);
         if (read == -1) break;
         baos.write (BUFFER, 0, read);
      }
      baos.flush();
      return  baos.toByteArray();
   }
}
