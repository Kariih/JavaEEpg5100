package ticket.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Concert {
	
	@GenericGenerator(name="incrementorC" , strategy="increment")
	@Id
	@GeneratedValue(generator="incrementorC")
	private int id;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date cdate;
	@ManyToOne
	@JoinColumn(name = "ARTIST_ID")
	private Artist artist;
	private int price;
	private String place;
	private String description;
	private int ticketstotal;
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
