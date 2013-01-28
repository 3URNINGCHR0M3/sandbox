package net.forje.taskcoach.types;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Files {

    public static void copy(final File source, final File destination) throws IOException {

        final FileInputStream inputStream = new FileInputStream(source);
        final FileOutputStream outputStream = new FileOutputStream(destination);

        try {
            int bite = -1;

            while ((bite = inputStream.read()) > -1) {
                outputStream.write(bite);
            }
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }


    }

}
