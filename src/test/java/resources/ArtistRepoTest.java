package resources;

import java.util.ArrayList;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import ticket.model.Artist;
import ticket.model.Concert;
import ticket.repositories.ArtistRepository;

@RunWith(MockitoJUnitRunner.class)
public class ArtistRepoTest {

	@Mock
	private EntityManager em;
	
	@Mock 
	private Query query;
	
	@InjectMocks
	private ArtistRepository artistRepository;
	
	
	@Test
	public void testAddArtist(){
		Artist artist = new Artist();
		artist.setName("Soilwork");
		artist.setGenre("Hard Rock");
		
		artistRepository.add(artist);
		verify(em).persist(artist);
		verifyNoMoreInteractions(em);
	}
	@Test
	public void testDelete(){
		int id = 1;
		artistRepository.delete(id);
		verify(em).remove(id);
		verifyNoMoreInteractions(em);
	}
	@Test
	public void testFindAll(){
		Artist artist = new Artist();
		artist.setName("Soilwork");
		artist.setGenre("Hard Rock");
		List<Artist> artists = new ArrayList<Artist>();
		artists.add(artist);
			
		when(query.getResultList()).thenReturn(artists);
		when(em.createQuery(Mockito.anyString())).thenReturn(query);
		
		List<Artist> returnedArtists = artistRepository.findAll();
		
		verify(query).getResultList();
		verifyNoMoreInteractions(query);
		
		Assert.assertEquals(1, returnedArtists.size());
		Assert.assertEquals(artists.indexOf(0), returnedArtists.indexOf(0));
				
		verify(em).createQuery(Mockito.anyString());
		verifyNoMoreInteractions(em);
	}
	
	
}
