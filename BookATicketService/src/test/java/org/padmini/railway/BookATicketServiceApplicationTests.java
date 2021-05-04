package org.padmini.railway;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.Passengers;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.service.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookATicketServiceApplicationTests {

	@InjectMocks
	private UserServiceImpl userSerImpl;
	
	@Mock
	private UserRepository userRepo;

	@BeforeAll public void init() { MockitoAnnotations.initMocks(this); }
	
	@Test
	@DisplayName("Testing addUserDetails method")
	public void addUserDetailsTest()
	{
		Passengers passengers = new Passengers(2, 4);
		UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345, "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
		userSerImpl.addUserDetails(details);
		verify(userRepo,times(1)).save(details);
	}
	
	@Test
	@DisplayName("Testing getUserDetailsById method")
	public void getUserDetailsByIdTest()
	{
		Passengers passengers = new Passengers(2, 4);
		Optional<UserDetails> details=Optional.of(new UserDetails("Padmini", 23, "Female", "Vzm", 12345, "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers));
		when(userRepo.findById(1)).thenReturn(details);
		UserDetails det=userSerImpl.getUserDetailsById(1);
		assertEquals("Padmini", det.getName());
		assertEquals(23, det.getAge());
		assertEquals("Female", det.getSex());
		assertEquals("Vzm", det.getAddress());
		assertEquals(12345, det.getTrainNo());
		assertEquals("Vishaka Express", det.getTrainName());
		assertEquals("Vzm", det.getStartStation());
		assertEquals("Vizag", det.getDestStation());
		assertEquals("FirstClassAc", det.getClassType());
		assertEquals(2, det.getPassengers().getAdults());
		assertEquals(4, det.getPassengers().getChildren());
	}
}
