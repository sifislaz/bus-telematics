package gr.upatras.bus.telematics.route;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.upatras.bus.telematics.json.JSONHandler;
import gr.upatras.bus.telematics.stop.*;

/**
 * @author jlaza
 *
 */
@Service
public class RouteService implements IRouteService {

	@Autowired
	private IStopService stopService;
	List<LinkedHashMap> routesJSON;
	ArrayList<Route> routes = new ArrayList<Route>();

	public RouteService() {
		super();
		routesJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("routes.json");
		for (LinkedHashMap m : routesJSON) {
			int id = Integer.parseInt(m.get("id").toString());
			if (id < 0) {
				throw new IllegalArgumentException("id can't be negative");
			} else {
				String start = m.get("start").toString();
				String destination = m.get("destination").toString();
				LinkedList<Integer> stops = new LinkedList<Integer>();
				ArrayList<Integer> stList = (ArrayList<Integer>) m.get("stops");
				for (Integer s : stList) {
					stops.add(s);
				}
				routes.add(new Route(id, start, destination, stops));
			}

		}
	}

	/**
	 * @return all {@link Route} instances
	 */
	@Override
	public ArrayList<Route> getAll() {
		return routes;
	}

	/**
	 * @param id
	 * @return the {@link Route} with specific id
	 */
	@Override
	public Route getById(int id) {
		if (id < 0)
			throw new IllegalArgumentException("Route id can't be negative");
		for (Route r : routes) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}

	/**
	 * @param r
	 * @return the created {@link Route}
	 */
	@Override
	public Route createRoute(Route r) {
		routes.add(r);
		return r;
	}

	/**
	 * @param id
	 * @param r
	 *
	 * @return the edited {@link Route} with specific id
	 */
	@Override
	public Route editRoute(int id, Route r) {
		Route toEdit = getById(id);
		if (toEdit != null) {
			toEdit.setStart(r.getStart());
			toEdit.setDestination(r.getDestination());
			toEdit.setStops(r.getStops());
		}
		return toEdit;
	}

	/**
	 * @param id Deletes the {@link Route} instance with the specific id
	 */
	@Override
	public Void deleteRoute(int id) {
		if (id < 0)
			throw new IllegalArgumentException("Id can't be negative");
		for (Route r : routes) {
			if (r.getId() == id) {
				routes.remove(id);
				break;
			}
		}
		return null;
	}

	/**
	 * @param stopId
	 * @return the {@link Route} instances that contain a specific {@link Stop} instance 
	 */
	public ArrayList<Route> getRoutesByStop(int stopId) {
		if (stopId < 0)
			throw new IllegalArgumentException("Id can't be negative");
		ArrayList<Route> stopRoutes = new ArrayList<Route>();
		for (Route r : routes) {
			LinkedList<Integer> stops = r.getStops();
			if(stops.indexOf(stopId)!=-1) {
				stopRoutes.add(r);
			}
		}
		return stopRoutes;
	}

}
