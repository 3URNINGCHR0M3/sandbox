package net.forje.common.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class FilesTest {

    @Test
    public void testGetExtensionString() throws Exception {

        final String filename = "readme.txt";

        final String extension = Files.getExtension(filename);
        Assert.assertEquals("txt", extension);

    }

    @Test
    public void testGetExtensionFile() throws Exception {

        final File file = File.createTempFile("blah", ".dat");
        file.deleteOnExit();

        String extension = Files.getExtension(file);
        Assert.assertEquals("dat", extension);

    }

    @Test
    public void testVerifyExistsTrue() throws Exception {

        final File file = File.createTempFile("blah", ".dat");
        file.deleteOnExit();

        // this method will throw an exception if the parameter file does not exist
        // this test will fail if an exception is thrown when the file does exist
        Files.verifyExists(file);

    }

    @Test
    public void testVerifyExistsFalse() throws Exception {

        final File file = File.createTempFile("blah", ".dat");
        file.deleteOnExit();
        file.delete();

        try {
            Files.verifyExists(file);
            Assert.fail("Expected an exception");
        } catch (FileNotFoundException e) {
            Assert.assertEquals("file " + file.getAbsolutePath() + " does not exist", e.getMessage());
        }

    }

}
