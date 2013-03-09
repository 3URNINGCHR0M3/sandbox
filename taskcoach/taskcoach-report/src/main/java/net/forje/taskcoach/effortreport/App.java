package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.types.Files;
import net.forje.taskcoach.types.Strings;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.*;
import java.util.Date;

/**
 * Hello world!
 */
public class App {

    private static final String REPORT_DATE = "reportDate";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String REPORT_CLASS = "report";
    private static final String VISITOR_CLASS = "visitor";
    private static final String MATCH = "match";
    private static final String INPUT_FILE = "inputFile";
    private static final String HELP = "help";

    private static final DateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy");

    public static void main(String[] args) {

        final Options options = buildOptions();

        final CommandLineParser parser = new PosixParser();

        final Arguments arguments = new Arguments();


        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption(HELP)) {
                displayHelp(options);
                return;
            }

            if (cmd.hasOption(INPUT_FILE)) {
                arguments._fileNameValue = cmd.getOptionValue(INPUT_FILE);
            }

            if (cmd.hasOption(REPORT_DATE)) {
                arguments._reportDate = getDate(cmd.getOptionValue(REPORT_DATE));
            }

            if (cmd.hasOption(REPORT_CLASS)) {
                arguments._className = cmd.getOptionValue(REPORT_CLASS);
            }

            if (cmd.hasOption(MATCH)) {
                arguments._match = cmd.getOptionValue(MATCH);
            }

            if (cmd.hasOption(VISITOR_CLASS)) {
                arguments._visitorClassName = cmd.getOptionValue(VISITOR_CLASS);
            }

            if (cmd.hasOption(START_DATE)) {
                arguments._startDateValue = getDate(cmd.getOptionValue(START_DATE));
            }

            if (cmd.hasOption(END_DATE)) {
                arguments._endDateValue = getDate(cmd.getOptionValue(END_DATE));
            }


            if (arguments._fileNameValue == null) {
                displayHelp(options);
                System.exit(1);
            }

            final TaskVisitor visitor = buildVisitorInstance(arguments);
            final EffortReport effortReport = buildReportInstance(arguments._className, visitor);
            process(arguments._fileNameValue, arguments._reportDate, effortReport);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            displayHelp(options);
        }

    }

    private static class Arguments {
        String _fileNameValue = null;
        Date _reportDate = null;
        Date _startDateValue = null;
        Date _endDateValue = null;
        String _match = null;
        String _className = null;
        String _visitorClassName = null;
    }

    private static EffortReport buildReportInstance(final String className,
                                                    final TaskVisitor visitor)
            throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        final Class<?> reportClass = getReportClass(className);
        final Constructor<?> constructor = reportClass.getConstructor(TaskVisitor.class);

        return (EffortReport) constructor.newInstance(visitor);
    }


    private static void process(final String fileNameValue,
                                final Date date,
                                final EffortReport effortReport
    )
            throws Exception {

        final File file = new File(fileNameValue);

        if (!file.exists()) {
            throw new Exception("File [" + file.getAbsolutePath() + "] does not exist");
        }

        if (!file.canRead()) {
            throw new Exception("File [" + file.getAbsolutePath() + "] can not be read");
        }

        backupFile(file);

        effortReport.generate(file);

        PrintStream printStream = null;

        boolean needToClosePrintStream = false;

        try {
            if (FileProvider.class.isAssignableFrom(effortReport.getClass())) {
                needToClosePrintStream = true;
                FileProvider fileProvider = (FileProvider) effortReport;
                final File outputFile = fileProvider.getFile(date);
                System.out.println("Writing to " + outputFile.getAbsoluteFile());
                if (outputFile.exists()) {
                    System.out.println("File already exists.  Deleting");
                    outputFile.delete();
                }
                outputFile.createNewFile();
                printStream = new PrintStream(outputFile);
            } else {
                printStream = System.out;
            }

            effortReport.print(printStream);

        } finally {
            if (needToClosePrintStream) {
                if (printStream != null) {
                    printStream.flush();
                    printStream.close();
                }
            }
        }

    }

    private static TaskVisitor buildVisitorInstance(final Arguments arguments)
            throws Exception {

        final Class<?> visitorClass = getVisitorClass(arguments._visitorClassName);

        TaskVisitor visitor = null;

        if (arguments._reportDate != null) {
            final Constructor<?> constructor = visitorClass.getConstructor(Date.class);
            visitor = (TaskVisitor) constructor.newInstance(arguments._reportDate);
        } else if (!Strings.isEmpty(arguments._match)) {
            final Constructor<?> constructor = visitorClass.getConstructor(String.class);
            visitor = (TaskVisitor) constructor.newInstance(arguments._match);
        } else if (arguments._endDateValue != null && arguments._startDateValue != null ) {
            final Constructor<?> constructor = visitorClass.getConstructor(Date.class,Date.class);
            visitor = (TaskVisitor) constructor.newInstance(arguments._startDateValue , arguments._endDateValue);
        }

        return visitor;

    }

    private static Class<?> getVisitorClass(final String className) throws ClassNotFoundException {
        return buildClassByName(className, DateMatchingTaskVisitor.class);
    }

    private static Class<?> getReportClass(final String className) throws ClassNotFoundException {
        return buildClassByName(className, ConsoleEffortReport.class);
    }

    private static Class<?> buildClassByName(final String className,
                                             final Class<?> defaultClass)
            throws ClassNotFoundException {

        String qualifiedClassName;
        if (Strings.isEmpty(className)) {
            qualifiedClassName = defaultClass.getName();
        } else {
            qualifiedClassName = "net.forje.taskcoach.effortreport." + className;
        }

        return Class.forName(qualifiedClassName);

    }

    private static Date getDate(final String dateValue) throws Exception {
        Date date = null;

        if (dateValue == null || dateValue.trim().length() == 0) {
            date = new Date();
        } else {
            try {
                date = FORMATTER.parse(dateValue);
            } catch (java.text.ParseException e) {
                throw new Exception("date [" + dateValue + "] was not in the correct format:  mm/dd/yyyy");
            }
        }
        return date;
    }

    private static void backupFile(final File file) throws Exception {

        final DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        final String datestring = format.format(new Date());
        String backupFileName = "taskcoach-" + datestring + ".xml";

        final File taskDir = file.getParentFile();

        File backupDir = new File(taskDir, "backup");
        backupDir.mkdirs();

        final File backup = new File(backupDir, backupFileName);
        System.out.println(backup.getAbsolutePath());
        backup.createNewFile();

        Files.copy(file, backup);


    }

    private static Options buildOptions() {

        final Options options = new Options();

        Option file = OptionBuilder.isRequired(false).
                withArgName("file")
                .hasArg()
                .withDescription("use given file for input (required)")
                .create(INPUT_FILE);

        Option date = OptionBuilder.isRequired(false)
                .withArgName("date")
                .hasArg()
                .withDescription("date to report on format : mm/dd/yyyy")
                .create(REPORT_DATE);

        Option startDate = OptionBuilder.isRequired(false)
                .withArgName("start")
                .hasArg()
                .withDescription("date to start the report on; format : mm/dd/yyyy")
                .create(START_DATE);

        Option endDate = OptionBuilder.isRequired(false)
                .withArgName("end")
                .hasArg()
                .withDescription("date to end the report on; format : mm/dd/yyyy")
                .create(END_DATE);

        Option match = OptionBuilder.isRequired(false)
                .withArgName("match")
                .hasArg()
                .withDescription("the description to match")
                .create(MATCH);

        Option report = OptionBuilder.isRequired(false)
                .withArgName("report")
                .hasArg()
                .withDescription("Unqualified class name for report")
                .create(REPORT_CLASS);

        Option visitor = OptionBuilder.isRequired(false)
                .withArgName("visitor")
                .hasArg()
                .withDescription("Unqualified class name for visitor")
                .create(VISITOR_CLASS);

        Option help = OptionBuilder.withDescription("print this message")
                .hasArg(false)
                .isRequired(false)
                .create(HELP);

        options.addOption(file);
        options.addOption(date);
        options.addOption(startDate);
        options.addOption(endDate);
        options.addOption(report);
        options.addOption(match);
        options.addOption(visitor);
        options.addOption(help);

        return options;

    }

    private static void displayHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("App", options);
    }

}
