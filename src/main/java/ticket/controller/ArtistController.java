package ticket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import ticket.model.Artist;
import ticket.repositories.ArtistRepository;

/*
 * Class for controlling the functionality representing artists.
 */

@ManagedBean
@RequestScoped
public class ArtistController {
	
	/*
	 * List of artists from database
	 */
	List<Artist> artists = new ArrayList<Artist>();
	/*
	 * Error message if something fails
	 */
	private String errorArtist;

	@Inject
	private ArtistRepository repository;
	Artist artist;
	
	public ArtistController() {
		setArtist(new Artist());
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}	
	public Artist getArtist() {
		return artist;
	}
	public String getErrorArtist() {
		return errorArtist;
	}
	public void setErrorArtist(String errorArtist) {
		this.errorArtist = errorArtist;
	}
	public void addArtist() {
		if(checkArtist() == 1){
			repository.add(this.artist);
		}else{
			errorArtist = "Et eller flere felt er feil utfylt";
		}
	}
	public List<Artist> getArtists(){
		artists = repository.findAll();
		return artists;
	}
	/*
	 * Check if all information needed is provided.
	 */
	private int checkArtist(){
		int checkPassed = 0;
		if(artist.getName().length() > 2){
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
