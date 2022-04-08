import org.bonn.ooka.buchungssystem.ss2022.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class LoggerTest {
	// https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	@DisplayName ("search for String Test | expect log to output String Test and Time")
	void searchForStringTestExpectLogToOutputStringTestAndTime() {
		Logger logger = new Logger();
		logger.log("Test");

		String testOut = outContent.toString();
		String[] parts = testOut.split(": ");

		assertTrue(parts[0].length() > 0);
		assertTrue(parts[1].contains("Test"));

		String testErr = errContent.toString();
		assertEquals(0, testErr.length());
	}
}
