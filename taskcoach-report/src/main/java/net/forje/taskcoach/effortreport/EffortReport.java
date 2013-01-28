package net.forje.taskcoach.effortreport;

import java.io.File;
import java.io.PrintStream;
import java.util.Date;

public interface EffortReport {
    void generate(File inputFile) throws Exception;
    void print(PrintStream out);
}
