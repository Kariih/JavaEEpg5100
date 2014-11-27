package ticket.repositories;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ticket.model.Concert;

@Stateless
public class ConcertRepository {
	
	@PersistenceContext(unitName = "concertunit")
	private EntityManager em;
	
	public void add(Concert concert){
		System.out.println(concert);
		em.persist(concert);
	}
	public void delete(int id){
		em.remove(findOne(id));
	}
	public void update(Concert concert){
		em.merge(concert);
	}
	@SuppressWarnings("unchecked")
	public List<Concert> findAll(){
	 return (List<Concert>)	em.createQuery("select c from " + Concert.class.getName() + " c").getResultList();	
	}
	public Concert findOne(int id){
		return em.find(Concert.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Concert> findByDate(Date start, Date end){
		return (List<Concert>)	em.createQuery("select c from " + Concert.class.getName() + " c where c.cdate between '"+ start +"' and '" + end +"'").getResultList();	
	}
}
