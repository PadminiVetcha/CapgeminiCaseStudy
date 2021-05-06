package org.padmini.railway.service;
import java.util.ArrayList;
import java.util.List;

import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	int id;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<UserDetails> getAll() {
		List<UserDetails> userDetails=new ArrayList<UserDetails>();
		userRepo.findAll().forEach(userDetails1 -> userDetails.add(userDetails1));
		return userDetails;
	}

	public String deleteBookingDetails(long pnrNo) {
		List<UserDetails> userDetails=userRepo.findAll();
		for(UserDetails x:userDetails)
		{
			System.out.println(x);
			if(x.getPnrNo()==pnrNo)
			{
				id=x.getId();
			}	
		}
		UserDetails existingDetails=userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Cannot delete as booking is done with PNR Number : "+pnrNo));
		userRepo.delete(existingDetails);
		//userRepo.deleteById(id);
		//return ResponseEntity.ok().build();
		return ("Your booking ticket with PNR Number : "+ pnrNo+ " is cancelled...!!!");
	}
	
	

}
