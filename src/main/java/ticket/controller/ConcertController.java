package ticket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import ticket.model.Concert;
import ticket.repositories.ConcertRepository;

@ManagedBean
@RequestScoped
public class ConcertController {
	
	List<Concert> concerts = new ArrayList<Concert>();
	
	@Inject
	private ConcertRepository repository;
	Concert concert = new Concert();
	
	public Concert getConcert() {
		return concert;
	}
	public void addConcert() {	
		repository.add(this.concert);
	}
	public void deleteConcert(Concert concert){
		repository.delete(concert.getId());
	}
	public List<Concert> getConcerts(){
		concerts = repository.findAll();
		return concerts;
	}
}
