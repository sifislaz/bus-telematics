package gr.upatras.bus.telematics.stop;

import java.util.List;

/**
 * @author jlaza
 *
 */
public interface IStopService {
	
	/**
	 * @return all {@link Stop} instances
	 */
	List<Stop>getAll();
	
	/**
	 * @param id
	 * @return the {@link Stop} with the specific id
	 */
	Stop getById(int id);
	
	/**
	 * @param s
	 * @return the created instance of {@link Stop}
	 */
	Stop createStop(Stop s);
	
	/**
	 * @param s
	 * @return the changed {@link Stop}
	 */
	Stop editStop(Stop s);
	
	/**
	 * @param id
	 */
	Void deleteStop(int id);
	
//	List<Route> getRoutes(int id);
	
	/**
	 * @param routeId
	 * @param id
	 * @return true if the stop belongs to the route, else false
	 */

	Boolean hasRoute(int id, int routeId);
}
