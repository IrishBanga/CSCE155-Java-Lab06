package unl.soc;

import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import static org.junit.platform.engine.discovery.DiscoverySelectors.*;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a test wrapper used by grading scripts (using JUnit's standalone
 * console)
 * 
 * This is a single, generic batch testing utility. Invoke this class by
 * providing a (space delimited) list of fully qualified package/class JUnit
 * test classes. Examples:
 * 
 * Compile: javac -d . -cp .:/junit-platform-console-standalone-1.9.2.jar *.java
 * 
 * Run:
 * 
 * java -cp .:./junit-platform-console-standalone-1.9.2.jar unl.soc.TestWrapper unl.soc.ColorUtilsTests -reportPass
 * 
 * @author cbourke
 */
@SuppressWarnings("unused")
public class TestWrapper {

	private final SummaryGeneratingListener listener = new SummaryGeneratingListener();
	private final List<String> testClasses;

	public TestWrapper(List<String> testClasses) {
		this.testClasses = testClasses;
	}

	public void runAll() {
		LauncherDiscoveryRequestBuilder builder = LauncherDiscoveryRequestBuilder.request();
		for (String testClass : this.testClasses) {
			builder = builder.selectors(selectClass(testClass));
		}
		LauncherDiscoveryRequest request = builder.build();
		Launcher launcher = LauncherFactory.create();
		TestPlan testPlan = launcher.discover(request);
		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);
	}

	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("You must provide one or more fully qualified path/class references for testing.");
			System.exit(1);
		}

		boolean reportPass = false;
		List<String> tests = new ArrayList<>();
		for (String arg : args) {
			if (arg.equals("-reportPass")) {
				reportPass = true;
			} else {
				tests.add(arg);
			}
		}
		TestWrapper bt = new TestWrapper(tests);

		// suppress standard output while tests are run
		boolean suppressStdOut = false;
		PrintStream original = null;
		if (suppressStdOut) {
			original = new PrintStream(System.out);
			PrintStream nps;
			try {
				nps = new PrintStream(new FileOutputStream("/dev/null"));
				System.setOut(nps);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		bt.runAll();

		// restore standard output for report
		if (suppressStdOut) {
			System.setOut(original);
		}

		TestExecutionSummary summary = bt.listener.getSummary();

		long numTests = summary.getTestsFoundCount();
		long numFail = summary.getTestsFailedCount();
		long numPass = summary.getTestsSucceededCount();

		// prints total number of points, number of pass/fail
		// and total tests in csv format
		System.out.printf("Tests PASS: %d\n", numFail);
		System.out.printf("Tests FAIL: %d\n", numPass);
		System.out.printf("Total:      %d\n", numTests);

		if (reportPass) {
			System.exit((int) numPass);
		} else {
			System.exit((int) numFail);
		}

	}

}
