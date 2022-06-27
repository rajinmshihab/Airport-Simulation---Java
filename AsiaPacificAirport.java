// Hub of the simulation. Will initialize threads and push processes.

public class ApuAirport {

	// Declaration [Initial Variables]
	
	private Runway apuRunway;
	private Gates apuGates;
	private Telemetry stats;

	// Initialization [Constructor]
	
	public ApuAirport() {

		apuRunway = new Runway();
		apuGates = new Gates();
		this.stats = Telemetry.getTelemetry();

	}

	// The threads will start and go through the processes
	// Awaiting end of all threads
	// We'll be using for-each loop: 
	//
	// for (type variableName : arrayName) {
	// code block to be executed
	// }
	
	
	public void plprocess(Plane[] planes) {

		for (Plane each : planes) {

			each.setArrCountdown(stats.random(5000));
			each.setDestAir(this);
			each.start();

			javUtils.wait(stats.random(2000));

		}

		
		
		
		for (Plane each : planes) {

			try {
				each.join();
			} catch (InterruptedException e) {
				javUtils.log("Failed!");
			}

		}

		stats.printTelemetry();

	}

	public Runway getArpRunway() {

		return apuRunway;

	}

	public Gates getArpGates() {

		return apuGates;

	}

}
