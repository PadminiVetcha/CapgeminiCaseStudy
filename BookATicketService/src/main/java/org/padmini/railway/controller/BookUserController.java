package org.padmini.railway.controller;
import javax.validation.Valid;

import org.padmini.railway.entity.PaymentDetails;
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
@RequestMapping("/user/book")
public class BookUserController 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/{pnrNo}")
	public UserDetails getUserDetailsById(@PathVariable long pnrNo)
	{
		return userServiceImpl.getUserDetailsById(pnrNo);
	}
	
	@PostMapping("/add")
	public String addUserDetails(@Valid @RequestBody UserDetails userDetails)
	{
		userDetails.setId(UserServiceImpl.getNextSequence(userDetails.SEQUENCE_NAME));
		userDetails.setPnrNo();
		userDetails.setPayment("Pending");
		userServiceImpl.addUserDetails(userDetails);
		return ("Your ticket id booked successfully...!!!  "
				+ "Your pnr number is "+ userDetails.getPnrNo());
	}
	/*
	 * @GetMapping("/all") public List<TrainDetails> getAllTrainDetails() { return
	 * userServiceImpl.getAllTrainDetails(); }
	 */
	
	
	  @PostMapping("/pay") 
	  public String addPaymentDetails(@Valid @RequestBody
	  PaymentDetails payment) { 
		  userServiceImpl.proceedToPay(payment); 
		  return "Your payment is successful..!!"; }
	 
	
}
