package com.github.uitf;

import java.util.Iterator;

/**
 *
 */
public interface TestResult {

    enum Outcome {
        Pass, Fail, Error
    }

    /**
     * Returns the full name and package of the class being executed as a test
     *
     * @return the name of the test class being executed
     */
    public String getTestName();

    /**
     * Returns an iterator over the set of assertions collected by this test.
     *
     * @return an Interator of AssertionEvent objects
     */
    public Iterator assertions();

    /**
     * The outcome of the test.
     *
     * @return an instance of TestResult.Outcome indicating the results of executing the test
     */
    public TestResult.Outcome getOutcome();

    /**
     * Returns the number of true assertions produced by the exection of the test
     *
     * @return the number of true assertions.
     */
    public int countTrueAssertions();

    /**
     * Returns the number of false assertions produced by the exection of the test
     *
     * @return the number of false assertions.
     */
    public int countFalseAssertions();

    /**
     * Returns a boolean if an error occured while exectuing the test
     *
     * @return true if an error occured, false otherwise.
     */
    public boolean hasError();


    /**
     * Returns a boolean if a failure occured while exectuing the test
     *
     * @return true if an failure occured, false otherwise.
     */
    public boolean hasFailure();

    /**
     * Returns the exception that caused an unexpected error in the execution of the test.
     *
     * @return the unexpected Exception which was thrown by the test.
     */
    public Exception getException();

    String getFailureMessage();


}
