package org.padmini.railway.controller;
import java.util.List;

import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/cancel")
public class CancelUserController {
	
	@Autowired
	private UserServiceImpl userSerImpl;
	
	@GetMapping("/all")
	public List<UserDetails> getAll()
	{
		return userSerImpl.getAll();
	}
	
	@DeleteMapping("/{pnrNo}")
	public String deleteUserDetailsById(@PathVariable long pnrNo)
	{
		return userSerImpl.deleteBookingDetails(pnrNo);
	}

}
