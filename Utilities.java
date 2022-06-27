import java.time.LocalTime;

/**
 * javUtils.java
 * 
 * It has all of the helper methods.
 */
public class javUtils {

	/**
	 * It will print the log on the console related to any message from ATC to plane
	 * or vice versa.
	 * 
	 * @param planeREG id of plane
	 * @param message message to print
	 * @param plane   true if plane sent message else ATC
	 */
	public static void log(int planeREG, String message, boolean plane) {

		System.out.println((plane ? "ThreadPlaneLine" : "ThreadTerminalLine ") + " : (" + getTime() + ") : Plane "
				+ String.format("%02d", planeREG) + " : " + message);

	}

	/**
	 * @return the formated current time
	 */
	private static String getTime() {

		LocalTime time = LocalTime.now();
		return String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond());

	}

	/**
	 * Print the message from main thread.
	 * 
	 * @param message message to print.
	 */
	public static void log(String message) {

		System.out.println("CoreThread  : (" + getTime() + ") : " + message);

	}

	/**
	 * It will execute all of the processes of the plane when it will be docked with
	 * the gate of airport.
	 * 
	 * @param id    plane id getting processes
	 * @param types type of processes it will get
	 */
	public static void executePostDockProcesses(int id, PlaneProcessType... types) {

		PdProcess[] processes = new PdProcess[types.length];
		for (int i = 0; i < types.length; i++) {
			processes[i] = new PdProcess(types[i], id);
			processes[i].start();
		}

		for (PdProcess process : processes) {
			try {
				process.join();
			} catch (InterruptedException e) {
				log(id, "I GOT ISSUE", true);
			}
		}

	}

	/**
	 * Sleeping the thread for that time.
	 * 
	 * @param time given time to sleep
	 */
	public static void wait(int time) {

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			javUtils.log("Unable to wait.");
		}

	}

}
