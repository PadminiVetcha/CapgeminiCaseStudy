package org.padmini.railway.service;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.UserDetails;

public interface UserService 
{
	public UserDetails getUserDetailsById(long pnrNo);
	public void addUserDetails(UserDetails userDetails);
	public void proceedToPay(PaymentDetails payment);
	
}
