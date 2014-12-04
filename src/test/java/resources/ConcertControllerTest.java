package resources;


import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import ticket.controller.ConcertController;
import ticket.model.Concert;
import ticket.repositories.ArtistRepository;
import ticket.repositories.ConcertRepository;

public class ConcertControllerTest {
	private ConcertController concertController;
	
	@Before
	public void setUp() throws Exception{
		concertController = new ConcertController();
		ArtistRepository artistRepo = mock(ArtistRepository.class);
		ConcertRepository concertRepo = mock(ConcertRepository.class);
		concertController.setConcertRepository(concertRepo);
		concertController.setArtistRepository(artistRepo);
		
	}
	@Test
	public void testAddConcert(){
		Concert concert = concertController.getConcert();
		concert.setName("konsert");
		concert.setCdate(new Date(2015-02-05));
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konser snart i Oslo");
		concert.setTicketstotal(200);
		
		concertController.addConcert();
		List<Concert> concerts = concertController.getConcerts();
		
		// ArgumentCaptor
		/*
		 * THIS IS SHIT
		 */
		// Legge til sjekk på at repo tar imot riktig objekt.
	}
	
	
	
		
}
	

