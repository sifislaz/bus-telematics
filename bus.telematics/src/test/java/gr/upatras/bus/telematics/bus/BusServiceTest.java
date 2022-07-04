package gr.upatras.bus.telematics.bus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author jlaza
 * Unit tests for the {@link BusService} class
 */
class BusServiceTest {
	

	@Test
	void testGetById() {
		BusService test = new BusService();
		assertThrows(IllegalArgumentException.class, ()-> test.getById(-1));
	}

	@Test
	void testDeleteBus() {
		BusService test = new BusService();
		assertThrows(IllegalArgumentException.class, ()-> test.deleteBus(-1));
	}

}
