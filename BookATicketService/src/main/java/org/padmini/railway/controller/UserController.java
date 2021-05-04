package org.padmini.railway.controller;
import javax.validation.Valid;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/{id}")
	public UserDetails getUserDetailsById(@PathVariable Integer id)
	{
		return userServiceImpl.getUserDetailsById(id);
	}
	
	@PostMapping("/add")
	public String addUserDetails(@Valid @RequestBody UserDetails userDetails)
	{
		//userDetails.setBookingId(sequenceGeneratorService)
		userDetails.setId(UserServiceImpl.getNextSequence(userDetails.SEQUENCE_NAME));
		userServiceImpl.addUserDetails(userDetails);
		return ("Your ticket id booked successfully...!!!"
				+ "Your ref number is "+ userDetails.getId());
	}
	/*
	 * @GetMapping("/all") public List<TrainDetails> getAllTrainDetails() { return
	 * userServiceImpl.getAllTrainDetails(); }
	 */

}
