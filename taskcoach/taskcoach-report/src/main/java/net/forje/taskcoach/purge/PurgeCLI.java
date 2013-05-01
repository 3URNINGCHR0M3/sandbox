package net.forje.taskcoach.purge;

import net.forje.taskcoach.effortreport.App;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.Date;

public class PurgeCLI {

    public static final String CUTOFF_DATE = "cutoffDate";

    public static void main(String[] args) {

        final Options options = buildOptions();
        final CommandLineParser parser = new PosixParser();

        CommandLine commandLine = null;

        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            App.displayHelp(options, PurgeCLI.class.getName());
            System.exit(1);
        }

        final String cutoffDateAsString = commandLine.getOptionValue(CUTOFF_DATE);
        final String inputFileName = commandLine.getOptionValue(App.INPUT_FILE);

        File file = new File(inputFileName);
        Date date = null;

        if (!file.exists()) {
            System.out.println("Could not locate file " + file.getAbsolutePath());
            App.displayHelp(options, PurgeCLI.class.getName());
            System.exit(2);
        }

        try {
            date = App.FORMATTER.parse(cutoffDateAsString);
        } catch (java.text.ParseException e) {
            System.out.println("Could not parse date " + cutoffDateAsString);
            App.displayHelp(options, PurgeCLI.class.getName());
            System.exit(3);
        }


        try {
            App.backupFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(4);
        }

        Parameters parameters = new Parameters(file, date);

        Purger purger = new Purger();
        try {
            purger.processRequest(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static Options buildOptions() {

        final Options options = new Options();

        final Option file = OptionBuilder.isRequired(true).
                withArgName("file")
                .hasArg()
                .withDescription("use given file for input (required)")
                .create(App.INPUT_FILE);

        final Option date = OptionBuilder.isRequired(true)
                .withArgName("date")
                .hasArg()
                .withDescription("cut off date to report on format : mm/dd/yyyy (required")
                .create(CUTOFF_DATE);

        options.addOption(file);
        options.addOption(date);

        return options;

    }

    public static class Parameters {

        private final File _file;
        private final Date _cutoffDate;

        public Parameters(File file, Date cutoffDate) {
            _file = file;
            _cutoffDate = cutoffDate;
        }

        public File getFile() {
            return _file;
        }

        public Date getCutoffDate() {
            return _cutoffDate;
        }

    }


}
