package ticket.repositories;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ticket.controller.ArtistRepository;
import ticket.model.Artist;

@Named
public class ArtistController {
	
	List<Artist> artists;
	
	@Inject
	private ArtistRepository repository;
	private Artist artist;
	
	
	public Artist getArtist() {
		return artist;
	}


	public void setArtist(Artist artist) {
		this.artist = artist;
	}


	public void addArtist(Artist artist) {	
		repository.add(artist);
	}
	public List<Artist> showArtists(){
		artists = repository.findAll();
		return artists;
	}
	
	
	
}
