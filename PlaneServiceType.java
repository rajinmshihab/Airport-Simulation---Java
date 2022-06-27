/**
 * PlaneProcessType.java
 * 
 * It represents the type of plane processes any plane can take after docking to
 * the gate.
 */
public enum PlaneProcessType {

	/**
	 * To disembark or embark the passengers through gate
	 */
	PASS_DIS_EM(1500),
	/**
	 * To refill the supplies in plane
	 */
	RESCTOCK(800),
	/**
	 * To refuel the plane.
	 */
	REFUEL(1300);

	private int processTime;

	/**
	 * Constructor to set the time of process.
	 * 
	 * @param processTime process execution time
	 */
	PlaneProcessType(int processTime) {

		this.processTime = processTime;

	}

	/**
	 * @return time for process execution.
	 */
	public int getProcessTime() {

		return processTime;

	}

}
