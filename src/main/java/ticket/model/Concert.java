package ticket.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Concert {
	
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	@ManyToOne
	private Artist artist;
	@Min(0)
	private int price;
	private String name;

	private String place;
	private String description;
	private int ticketstotal;
	private int ticketsleft;
	
	public Concert(Date consertDate, Artist artist, int price,
			String place, String description, int totalTicket,
			int totalTicketFree) {
		super();
		this.date = consertDate;
		this.artist = artist;
		this.price = price;
		this.place = place;
		this.description = description;
		this.ticketstotal = totalTicket;
		this.ticketsleft = totalTicketFree;
	}
	public Concert(){
		
	}	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getConsertDate() {
		return date;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setConsertDate(Date consertDate) {
		this.date = consertDate;
	}

	public Artist getArtistName() {
		return artist;
	}

	public void setArtistName(Artist artistName) {
		this.artist = artistName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalTicket() {
		return ticketstotal;
	}

	public void setTotalTicket(int totalTicket) {
		this.ticketstotal = totalTicket;
	}

	public int getTotalTicketLeft() {
		return ticketsleft;
	}

	public void setTotalTicketLeft(int totalTicketFree) {
		this.ticketstotal = totalTicketFree;
	}
	
}
