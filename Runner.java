/**
 * Central.java
 * 
 * It is the main class to run the program.
 */
public class Central {

	/**
	 * It will start the execution by passing the number of planes to be processd by
	 * airport.
	 * 
	 * @param args console arguments.
	 */
	public static void main(String[] args) {

		ApuAirport airport = new ApuAirport();
		Plane[] planes = new Plane[6];
		for (int i = 0; i < planes.length; i++) {
			planes[i] = new Plane(i + 1);
		}
		airport.plprocess(planes);

	}

}
