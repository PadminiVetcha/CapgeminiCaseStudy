package org.padmini.railway.service;
import org.padmini.railway.entity.UserDetails;

public interface UserService 
{
	public UserDetails getUserDetailsById(int id);
	public void addUserDetails(UserDetails userDetails);
	
}
