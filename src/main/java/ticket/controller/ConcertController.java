package ticket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import ticket.model.Concert;
import ticket.repositories.ArtistRepository;
import ticket.repositories.ConcertRepository;

@ManagedBean
@RequestScoped
public class ConcertController {
	
	List<Concert> concerts = new ArrayList<Concert>();
	Concert concert = new Concert();
	
	@Inject
	private ConcertRepository repository;
	@Inject
	private ArtistRepository artistRepository;
	
	private int artistId;
	private int concertId;
	private int reservedTickets;
	
	public int getReservedTickets() {
		return reservedTickets;
	}
	public void setReservedTickets(int reservedTickets) {
		this.reservedTickets = reservedTickets;
	}

	private String searchString;
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public void updateTickets(){
		this.concert = concerts.get(concertId);
		this.concert.setTicketsleft(concert.getTicketstotal() - reservedTickets);
		repository.update(this.concert);
	}
	
	public int getConcertId() {
		return concertId;
	}
	public void setConcertId(int concertId) {
		this.concertId = concertId;
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public Concert getConcert() {
		return concert;
	}
	public void addConcert() {	
		concert.setArtist(artistRepository.findOne(artistId));
		repository.add(this.concert);
	}
	public void deleteConcert(){
		repository.delete(concertId);
	}
	public List<Concert> getConcerts(){
		concerts = repository.findAll();
		return concerts;
	}

    public Concert getInput(){
        return repository.findOne(concertId);
    }

    public void setInput(int id) {
        this.concertId = id;
    }
}
