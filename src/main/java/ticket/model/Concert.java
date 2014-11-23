package ticket.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Concert {
	
	@Id
	@GeneratedValue
	private int id;
	private Date consertDate;
	@ManyToOne
	private Artist artist;
	@Min(0)
	private int price;
	private String place;
	private String description;
	private int totalTicket;
	private int totalTicketLeft;
	
	public Concert(Date consertDate, Artist artist, int price,
			String place, String description, int totalTicket,
			int totalTicketFree) {
		super();
		this.consertDate = consertDate;
		this.artist = artist;
		this.price = price;
		this.place = place;
		this.description = description;
		this.totalTicket = totalTicket;
		this.totalTicketLeft = totalTicketFree;
	}

	public Date getConsertDate() {
		return consertDate;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setConsertDate(Date consertDate) {
		this.consertDate = consertDate;
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
		return totalTicket;
	}

	public void setTotalTicket(int totalTicket) {
		this.totalTicket = totalTicket;
	}

	public int getTotalTicketLeft() {
		return totalTicketLeft;
	}

	public void setTotalTicketLeft(int totalTicketFree) {
		this.totalTicketLeft = totalTicketFree;
	}

	@Override
	public String toString() {
		return "Consert [consertDate=" + consertDate + ", artistName="
				+ artist + ", price=" + price + ", place=" + place
				+ ", description=" + description + ", totalTicket="
				+ totalTicket + ", totalTicketFree=" + totalTicketLeft + "]";
	}
	
}
