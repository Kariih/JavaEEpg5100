package ticket.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
/*
 * Entity for concert
 */
@Entity
public class Concert {
	
	@GenericGenerator(name="incrementorC" , strategy="increment")
	@Id
	@GeneratedValue(generator="incrementorC")
	private int id;
	private String name;
	@Future
	@Temporal(TemporalType.DATE)
	private Date cdate;
	@ManyToOne
	@JoinColumn(name = "ARTIST_ID")
	private Artist artist;
	@Min(1)
	@Max(10000)
	private int price;
	private String place;
	@Size(min=1, max=1000)
	private String description;
	@Min(1)
	@Max(100000)
	private int ticketstotal;
	@Min(0)
	@Max(100000)
	private int ticketsSold;

	public Concert(Date cdate, Artist artist, int price,
			String place, String description, int ticketstotal,
			int ticketsSold) {
		super();
		this.cdate = cdate;
		this.artist = artist;
		this.price = price;
		this.place = place;
		this.description = description;
		this.ticketstotal = ticketstotal;
		this.ticketsSold = ticketsSold;
	}
	public Concert(){
		
	}	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getCdate() {
		return cdate;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
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

	public int getTicketstotal() {
		return ticketstotal;
	}

	public void setTicketstotal(int ticketstotal) {
		this.ticketstotal = ticketstotal;
	}

	public int getTicketsSold() {
		return ticketsSold;
	}

	public void setTicketsSold(int ticketsSold) {
		this.ticketsSold = ticketsSold;
	}
}
