package ticket.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ticket.model.Concert;
import ticket.repositories.ConcertRepository;

@Named
@RequestScoped
public class ConcertController {
	
	List<Concert> concerts = showConcerts();
	
	@Inject
	private ConcertRepository repository;
	private Concert concert;
	
	public Concert getConcert() {
		return concert;
	}

	public void addArtist() {	
		repository.add(this.concert);
	}
	public List<Concert> showConcerts(){
		concerts = repository.findAll();
		return concerts;
	}
}
