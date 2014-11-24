package ticket.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ticket.model.Artist;
import ticket.repositories.ArtistRepository;

@Named
@RequestScoped
public class ArtistController {
	
	List<Artist> artists = showArtists();
	
	@Inject
	private ArtistRepository repository;
	private Artist artist;
	
	public Artist getArtist() {
		return artist;
	}

	public void addArtist() {	
		repository.add(this.artist);
	}
	public List<Artist> showArtists(){
		artists = repository.findAll();
		return artists;
	}
	
	
	
}
