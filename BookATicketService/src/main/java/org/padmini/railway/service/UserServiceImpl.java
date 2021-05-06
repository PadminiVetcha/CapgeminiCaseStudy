package org.padmini.railway.service;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;

import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.DatabaseSequence;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService 
{
	int id;
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserPaymentRepository userPayRepo;
	
	@Autowired
	private static MongoOperations mongo;
	
	private UserServiceImpl(MongoOperations mongo) {
		this.mongo=mongo;
	}
	@Override
	public UserDetails getUserDetailsById(long pnrNo) {
		List<UserDetails> userDetails=userRepo.findAll();
		for(UserDetails x:userDetails) {
			//System.out.println(x);
			if(x.getPnrNo()==pnrNo) {
				id=x.getId();
			}	
		}
		return userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No ticket is booked with PNR Number : "+ pnrNo));	
	}
	
	@Override
	public void addUserDetails(UserDetails userDetails) {
		userRepo.save(userDetails);	
	}
	
	 @Override
	public void proceedToPay(@Valid PaymentDetails payment) {
		  userPayRepo.save(payment); 
	}
	 
	//to update payment field in user details after successful payment
	  public void updateUserPaymentDetails(long pnrNo)
	  {
		  List<UserDetails> det=userRepo.findAll();
		  for(UserDetails x:det) {
			  System.out.println(x);
				if(x.getPnrNo()==pnrNo) {
					x.setPayment("Successful");
					userRepo.save(x);
				}
		  }
	  }
	
	 //to get autogenerated id 
	public static int getNextSequence(String key) 
	{
	  DatabaseSequence dbSeq=mongo.findAndModify(query(where("id").is(key)), 
			  new Update().inc("seq",1), options().returnNew(true).upsert(true),
			  DatabaseSequence.class); 
	  return !Objects.isNull(dbSeq) ? dbSeq.getSeq() : 1;
	  } 
}
