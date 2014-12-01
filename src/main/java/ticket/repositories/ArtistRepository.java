package ticket.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import logg.Logger;
import ticket.model.Artist;

@Interceptors(Logger.class)
@Stateless
public class ArtistRepository {

	@PersistenceContext(unitName = "concertunit")
	private EntityManager em;
	
	public void add(Artist artist){
		em.persist(artist);
	}
	public void delete(int id){
		em.remove(id);
	}
	public void update(Artist artist){
		em.persist(artist);
	}
	@SuppressWarnings("unchecked")
	public List<Artist> findAll(){
	 return (List<Artist>)	em.createQuery("select a from " + Artist.class.getName() + " a").getResultList();	
	}
	public Artist findOne(int id){
		return em.find(Artist.class, id);
	}
}
