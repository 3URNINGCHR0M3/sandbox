package net.forje.common.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Set of utility methods for working with stream objects.
 */
public class Streams {

    /**
     * Closes the InputStream parameter, handles null InputStream objects.  Reports any exceptions that occur.
     *
     * @param inputStream the InputStream to close
     */
    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Flushes and closes the OutputStream parameter, handles null OutputStream objects.  Reports any exceptions that occur.
     *
     * @param outputStream the OutputStream to close
     */
    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Copies the content of the inputStream to the outputStream.
     *
     * @param inputStream  the InputStream to read from
     * @param outputStream the OutputStream to write to
     * @throws IOException if any errors occur working with the streams
     */
    public static void copy(final InputStream inputStream,
                            final OutputStream outputStream)
            throws IOException {

        final StreamCoupler streamCoupler = new StreamCoupler(inputStream, outputStream);
        streamCoupler.process();

    }

    /**
     * Return the contents of the InputStream as a String.  This code makes no attempt to make
     * sure that the contents of the InputStream is character data.
     *
     * @param inputStream the InputStream to read from
     * @return the contents of the InputStream as a String
     * @throws IOException if any errors occur while working with the stream
     */
    public static String readAsString(InputStream inputStream)
            throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        copy(inputStream, baos);

        baos.flush();
        baos.close();

        return baos.toString();

    }

    /**
     * Return the contents of the InputStream as a byte array.
     *
     * @param inputStream the InputStream to read from
     * @return the contents of the InputStream as a byte array
     * @throws IOException if any errors occur while working with the stream
     */
    public static byte[] readAsBytes(final InputStream inputStream) throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        copy(inputStream, baos);

        baos.flush();
        baos.close();

        return baos.toByteArray();

    }

}
