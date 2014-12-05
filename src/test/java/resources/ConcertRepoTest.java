package resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ticket.model.Concert;
import ticket.repositories.ConcertRepository;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConcertRepoTest {
	
	@Mock
	private EntityManager em;
	
	@Mock 
	private Query query;
	
	@InjectMocks
	private ConcertRepository concertRepository;
	
	
	@Test
	public void testAddConcert(){
		Concert concert = new Concert();
		concert.setName("konsert");
		concert.setCdate(new Date(2015-02-05));
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konser snart i Oslo");
		concert.setTicketstotal(200);
		concert.setTicketsSold(199);
		
		concertRepository.add(concert);
		verify(em).persist(concert);
		verifyNoMoreInteractions(em);
	}
	
	@Test
	public void testDelete(){		
		int id = 1;
		concertRepository.delete(id);
		verify(em).remove(em.find(Concert.class, id));
	}
	@Test
	public void testFindOne(){
		int id = 1;
		concertRepository.findOne(id);
		verify(em).find(Concert.class, id);
		verifyNoMoreInteractions(em);				
	}
	
	@Test
	public void testFindAll(){
		Concert concert = new Concert();
		concert.setName("konsert");
		concert.setCdate(new Date(2015-02-05));
		concert.setPrice(100);
		concert.setPlace("Oslo");
		concert.setDescription("konser snart i Oslo");
		concert.setTicketstotal(200);
		concert.setTicketsSold(199);
		
		List<Concert> concerts = new ArrayList<Concert>();
		concerts.add(concert);
			
		when(query.getResultList()).thenReturn(concerts);
		when(em.createQuery(Mockito.anyString())).thenReturn(query);
		
		List<Concert> returnedConcerts = concertRepository.findAll();
		
		verify(query).getResultList();
		verifyNoMoreInteractions(query);
		
		Assert.assertEquals(1, returnedConcerts.size());
		Assert.assertEquals(concerts.indexOf(0), returnedConcerts.indexOf(0));
				
		verify(em).createQuery(Mockito.anyString());
		verifyNoMoreInteractions(em);
	}

}
