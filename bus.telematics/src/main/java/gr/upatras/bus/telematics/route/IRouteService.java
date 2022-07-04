package gr.upatras.bus.telematics.route;

import java.util.ArrayList;
import java.util.List;

import gr.upatras.bus.telematics.stop.Stop;

/**
 * @author jlaza
 *
 */
public interface IRouteService {

	/**
	 * @return all {@link Route} instances
	 */
	List<Route> getAll();

	/**
	 * @param id
	 * @return the {@link Route} with the specific id
	 */
	Route getById(int id);

	/**
	 * @param r
	 * @return the created {@link Route} instance
	 */
	Route createRoute(Route r);

	/**
	 * @param id
	 * @param r
	 * @return the changed {@link Route} with specific id
	 */
	Route editRoute(int id, Route r);

	/**
	 * @param id
	 */
	Void deleteRoute(int id);

	/**
	 * @param stopId
	 * @return the {@link Route} instances that contain a specific {@link Stop}
	 *         instance
	 */
	ArrayList<Route> getRoutesByStop(int stopId);

}
