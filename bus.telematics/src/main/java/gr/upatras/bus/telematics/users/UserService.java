package gr.upatras.bus.telematics.users;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.upatras.bus.telematics.json.JSONHandler;


public class UserService implements IUserService{
	@Autowired
	List<LinkedHashMap> usersJSON;
	ArrayList<Users> users = new ArrayList<Users>();
	
	public UserService (){
		super();
		usersJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("user.json");
		for (LinkedHashMap m : usersJSON) {
			// get bus key values
			int id = Integer.parseInt(m.get("id").toString());
			double lng = Double.parseDouble(m.get("long").toString());
			double lat = Double.parseDouble(m.get("lat").toString());
			
			
			users.add(new Users(id, lng, lat));
			
		
		
	}}
	
	
	/**
	 * Return the list of available users
	 */
	@Override
	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @param id Return the user with the id if exists
	 */

	@Override
	public Users getById(int id) {
		for (Users u : users) {
			if (u.getUser_id() == id) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * @param u
	 * @return the created {@link User}
	 */
	@Override
	public Users createUsers(Users u) {
		users.add(u);
		return u;
	}
	
	/**
	 *@param User
	 *@return the changed {@link User}
	 */

	@Override
	public Users editUser(Users User) {
		Users temp = getById(User.getUser_id());
		if (temp != null) {
			temp.setLongitude(User.getLongitude());
			temp.setLatitude(User.getLatitude());
			
		}

		return temp;
	}
	
	/**
	 *@param id
	 delete the user from list
	 */
	

	@Override
	public Void deleteUsers(int id) {
		for (Users u : users) {
			if (u.getUser_id() == id) {
				users.remove(u);
				break;
			}
		}
		return null;
	}
	

}
	


