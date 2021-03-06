package gr.upatras.bus.telematics.stop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import gr.upatras.bus.telematics.bus.apiClass;
import gr.upatras.bus.telematics.route.*;

/**
 * @author jlaza
 * @author sotirissid
 *
 */
public interface IStopService {

	/**
	 * @return all {@link Stop} instances
	 */
	List<Stop> getAll();

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
	 * @param id
	 * @param s
	 * @return the changed {@link Stop} with specific id
	 */
	Stop editStop(int id, Stop s);

	/**
	 * @param id
	 */
	Void deleteStop(int id);

	/**
	 * @param routeId
	 * @return {@link ArrayList} containing {@link Stop} of the specific
	 *         {@link Route}
	 */
	ArrayList<Stop> getStopsByRouteId(int routeId);

	/**
	 * @param stopId
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	ArrayList<apiClass> getTime(int stopId) throws IOException, InterruptedException, ParseException;

}
