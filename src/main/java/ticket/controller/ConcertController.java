package ticket.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
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
	List<String> topFiveConcert = new ArrayList<String>();
	
	@Inject
	private ConcertRepository repository;
	@Inject
	private ArtistRepository artistRepository;
	
	private int artistId;
	private int concertId;
	private int reservedTickets;
	private Date startDate;
	private Date endDate;
	
	public int getReservedTickets() {
		return reservedTickets;
	}
	public void setReservedTickets(int reservedTickets) {
		this.reservedTickets = reservedTickets;
		topFiveConcert = null;
	}	
	public void updateTickets(){
		Concert c = repository.findOne(concertId);
		int ticketsSold = c.getTicketsSold();
		ticketsSold += reservedTickets;
		c.setTicketsSold(ticketsSold);
		repository.update(c);
	}
	@PostConstruct
	public void startup(){
		concerts = repository.findAll();
	}
	public List<String> getTopFiveConcert() {
		return topFiveConcert;
	}
	public void setTopFiveConcert(List<String> topFiveConcert) {
		this.topFiveConcert = topFiveConcert;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
		concerts = repository.findAll();
	}
	public void deleteConcert(){
		repository.delete(concertId);
		concerts = repository.findAll();
	}
	public List<Concert> getConcerts(){
		return concerts;
	}
	public List<Concert> getConcertsByTime(){
		concerts = repository.findByDate(startDate, endDate);
		return concerts;
		
	}
    public Concert getInput(){
        return repository.findOne(concertId);
    }

	public void setInput(int id) {
        this.concertId = id;
    }
	public List<String> makeReport(){
		Map<String, Double> sortConcerts = new TreeMap<>();
		double percent;
		for (Concert c : concerts) {
			if(c.getTicketsSold()!= 0){
				percent = ((double)c.getTicketsSold() / (double)c.getTicketstotal())*100;
				sortConcerts.put(c.getName(), percent);
			}
		}
		Map<String, Double>sortedConcerts = new TreeMap<String, Double>(Collections.reverseOrder());	
		sortedConcerts.putAll(sortConcerts);
		int count = 0;
		for (Map.Entry<String, Double> e: sortedConcerts.entrySet()) {
			topFiveConcert.add(count + 1+ ": "+ e.getKey() + " Solgt: " + e.getValue() + "%");
			count++;
			if(count == 5){
				break;
			}
		}
		
		
		return topFiveConcert;
	}

}
