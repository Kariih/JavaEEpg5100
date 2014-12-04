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
import javax.validation.constraints.Future;

import ticket.model.Concert;
import ticket.repositories.ArtistRepository;
import ticket.repositories.ConcertRepository;

@ManagedBean
@RequestScoped
public class ConcertController {
	
	/*
	 * List of concerts from database
	 */
	List<Concert> concerts = new ArrayList<Concert>();
	/*
	 * A spesific concert
	 */
	Concert concert = new Concert();
	/*
	 * List of top 5 concerts based on % sold tickes of total amout tickets
	 */
	List<String> topFiveConcert = new ArrayList<String>();
	
	@Inject
	private ConcertRepository repository;
	@Inject
	private ArtistRepository artistRepository;
	
	private int artistId;
	private int concertId;
	private int reservedTickets;
	@Future
	private Date startDate;
	@Future
	private Date endDate;
	private String errorConcert;
	private String errorDate;
	private String enoughTickets;
	/*
	 * Generate the list of concerts.
	 */
	@PostConstruct
	public void startup(){
		concerts = repository.findAll();
	}
	public String getErrorConcert() {
		return errorConcert;
	}
	public void setErrorConcert(String errorConcert) {
		this.errorConcert = errorConcert;
	}
	public String getErrorDate() {
		return errorDate;
	}
	public void setErrorDate(String errorDate) {
		this.errorDate = errorDate;
	}
	public int getReservedTickets() {
		return reservedTickets;
	}
	public void setReservedTickets(int reservedTickets) {
		this.reservedTickets = reservedTickets;
		topFiveConcert = null;
	}	
	/*
	 * update the amount of tickets left
	 */
	public void updateTickets(){
		Concert c = repository.findOne(concertId);
		int ticketsSold = c.getTicketsSold();
		if((ticketsSold + reservedTickets) > c.getTicketstotal()){
			enoughTickets ="Ikke nok billetter igjen: UTSOLGT!";
		}else{
			ticketsSold += reservedTickets;
			c.setTicketsSold(ticketsSold);
			repository.update(c);
		}
	}
	public List<String> getTopFiveConcert() {
		return topFiveConcert;
	}
	public void setTopFiveConcert(List<String> topFiveConcert) {
		this.topFiveConcert = topFiveConcert;
	}
	public Date getEndDate() {
		return (Date)endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return (Date)startDate;
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
	public String getEnoughTickets() {
		return enoughTickets;
	}
	public void setEnoughTickets(String enoughTickets) {
		this.enoughTickets = enoughTickets;
	}
	public void setConcertRepository(ConcertRepository repo) {
		this.repository = repo;
	}
	public void setArtistRepository(ArtistRepository repo) {
		this.artistRepository = repo;
	}
	/*
	 * Add a concert if all information needed is provide.
	 */
	public void addConcert() {	
		if(checkAddConcert() == 1){
			concert.setArtist(artistRepository.findOne(artistId));
			repository.add(this.concert);
			concerts = repository.findAll();
		}else{
			errorConcert = "Et eller flere felt er feil utfylt";
		}
	}
	/*
	 * delete an existing concert
	 */
	public void deleteConcert(){
		repository.delete(concertId);
		concerts = repository.findAll();
	}
	public List<Concert> getConcerts(){
		return concerts;
	}
	/*
	 * Get the list of concerts by timesearch
	 */
	public List<Concert> getConcertsByTime(){
		if(checkDates() == 1){
			concerts = repository.findByDate((Date)getStartDate(), (Date)getStartDate());
			return concerts;
		}else{
			errorDate = "Fyll in to datoer";
			return null;
		}
		
	}
    public Concert getInput(){
        return repository.findOne(concertId);
    }

	public void setInput(int id) {
        this.concertId = id;
    }
	/*
	 * make the top 5 concerts reports
	 */
	public List<String> makeReport(){
		Map<Double, String> sortConcerts = new TreeMap<>(Collections.reverseOrder());
		double percent;
		for (Concert c : concerts) {
			if(c.getTicketsSold()!= 0){
				percent = ((double)c.getTicketsSold() / (double)c.getTicketstotal())*100;
				sortConcerts.put(percent,c.getName());
			}
		}
		int count = 0;
		for (Map.Entry<Double, String> e: sortConcerts.entrySet()) {
			topFiveConcert.add(count + 1 + ": "+ e.getValue());
			count++;
			if(count == 5){
				break;
			}
		}
		
		return topFiveConcert;
	}
	/*
	 * Check if all information needed is provided for adding a concert.
	 */
	private int checkAddConcert(){
		int checkPassed = 0;
		if(concert.getName() != null){
			checkPassed++;
		}
		if(concert.getCdate() != null){
			checkPassed++;
		}
		if(concert.getPrice() >= 1){
			checkPassed++;
		}
		if(concert.getPlace() != null){
			checkPassed++;
		}
		if(concert.getDescription() != null){
			checkPassed++;
		}
		if(concert.getTicketstotal() >= 1){
			checkPassed++;
		}
		if(checkPassed == 6){
			errorConcert = "";
			return 1;
		}
		return 0;
	}
	/*
	 * Check if all information needed is provided for searching by dates.
	 */
	private int checkDates(){
		int checkPassed = 0;
		if(getStartDate() != null){
			checkPassed++;
		}
		if(getEndDate() != null){
			checkPassed++;
		}
		if(checkPassed == 2){
			errorDate = "";
			return 1;
		}
		return 0;
	}

}
