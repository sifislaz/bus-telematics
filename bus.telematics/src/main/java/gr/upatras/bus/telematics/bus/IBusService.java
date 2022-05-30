package gr.upatras.bus.telematics.bus;

import java.util.List;

/**
 * @author jlaza
 *
 */
public interface IBusService {
	
	/**
	 * @return all buses
	 */
	List<Bus> getAll();
	
	
	/**
	 * @param id
	 * @return a {@link Bus}
	 */
	Bus getById(int id);
	
	/**
	 * @param b
	 * @return the created {@link Bus}
	 */
	Bus createBus(Bus b);
	
	/**
	 * @param bus
	 * @return the changed {@link Bus}
	 */
	Bus editBus(Bus bus);
	/**
	 * @param the id of the {@link Bus} to be deleted
	 */
	Void deleteBus(int id);
}