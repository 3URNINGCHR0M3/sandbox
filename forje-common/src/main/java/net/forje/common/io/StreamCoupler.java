package net.forje.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Automates the process of copying the contents of an InputStream to an OutputStream.  Calling routine is
 * responsible for flushing and closing streams.
 */
public class StreamCoupler {

    /**
     * The InputStream to read from.
     */
    private final InputStream _inputStream;

    /**
     * The OutputStream to write to.
     */
    private final OutputStream _outputStream;

    /**
     * Create a new StreamCoupler around a pair of streams.
     *
     * @param inputStream the stream to read from
     * @param outputStream the stream to read
     */
    public StreamCoupler(final InputStream inputStream,
                         final OutputStream outputStream) {
        _inputStream = inputStream;
        _outputStream = outputStream;
    }


    /**
     * Copy the contents of the InputStream to the OutputStream
     *
     * @throws IOException if any errors occur working with the streams
     */
    public void process() throws IOException {

        int i = -1;

        while ((i = _inputStream.read()) >= 0) {
            _outputStream.write(i);
        }

    }

}
