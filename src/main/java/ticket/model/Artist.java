package ticket.model;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/*
 * Entity for Artist
 */
@Entity
public class Artist {
	@GenericGenerator(name="incrementor" , strategy="increment")
	@Id
	@GeneratedValue(generator="incrementor")
	private int id;
	private String name;
	private String genre;
	@OneToMany
	@JoinColumn(name = "ARTIST_ID")
	private List<Concert> concerts;
	
	public Artist(int id, String name, String genre) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		
	}
	
	public Artist() {
	}
	
	public int getId() {
		return id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
