package gr.upatras.bus.telematics.stop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashMap;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import gr.upatras.bus.telematics.bus.apiClass;
import gr.upatras.bus.telematics.json.JSONHandler;
import gr.upatras.bus.telematics.route.*;

/**
 * @author jlaza
 * @author sotirissid
 *
 */
@Service
@Scope("prototype")
public class StopService implements IStopService {

	@Autowired
	private IRouteService routeService; // Connection with route service
	List<LinkedHashMap> stopsJSON; // Storage of stop data from JSON
	ArrayList<Stop> stops = new ArrayList<Stop>(); // Stop instances created from JSON data
	private static final Logger log = LoggerFactory.getLogger(StopController.class);

	public StopService() {
		super();
		stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops.json"); // read JSON file
		// Create new Stop objects from parsed data
		for (LinkedHashMap m : stopsJSON) {
			int id = Integer.parseInt(m.get("id").toString());

			if (id < 0)
				throw new IllegalArgumentException("id can't be negative");
			else {
				String name = m.get("name").toString();
				double lng = Double.parseDouble(m.get("long").toString());
				double lat = Double.parseDouble(m.get("lat").toString());
				stops.add(new Stop(id, name, lng, lat));
			}
		}
	}

	/**
	 * Return the list of all stops
	 */
	@Override
	public ArrayList<Stop> getAll() {
		System.out.println(stops);
		return stops;
	}

	/**
	 * @param id Return the stop, if exists, with the given id
	 */
	@Override
	public Stop getById(int id) {
		if (id < 0)
			throw new IllegalArgumentException("Stop id can't be negative");
		for (Stop s : stops) {
			if (s.getId() == id) {
				return s;
			}
		}
		return null;
	}

	/**
	 * @param id Delete stop, if exists, with the given id
	 */
	@Override
	public Void deleteStop(int id) {
		if (id < 0)
			throw new IllegalArgumentException("Id can't be negative");
		for (Stop s : stops) {
			if (s.getId() == id) {
				stops.remove(s); // delete from list
				break;
			}
		}
		return null;
	}

	/**
	 * @param s
	 * @return the edited {@link Stop} instance Edit the stop
	 */
	@Override
	public Stop editStop(int id, Stop s) {
		Stop toEdit = getById(id);
		// change stop's attributes according to the given object's attributes
		if (toEdit != null) {
			toEdit.setLatitude(s.getLatitude());
			toEdit.setLongitude(s.getLongitude());
			toEdit.setName(s.getName());
		}
		return toEdit; // return the new instance
	}

	/**
	 * @param s
	 * @return the created {@link Stop} instance
	 */
	@Override
	public Stop createStop(Stop s) {
		stops.add(s); // save the new Stop
		return s;
	}

	/**
	 * @param routeId
	 * @return {@link ArrayList} containing {@link Stop} of the specific
	 *         {@link Route}
	 */
	@Override
	public ArrayList<Stop> getStopsByRouteId(int routeId) {
		if (routeId < 0)
			throw new IllegalArgumentException("Id should've been positive");
		Route r = routeService.getById(routeId); // get route
		LinkedList<Integer> stopIds = r.getStops(); // get stops given by route
		ArrayList<Stop> stops = new ArrayList<Stop>();
		// obtain data for the given route's stops
		for (Integer sId : stopIds) {
			stops.add(getById(sId));
		}
		return stops;
	}

	/**
	 * @param Stopname
	 * @return the {@link ArrayList} of the time needed for the {@link Bus} that
	 *         serves a route to arrive at the {@link Stop}
	 */
	public ArrayList<apiClass> getTime(int stopId) throws IOException, InterruptedException, ParseException {
		String cord = "";

		// Get the coordinations
		for (Stop s : stops) {
			if (s.getId() == stopId) {
				cord = Double.toString(s.getLongitude()) + "," + Double.toString(s.getLatitude());

			}

		}
		// find the routes that contain the id of the stop
		ArrayList<Integer> routelst = new ArrayList();
		List<LinkedHashMap> routeJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("routes.json");
		for (int i = 0; i < routeJSON.size(); i++) {

			ArrayList<Integer> stList = (ArrayList<Integer>) routeJSON.get(i).get("stops");
			if (stList.contains(stopId)) {
				int id = (Integer) routeJSON.get(i).get("id");
				routelst.add(id);

			}
		}

		// finds buses that have that route, gets their coordinates and calculates time
		ArrayList<Integer> buslst = new ArrayList();
		ArrayList<String> buslstCord = new ArrayList();
		ArrayList<apiClass> time = new ArrayList();
		ArrayList<Integer> routeIdforCall = new ArrayList();
		List<LinkedHashMap> busJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("bus.json");
		try {
			for (int i = 0; i < busJSON.size(); i++) {
				int id = (Integer) busJSON.get(i).get("routeId");
				if (routelst.contains(id)) {
					// find the Stop of the current buss to check if it passed the stop
					double cur_long = (double) busJSON.get(i).get("long");
					double cur_lat = (double) busJSON.get(i).get("lat");
					for (Stop s : stops) {
						if (s.getLongitude() == cur_long && s.getLatitude() == cur_lat) {
							int busStop_id = s.getId(); // gets the stop that the bus is right now
							System.out.println("Bus stop id " + busStop_id);
							ArrayList<Stop> stoparray = new ArrayList<Stop>();
							ArrayList<Integer> stoplst = new ArrayList<Integer>();
							stoparray = getStopsByRouteId(id);
							for (Stop ob : stoparray) {
								stoplst.add(ob.getId());

							}
							int indexOfBus = stoplst.indexOf(busStop_id);
							int indexOfLocation = stoplst.indexOf(stopId);
							System.out.println("index bus " + indexOfBus);
							System.out.println("index location " + indexOfLocation);
							// if the stop location is further away than bus location
							if (indexOfLocation > indexOfBus) {
								// add the buses to the list to call the api
								routeIdforCall.add(id);
								buslst.add((Integer) busJSON.get(i).get("id"));
								buslstCord.add(busJSON.get(i).get("long").toString() + ","
										+ busJSON.get(i).get("lat").toString()); // gets bus coords

							}

						}
					}

				}
			}
			System.out.println(buslstCord.get(0));
			System.out.println(cord);
			for (int i = 0; i < buslst.size(); i++) {
				apiClass temp = new apiClass(buslstCord.get(i), cord, routeIdforCall.get(i));
				time.add(temp);
			}
		} catch (Exception e) {
			System.out.println("The is no bus currently that services this stop");
			log.info(String.format("The is no bus currently that services this stop"));
			return null;

		}
		return time;
	}
}
