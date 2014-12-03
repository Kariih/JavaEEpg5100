package resources;


import org.junit.Assert;
import org.junit.Before;

import static org.mockito.Mockito.*;
import ticket.controller.ConcertController;
import ticket.repositories.ArtistRepository;
import ticket.repositories.ConcertRepository;

public class ConcertControllerTest {
	private ConcertController cCon;
	
	@Before
	public void setUp() throws Exception{
		cCon = new ConcertController();
		ArtistRepository aRepo = mock(ArtistRepository.class);	
		ConcertRepository cRepo = mock(ConcertRepository.class);
	}
	//@Test
	public void testStartUp(){
		
	}
	//@Test
	public void testDelete(){
		
	}
	//@Test
	public void testCheckDates(){
		
	}
	
		
}
	

