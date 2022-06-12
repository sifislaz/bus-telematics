package gr.upatras.bus.telematics.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.upatras.bus.telematics.bus.Bus;
import gr.upatras.bus.telematics.stop.Stop;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jlaza
 *
 */
@RestController
public class RouteController {
	@Autowired
	private IRouteService routeService;
	private static final Logger log = LoggerFactory.getLogger(RouteController.class);

	/**
	 * @return all {@link Route} instances
	 */
	@ApiOperation(value = "Retrieves all routes", notes = "This operation retrieves all Routes", response = Route.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Route.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/route/", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public List<Route> getRoutes() {
		List<Route> routes = routeService.getAll(); // Get all routes
		return routes;
	}

	/**
	 * @param id
	 * @return the {@link Route} with the specific id
	 */
	@ApiOperation(value = "Retrieves a Route by ID", notes = "This operation retrieves a Route entity. ", response = Route.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Route.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/route/{id}", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Route getRouteById(@ApiParam(value="Route Id", required=true) @PathVariable("id") int id) {
		log.info(String.format("Getting route with id %s", id));
		return routeService.getById(id);
	}
	
	/**
	 * @param r
	 * @return the created {@link Route} instance
	 */
	@ApiOperation(value = "Creates a Route", notes = "This operation creates a Route entity.", response = Route.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = Route.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
	})
	@RequestMapping(value = "/route", produces = { "application/json;charset=utf-8" }, consumes = {
			"application/json;charset=utf-8" }, method = RequestMethod.POST)
	public ResponseEntity<Route> createRoute(@ApiParam(value="Route Instance", required=true) @RequestBody Route r){
		log.info("Will add a new Route");
		Route route = routeService.createRoute(r);
		return new ResponseEntity<Route>(route, HttpStatus.OK);
	}
	
	
	/**
	 * @param r
	 * @param id
	 * @return the edited {@link Route} instance
	 */
	@ApiOperation(value = "Updates partially a Route", nickname = "patchRoute", notes = "This operation updates partially a Route entity.", response = Route.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updated", response = Route.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
	})
	@RequestMapping(value = "/route/{id}", produces = { "application/json;charset=utf-8" }, consumes = {
			"application/json;charset=utf-8" }, method = RequestMethod.PATCH)
	public ResponseEntity<Route> editRoute(
			@ApiParam(value="Route Instance", required=true) @RequestBody Route r,
			@ApiParam(value="Route ID", required=true) @PathVariable int id){
			Route route = routeService.editRoute(id, r);
			return new ResponseEntity<Route>(route, HttpStatus.OK);
	}
	/**
	 * @param id
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation(value = "Deletes a Route by ID", notes = "This operation deletes a Route entity. ", response = Route.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Route.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
	})
	@RequestMapping(value = "/route/{id}", produces = {
			"application/json;charset=utf-8" }, method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRoute(@ApiParam(value = "Route ID", required=true) @PathVariable("id") int id) {
		try {
			log.info(String.format("Going to delete route with id %s", id)); // delete stop, return OK
			return new ResponseEntity<Void>(routeService.deleteRoute(id), HttpStatus.OK);
		}
		catch(Exception e) {
			log.error("Couldn't resialize response for content type application/json", e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * @param stopId
	 * @return the {@link Route} instances that contain the specific {@link Stop}
	 */
	@ApiOperation(value = "Gets routes by stop id",  notes = "This operation gets the routes that contain the stop with given id.", response = Route.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updated", response = Route.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
	})
	@RequestMapping(value="/route/stop/{stopId}", produces= {"application/json;charset=utf-8"}, method = RequestMethod.GET)
	public List<Route> getRoutesByStop(@ApiParam(value="Stop ID", required=true) @PathVariable("stopId") int stopId){
		log.info(String.format("Will return the routes that contain stop with id %s", stopId));
		List<Route> routes = routeService.getRoutesByStop(stopId);
		return routes;
	}

}
