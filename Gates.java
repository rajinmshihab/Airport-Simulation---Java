import java.util.concurrent.Semaphore;

/**
 * Gates.java
 * 
 * It represents the 2 gates in the airport which will be used in parallel by
 * different planes to get processes.
 */
public class Gates extends Semaphore {

	// Constants..
	private static final int GATES_QUANTITY= 2;
	private static final int PUSH_WAIT = 1500;
	private static final int GATE_OCCUPY_DUR = 1000;

	/**
	 * Default Constructor.
	 */
	public Gates() {

		super(NUMBER_OF_GATES);

	}

	/**
	 * It will create a synchronized queue of plane threads requesting them by the
	 * use of Semaphore.
	 * 
	 * All threads keep on getting the access to any of gate, each plane get it by
	 * their turn and they will acquire it and no any other plane can use it.
	 * 
	 * The 2 gates can be used in parallel.
	 * 
	 * @param planeREG id of plane
	 */
	public void dock(int planeREG) {

		javUtils.log(planeREG, "Requesting permission to dock the gate.", true);
		if (!tryAcquire()) {

			do {

				javUtils.log(planeREG, "2 GATES ARE OCCUPIED, Wait for clearance.", false);
				try {
					Thread.sleep(PUSH_WAIT);
				} catch (InterruptedException e) {
					javUtils.log(planeREG, "UNABLE TO WAIT!", true);
				}
				javUtils.log(planeREG, "Again Requesting permission to dock the gate.", true);

			} while (!tryAcquire());

		}
		javUtils.log(planeREG, "Docking Permission Granted.", false);
		try {
			Thread.sleep(GATE_OCCUPY_DUR);
		} catch (InterruptedException e) {
			javUtils.log(planeREG, "UNABLE TO WAIT!", true);
		}
		javUtils.log(planeREG, "Docked successfully!", true);
		javUtils.log("Gate is available to dock!");

	}

	/**
	 * It will undock the gate and release it be used by others.
	 * 
	 * @param planeREG the id of plane
	 */
	public void undock(int planeREG) {

		javUtils.log(planeREG, "Undocked the Gate!", true);
		release();

	}

}
