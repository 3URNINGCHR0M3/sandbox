*Untitled Integration Test Library*

This library will build on the patterns found in the JUnit library, but add functionally required for complex integration testing.  Specifically:
* *Assertions and Requirements* -  Integration tests may take signficant amounts of time to setup and execute.  Therefore, it is desireable to proceed as far as possible through the test
  * The test will continue if a soft assertion is false
  * The test will halt if a requirement is false
  * If a fatal condition is encountered, the test must be able to stop
  * The result of all assertions must be captured and made available for reporting
* *Single test per class* - Because integrations are expected to be more complex, having multiple tests in a single class would make for unmanagable source files.  Shared or repeated functionality should be moved upwards in the class heirartcy or into utility classes.
