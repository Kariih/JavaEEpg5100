package ticket.repositories;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import logg.LoggerInterceptor;
import ticket.model.Concert;
/*
 * repository class which contains functionality for database connection.
 */
@Interceptors(LoggerInterceptor.class)
@Stateless
public class ConcertRepository {
	
	@PersistenceContext(unitName = "concertunit")
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	/*
	 * Add Concert to database
	 */
	public void add(Concert concert){
		System.out.println(concert);
		em.persist(concert);
	}
	/*
	 * Delete Concert in database
	 */
	public void delete(int id){
		em.remove(findOne(id));
	}
	/*
	 * Update Concert in database
	 */
	public void update(Concert concert){
		em.merge(concert);
	}
	/*
	 * Find all Concerts in database
	 */
	@SuppressWarnings("unchecked")
	public List<Concert> findAll(){
	 return (List<Concert>)	em.createQuery("select c from " + Concert.class.getName() + " c").getResultList();	
	}
	public Concert findOne(int id){
		return em.find(Concert.class, id);
	}	
	/*
	 * Find all Concerts in database between dates
	 */
	public List<Concert> findByDate(Date start, Date end){
		@SuppressWarnings("unchecked")
		List<Concert> concerts = em.createQuery("SELECT c FROM " + Concert.class.getName() + " c WHERE c.cdate between :startDate AND :endDate")
		.setParameter("startDate", start, TemporalType.DATE).setParameter("endDate", end, TemporalType.DATE).getResultList();
		return concerts;
	}
}
