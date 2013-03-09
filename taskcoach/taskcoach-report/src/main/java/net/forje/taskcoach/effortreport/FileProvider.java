package net.forje.taskcoach.effortreport;

import java.io.File;
import java.util.Date;

public interface FileProvider {

    /**
     * Returns a File object representing the file the report data should be written to.
     * @param date
     */
    public File getFile(final Date date);

}
