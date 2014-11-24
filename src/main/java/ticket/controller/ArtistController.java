package ticket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ticket.model.Artist;
import ticket.repositories.ArtistRepository;

@ManagedBean
@RequestScoped
public class ArtistController {
	
	List<Artist> artists = new ArrayList<Artist>();

	@Inject
	private ArtistRepository repository;
	private Artist artist;
	
	public Artist getArtist() {
		return artist;
	}

	public void addArtist() {	
		repository.add(this.artist);
	}
	public List<Artist> getArtists(){
		artists = repository.findAll();
		return artists;
	}
	
	
	
}
