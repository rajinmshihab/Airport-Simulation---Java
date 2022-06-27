/**
 * PdProcess.java
 *
 * It will be used to execute the plane process while it is docked on the gate
 */
public class PdProcess extends Thread {

	// Required variables
	private PlaneProcessType type;
	private int planeREG;

	/**
	 * Constructor to set the object.
	 * 
	 * @param type    the type of process it needed
	 * @param planeREG the plane id getting process
	 */
	public PdProcess(PlaneProcessType type, int planeREG) {

		this.type = type;
		this.planeREG = planeREG;

	}

	/**
	 * It will print the message that execution of process is started, then it will
	 * wait for the time for execution and then print the message for completion and
	 * end it.
	 */
	@Override
	public void run() {

		javUtils.log(planeREG, getMessage(true), true);
		try {
			sleep(type.getProcessTime());
		} catch (InterruptedException e) {
			javUtils.log(planeREG, "Unable to wait for operation.", true);
		}
		javUtils.log(planeREG, getMessage(false), true);

	}

	/**
	 * It send the message corresponding to the type of process.
	 * 
	 * @param initial the message to be sent in initial state or at end state
	 * @return the message
	 */
	private String getMessage(boolean initial) {

		switch (type) {

		case PASS_DIS_EM:
			return initial ? "Disembarking all plassengers." : "Embarked the total of 50 passengers.";
		case RESCTOCK:
			return initial ? "Gathering and filling all supplied" : "All supplies are refilled.";
		case REFUEL:
			return initial ? "Waiting for refueling" : "Completely refueled for flight.";
		default:
			return "";

		}

	}

}
