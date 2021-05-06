package org.padmini.railway.service;

import java.util.List;

import org.padmini.railway.entity.UserDetails;
import org.springframework.http.ResponseEntity;

public interface UserService {
	public String deleteBookingDetails(long pnrNo); 
	public List<UserDetails> getAll();
}
