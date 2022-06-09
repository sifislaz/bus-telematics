package gr.upatras.bus.telematics.stop;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.upatras.bus.telematics.json.JSONHandler;

/**
 * @author jlaza
 *
 */
@Service
public class StopService implements IStopService {
	//private IRouteService routeService;
	List<LinkedHashMap> stopsJSON;
	ArrayList<Stop> stops = new ArrayList<Stop>();

	public StopService() {
		super();
		stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops.json");
		for (LinkedHashMap m : stopsJSON) {
			int id = Integer.parseInt(m.get("id").toString());
			String name = m.get("name").toString();
			double lng = Double.parseDouble(m.get("long").toString());
			double lat = Double.parseDouble(m.get("lat").toString());
			if (id < 0)
				throw new IllegalArgumentException("id can't be negative");
		}
	}

	/**
	 * Return the list of all stops
	 */
	@Override
	public ArrayList<Stop> getAll() {
		return stops;
	}

	/**
	 * @param id
	 * Return the stop, if exists, with the given id
	 */
	@Override
	public Stop getById(int id) {
		if (id < 0) throw new IllegalArgumentException("Stop id can't be negative");
		for (Stop s : stops) {
			if (s.getId() == id) {
				return s;
			}
		}
		return null;
	}

	/**
	 * @param id
	 * Delete stop, if exists, with the given id
	 */
	@Override
	public Void deleteStop(int id) {
		if(id < 0) throw new IllegalArgumentException("Id can't be negative");
		for(Stop s : stops) {
			if(s.getId() == id) {
				stops.remove(s);
				break;
			}
		}
		return null;
	}
	
	/**
	 * @param s
	 * @return the edited {@link Stop} instance
	 * Edit the stop
	 */
	@Override
	public Stop editStop(Stop s) {
		Stop toEdit = getById(s.getId());
		if(toEdit != null) {
			toEdit.setLatitude(s.getLatitude());
			toEdit.setLongitude(s.getLongitude());
			toEdit.setName(s.getName());
		}
		return toEdit;
	}
	
	/**
	 *@param s
	 *@return the created {@link Stop} instance
	 */
	@Override
	public Stop createStop(Stop s) {
		stops.add(s);
		return s;
	}
	
	/**
	 * @param id
	 * @param routeId
	 * @return true if the stop belongs to the route, else false
	 */
	@Override
	public Boolean hasRoute(int id, int routeId) {
		return null;  // to be fixed
	}
}
