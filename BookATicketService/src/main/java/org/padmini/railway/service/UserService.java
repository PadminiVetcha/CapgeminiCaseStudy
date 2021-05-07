package org.padmini.railway.service;
import java.util.List;

import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.UserDetails;

public interface UserService 
{
	public List<UserDetails> getAll();
	public UserDetails getUserDetailsById(long pnrNo);
	public void addUserDetails(UserDetails userDetails);
	public void proceedToPay(PaymentDetails payment);
	public String deleteBookingDetails(long pnrNo); 
	
}
