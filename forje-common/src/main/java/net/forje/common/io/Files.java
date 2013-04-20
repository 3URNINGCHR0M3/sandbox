package net.forje.common.io;

import java.io.File;
import java.io.FileNotFoundException;

public class Files {

    /**
     * Returns the extension of the file passed in.
     *
     * @param fileName the name of a file as a String
     *
     * @return the extension of the file name
     */
    public static String getExtension(final String fileName) {
        final int i = fileName.lastIndexOf('.');
        return fileName.substring(i+1);
    }

    /**
     * Returns the extension of the file passed in.
     *
     * @param file the File object representing the file on disc
     *
     * @return the extension of the file
     */
    public static String getExtension(final File file) {
        final String fileName = file.getName();
        return getExtension(fileName);
    }

    /**
     * Checks to see if the File object exists, throws an FileNotFoundException if the file does not exist
     *
     * @param file the File object to check on
     *
     * @throws FileNotFoundException if the file does not exist
     */
    public static void verifyExists(final File file) throws FileNotFoundException {

        if (!file.exists()) {
            throw new FileNotFoundException("file " + file.getAbsolutePath() + " does not exist");
        }

    }

}
