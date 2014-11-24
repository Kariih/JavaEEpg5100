package ticket.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Entity
public class Artist {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String genre;
	@OneToMany
	private List<Concert> concerts;
//	private String generesPossible[] = {"pop", "blues", "hard rock", "country"};
	
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

	@Override
	public String toString() {
		return "artist [id=" + id + ", name=" + name + ", generes=" + genre;
	}
	
}
