package ticket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import ticket.model.Artist;
import ticket.repositories.ArtistRepository;

@ManagedBean
@RequestScoped
public class ArtistController {
	
	List<Artist> artists = new ArrayList<Artist>();
	private String errorArtist;

	@Inject
	private ArtistRepository repository;
	Artist artist = new Artist();
	
	public Artist getArtist() {
		return artist;
	}

	public void addArtist() {
		if(checkArtist() == 1){
			repository.add(this.artist);
		}else{
			errorArtist = "Felt uriktig fylt ut";
		}
	}
	public List<Artist> getArtists(){
		artists = repository.findAll();
		return artists;
	}
	public String getErrorArtist() {
		return errorArtist;
	}

	public void setErrorArtist(String errorArtist) {
		this.errorArtist = errorArtist;
	}
	private int checkArtist(){
		int checkPassed = 0;
		if(artist.getName() != ""){
			checkPassed++;
		}
		if(artist.getGenre() != ""){
			checkPassed++;
		}
		if(checkPassed == 2){
			return 1;
		}
		return 0;
	}
	
	
}
