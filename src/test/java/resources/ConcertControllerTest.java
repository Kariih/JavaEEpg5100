package resources;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ticket.controller.ConcertController;
import ticket.model.Concert;
import ticket.repositories.ArtistRepository;
import ticket.repositories.ConcertRepository;

@RunWith(MockitoJUnitRunner.class)
public class ConcertControllerTest {
	
	@Mock
	private ConcertRepository repository;
	
	@Mock
	private ArtistRepository artistRepository;
	
	@InjectMocks
	private ConcertController concertController;
	
	@Test
	public void testAddConcertIsStored(){
		Date now = new Date();
		now.setTime(now.getTime());
		Concert concert = concertController.getConcert();
		concert.setName("konsert");
		concert.setCdate(now);
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konsert snart i Oslo");
		concert.setTicketstotal(200);
		
		List<Concert> concerts = new ArrayList<Concert>();
		concerts.add(concert);
		
		when(repository.findAll()).thenReturn(concerts);
		
		concertController.addConcert();
		verify(repository).add(concert);
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
	}
	@Test
	public void testAddConcertIsNotStored(){
		Date now = new Date();
		now.setTime(now.getTime());
		Concert concert = concertController.getConcert();
		concert.setName("konsert");
		concert.setCdate(now);
		concert.setPrice(100);
		concert.setPlace(null);
		concert.setDescription("konsert snart i Oslo");
		concert.setTicketstotal(200);
		
		List<Concert> concerts = new ArrayList<Concert>();
		concerts.add(concert);
		
		when(repository.findAll()).thenReturn(concerts);
		
		concertController.addConcert();
		verifyNoMoreInteractions(repository);
	}
	
	
	@Test
	public void testDeleteConcertIsDeleted(){
		concertController.setConcertId(1);
		concertController.deleteConcert();
		
		verify(repository).delete(1);
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);	
	}
	@Test
	public void testStartupFindAll(){
		
		Concert concert = concertController.getConcert();
		Date now = new Date();
		now.setTime(now.getTime());
		concert.setName("konsert");
		concert.setCdate(now);
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konser snart i Oslo");
		concert.setTicketstotal(200);
		
		List<Concert> concerts = new ArrayList<Concert>();
		concerts.add(concert);
		
		when(repository.findAll()).thenReturn(concerts);
		
		concertController.startup();
		
		Assert.assertEquals(1, concertController.getConcerts().size());
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
	}
	@Test
	public void updateTicketsIsNotUpdated(){
		Date now = new Date();
		now.setTime(now.getTime());
		Concert concert = concertController.getConcert();
		concert.setName("konsert");
		concert.setCdate(now);
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konser snart i Oslo");
		concert.setTicketstotal(200);
		concert.setTicketsSold(199);
		
		when(repository.findOne(concert.getId())).thenReturn(concert);
		
		concertController.setConcertId(concert.getId());
		concertController.setReservedTickets(4);
		concertController.updateTickets();
	
		
		verify(repository).findOne(concert.getId());
		verifyNoMoreInteractions(repository);		
	}
	@Test
	public void updateTicketsIsUpdated(){
		Date now = new Date();
		now.setTime(now.getTime());
		Concert concert = concertController.getConcert();
		concert.setName("konsert");
		concert.setCdate(now);
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konser snart i Oslo");
		concert.setTicketstotal(200);
		concert.setTicketsSold(150);
		
		
		when(repository.findOne(concert.getId())).thenReturn(concert);
		
		concertController.setConcertId(concert.getId());
		concertController.setReservedTickets(4);
		concertController.updateTickets();
		
		
		verify(repository).findOne(concert.getId());
		verify(repository).update(concert);
		verifyNoMoreInteractions(repository);
		
	}
	@Test
	public void testFindConcertByTimeIsFound(){
		Date now = new Date();
		now.setTime(now.getTime());
		Concert concert = concertController.getConcert();
		concert.setName("konsert");
		concert.setCdate(now);
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konser snart i Oslo");
		concert.setTicketstotal(200);
		concert.setTicketsSold(150);
		
		List<Concert> concerts = new ArrayList<Concert>();
		concerts.add(concert);
		
		
		Date startDate = new Date();
		startDate.setTime(startDate.getTime() - 50000);
		Date endDate = new Date();
		endDate.setTime(endDate.getTime() + 50000);
		
		concertController.setStartDate(startDate);
		concertController.setEndDate(endDate);		
		
		concertController.getConcertsByTime();
		
		verify(repository).findByDate(startDate, endDate);
		verifyNoMoreInteractions(repository);
		
	}
	@Test
	public void testFindConcertByTimeIsNotFound(){
		Date now = new Date();
		now.setTime(now.getTime());
		Concert concert = concertController.getConcert();
		concert.setName("konsert");
		concert.setCdate(now);
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konser snart i Oslo");
		concert.setTicketstotal(200);
		concert.setTicketsSold(150);
		
		List<Concert> concerts = new ArrayList<Concert>();
		concerts.add(concert);
		
		concertController.getConcertsByTime();
		
		verifyNoMoreInteractions(repository);
	}
	
		
}
	

