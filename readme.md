# deors-demos-testing-selenium

Demonstration of Java UI integration tests with Selenium on top of JUnit.

The project illustrates some best practices for writing integration tests with Selenium, as for example: decoupling test case logic from browser init, configuration and execution, or using lambda expression to control wait conditions during the test execution.

The initial configuration of the project, in pom.xml and test code, is set to work without needing any external dependency, by combining Cargo to provision a Tomcat runtime and HtmlUnit to run tests in that in-memory browser. To launch the complete cycle just execute:

	mvn clean verify

The test cases are prepared to receive certain external configuration items, to control its behaviour. The list below shows the set of environment variables (in uppercase, separated by '_') and JVM system properties (in lowercase, separated by '.'), that can be used to control test execution:

* `RUN_HTMLUNIT` / `test.run.htmlunit` -> `true`/`false`, to control execution of tests with HtmlUnit in-memory browser. Default value is: `true`.

* `RUN_IE` / `test.run.ie` -> `true`/`false`, to control execution of tests with Microsoft Internet Explorer (requires a Selenium Grid setup). Default value is: `false`.

* `RUN_FIREFOX` / `test.run.firefox` -> `true`/`false`, to control execution of tests with Mozilla Firefox (requires a Selenium Grid setup). Default value is: `false`.

* `RUN_CHROME` / `test.run.chrome` -> `true`/`false`, to control execution of tests with Google Chrome (requires a Selenium Grid setup). Default value is: `false`.

* `RUN_OPERA` / `test.run.opera` -> `true`/`false`, to control execution of tests with Opera (requires a Selenium Grid setup). Default value is: `false`.

* `SELENIUM_HUB_URL` / `test.selenium.hub.url` -> URL where the Selenium Grid is awaiting for browser automation commands. Default value is: `http://localhost:4444/wd/hub` (a grid in the same box running the tests).

* `TARGET_SERVER_URL` / `test.target.server.url` -> URL where the application is listening to HTTP requests. Default value is: `http://localhost:57080/deors-demos-testing-selenium` (the application as is launched by Cargo in a Tomcat runtime).
