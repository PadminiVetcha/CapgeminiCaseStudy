package org.padmini.railway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
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
class CancelATicketServiceApplicationTests {
	
	@InjectMocks
	private UserServiceImpl userSerImpl;
	
	@Mock
	private UserRepository userRepo;

	@BeforeAll public void init() { MockitoAnnotations.initMocks(this); }

	@Test
	public void getAllTest()
	{
		List<UserDetails> detailsList=new ArrayList<UserDetails>();
		Passengers passengers = new Passengers(2, 4);
		UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345, "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
		detailsList.add(details);
		when(userRepo.findAll()).thenReturn(detailsList);
		List<UserDetails> detailsListNew=userSerImpl.getAll();
		assertEquals(1, detailsListNew.size());
	}
	
	@Test
	@DisplayName("Testing deleteUserDetails method")
	public void deleteUserDetailsTest()
	{
		Passengers passengers = new Passengers(2, 4);
		UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345, "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
		when(userRepo.findById(1)).thenReturn(Optional.of(details));
		userSerImpl.deleteBookingDetails(1);
		List<UserDetails> detailsListNew=userSerImpl.getAll();
		assertEquals(0, detailsListNew.size());

	}
}
