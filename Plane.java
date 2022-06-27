/**
 * Plane.java
 * 
 * A single plane getting plprocessd by the airport which will land first, do 3
 * processes and take off again.
 */
public class Plane extends Thread {

	// Required variables.
	private int planeREG;
	private int arrTime;
	private ApuAirport destinationAirport;

	/**
	 * Initializing the plane object.
	 * 
	 * @param planeREG the id of plane
	 */
	public Plane(int planeREG) {

		this.planeREG = planeREG;

	}

	/**
	 * It will wait until it get turn to be plprocessd.
	 * 
	 * Step 1: It will keep on requesting for runway to land until it gets it.
	 * Step 2: It will keep on requesting for gate to dock until it gets it.
	 * Step 3: It will execute three processes in parallel including passengers, refueling and refilling.
	 * Step 4: It will again request for runway to take off off until it gets it.
	 * Step 5: It will update the stats.
	 */
	@Override
	public void run() {

		javUtils.log(planeREG, "We only have fuel for " + 5 + " seconds.", true);
		javUtils.log(planeREG, "Reaching to airport within " + ((int) (arrTime / 1000)) + " seconds.", true);
		javUtils.wait(arrTime);
		javUtils.log(planeREG, "We reached near airport, ready to land!", true);

		Runway apuRunway = destinationAirport.getArpRunway();
		Gates gates = destinationAirport.getArpGates();

		double runTimeout = System.currentTimeMillis();
		apuRunway.requestAccess(planeREG, true);
		apuRunway.useRunway(planeREG, true);
		runTimeout = System.currentTimeMillis() - runTimeout;

		double gateWaiting = System.currentTimeMillis();
		gates.dock(planeREG);
		gateWaiting = System.currentTimeMillis() - gateWaiting;

		javUtils.executePostDockProcesses(planeREG, PlaneProcessType.PASS_DIS_EM,
				PlaneProcessType.RESCTOCK, PlaneProcessType.REFUEL);

		gates.undock(planeREG);

		apuRunway.requestAccess(planeREG, false);
		apuRunway.useRunway(planeREG, false);

		Telemetry.getTelemetry().add(runTimeout, gateWaiting);

	}

	/**
	 * Setting new value of arrival time.
	 * 
	 * @param arrTime time to arrive at airport
	 */
	public void setArrCountdown(int arrTime) {

		this.arrTime = arrTime;

	}

	/**
	 * Setting new value of destination Airport.
	 * 
	 * @param destinationAirport on which plane is landing.
	 */
	public void setDestAir(ApuAirport destinationAirport) {

		this.destinationAirport = destinationAirport;

	}

}
	
