package ticket.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import logg.LoggerInterceptor;
import ticket.model.Artist;
/*
 * repository class which contains functionality for database connection.
 */
@Interceptors(LoggerInterceptor.class)
@Stateless
public class ArtistRepository {

	@PersistenceContext(unitName = "concertunit")
	private EntityManager em;
	/*
	 * Add Artist to database
	 */
	public void add(Artist artist){
		em.persist(artist);
	}
	/*
	 * Delete Artist in database
	 */
	public void delete(int id){
		em.remove(id);
	}
	/*
	 * Find all Artists in database
	 */
	@SuppressWarnings("unchecked")
	public List<Artist> findAll(){
	 return (List<Artist>)	em.createQuery("select a from " + Artist.class.getName() + " a").getResultList();	
	}
	public Artist findOne(int id){
		return em.find(Artist.class, id);
	}
}
