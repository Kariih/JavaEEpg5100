package resources;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ticket.controller.ArtistController;
import ticket.model.Artist;
import ticket.repositories.ArtistRepository;

@RunWith(MockitoJUnitRunner.class)
public class ArtistControllerTest {
	
	@Mock
	private ArtistRepository repository;
	
	@InjectMocks
	private ArtistController artistController;
	
	@Test
	public void testAddArtistIsStored(){
		Artist artist = artistController.getArtist();
		artist.setName("Soilwork");
		artist.setGenre("Hard Rock");
		
		artistController.addArtist();
		verify(repository).add(artist);
		verifyNoMoreInteractions(repository);
	}
	@Test
	public void testAddArtistIsNotStored(){
		Artist artist = artistController.getArtist();
		artist.setName("");
		artist.setGenre("");
		
		artistController.addArtist();
		verifyNoMoreInteractions(repository);
	}
	@Test
	public void testGetAllArtist(){
		Artist artist = artistController.getArtist();
		artist.setName("Soilwork");
		artist.setGenre("Hard Rock");
		
		List<Artist> artists = new ArrayList<Artist>();
		artists.add(artist);
		
		when(repository.findAll()).thenReturn(artists);
		artistController.getArtists();
		
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
		
	}

}
