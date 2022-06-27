import java.util.Arrays;
import java.util.Random;

/**
 * Telemetry.java
 * 
 * It is responsible for collecting all of the statistical data of the
 * simulation.
 */
public class Telemetry {

	// Used Variables
	private double[] runTime;
	private double[] gateTime;
	private int dummy;
	private int passProcessed;
	private int planeProcessed;
	private Random random;

	// Constants
	private static final int PLANE_QUANTITY = 6;
	private static final int TOT_PASSENGER_pPLANE = 50;

	// Singleton
	private static Telemetry stats;

	/**
	 * It represents the single instance of the class.
	 * 
	 * @return instance of stats
	 */
	public static Telemetry getTelemetry() {

		if (stats == null) {
			stats = new Telemetry();
		}
		return stats;

	}

	/**
	 * Default Constructor
	 */
	private Telemetry() {

		clear();
		random = new Random();

	}

	/**
	 * To generate random number within range.
	 * 
	 * @param range given range
	 * @return random number
	 */
	public int random(int range) {

		return random.nextInt(range);

	}

	/**
	 * Clear the stats for next use
	 */
	public void clear() {

		runTime = new double[PLANE_QUANTITY];
		gateTime = new double[PLANE_QUANTITY];
		dummy = 0;
		this.passProcessed = 0;

	}

	/**
	 * Add the runway and gate waiting time of plane.
	 * 
	 * @param runway the time plane waited to get access to runway
	 * @param gate   the time plane waited to get access to gate to dock
	 */
	public void add(double runway, double gate) {

		if (dummy < PLANE_QUANTITY) {
			this.passProcessed += TOT_PASSENGER_pPLANE;
			this.planeProcessed++;
			this.runTime[dummy] = runway;
			this.gateTime[dummy] = gate;
			dummy++;
		}

	}

	/**
	 * Printing the stats on the screen.
	 */
	public void printTelemetry() {

		System.out.println("\nAirport Simulation Done!\nTelemetry Details\n");
		System.out.println("Total Planes: " + planeProcessed);
		System.out.println("Total Passengers: " + passProcessed);

		System.out.println("\nWaiting Time for getting access to Runway!" + "\n\tMin: "
				+ String.format("%.1f", Arrays.stream(runTime).min().getAsDouble()) + " ms." + "\n\tAvg: "
				+ String.format("%.1f", Arrays.stream(runTime).average().getAsDouble()) + " ms." + "\n\tMax: "
				+ String.format("%.1f", Arrays.stream(runTime).max().getAsDouble()) + " ms.");

		System.out.println("\nWaiting Time for getting access to Dock!" + "\n\tMin: "
				+ String.format("%.1f", Arrays.stream(gateTime).min().getAsDouble()) + " ms." + "\n\tAvg: "
				+ String.format("%.1f", Arrays.stream(gateTime).average().getAsDouble()) + " ms." + "\n\tMax: "
				+ String.format("%.1f", Arrays.stream(gateTime).max().getAsDouble()) + " ms.");

	}

}
