package gr.upatras.bus.telematics.users;
import java.util.List;

public interface  IUserService {

	
	/**
	 * @return all Users
	 */
	List<Users> getAll();
	
	
	/**
	 * @param id
	 * @return a {@link Users}
	 */
	Users getById(int id);
	
	/**
	 * @param b
	 * @return the created {@link Users}
	 */
	Users createUsers(Users b);
	
	/**
	 * @param Users
	 * @return the changed {@link Users}
	 */
	Users editUser(Users User);
	/**
	 * @param the id of the {@link Users} to be deleted
	 */
	Void deleteUsers(int id);
}