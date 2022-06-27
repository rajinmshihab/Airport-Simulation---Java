import java.util.concurrent.Semaphore;

/**
 * Runway.java
 * 
 * It represents the single runway in the airport, which will be used for
 * multiple planes for landing and take offing.
 */
public class Runway extends Semaphore {

	// Constants..
	private static final int RUNWAY_QUANTITY = 1;
	private static final int PUSH_WAIT = 1500;
	private static final int RUNWAY_USE_TIME = 1000;

	/**
	 * Default Constructor.
	 */
	public Runway() {

		super(RUNWAY_QUANTITY);

	}

	/**
	 * It will create a synchronized queue of plane threads requesting them by the
	 * use of Semaphore.
	 * 
	 * All threads keep on getting the access to runway, each plane get it by their
	 * turn and they will acquire it and no any other plane can use it.
	 * 
	 * @param planeREG id of plane
	 * @param land    true if landing or false if take offing
	 */
	public void requestAccess(int planeREG, boolean land) {

		javUtils.log(planeREG, "Requesting permission to " + (land ? "land" : "take off") + ".", true);
		if (!tryAcquire()) {

			do {

				javUtils.log(planeREG, "ALREADY OCCUPIED, Wait for clearance.", false);
				try {
					Thread.sleep(PUSH_WAIT);
				} catch (InterruptedException e) {
					javUtils.log(planeREG, "UNABLE TO WAIT!", true);
				}
				javUtils.log(planeREG, "Again Requesting permission to " + (land ? "land" : "take off") + ".", true);

			} while (!tryAcquire());

		}
		javUtils.log(planeREG, (land ? "Landing" : "Take off") + " Permission Granted.", false);

	}

	/**
	 * It will be used by the plane to remove the runway resource so that the other
	 * plane threads waiting for it can access it.
	 * 
	 * @param planeREG id of plane
	 * @param land    true if landing else false if take offing.
	 */
	public void useRunway(int planeREG, boolean land) {

		try {
			Thread.sleep(RUNWAY_USE_TIME);
		} catch (InterruptedException e) {
			javUtils.log(planeREG, "UNABLE TO WAIT!", true);
		}
		javUtils.log(planeREG, (land ? "Landed" : "Flown") + " successfully!", true);
		javUtils.log("Runway is available!");
		release();

	}

}
