package org.bonn.ooka.buchungssystem.ss2022.logging;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class Logger {
	public void log(String text) {
		String time = new SimpleDateFormat("dd.MM.yy HH:mm").format(Calendar.getInstance().getTime());
		System.out.println(time + ": " + text);
	}
}
