package timerpackage;

	/*
	 * Simple program to demonstrate use of timer package.
	 * Change the value of time in Time.txt file, in the root folder.
	 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
	Timer timer;

	public Reminder(int seconds) {
		timer = new Timer();
		timer.schedule(new RemindTask(), seconds * 1000);
	}

	class RemindTask extends TimerTask {
		public void run() {
			System.out.println("Time's up!");
			timer.cancel();
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"Time.txt"));
		int time = 0;
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				if (line.startsWith("Time")) {
					time = Integer
							.parseInt(line.split("=")[1].replace(" ", ""));
				}
				sb.append(line);
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		new Reminder(time);
		System.out.println("Task scheduled.");
	}
}