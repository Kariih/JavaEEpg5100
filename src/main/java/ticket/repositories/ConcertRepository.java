package ticket.repositories;

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
		em.persist(concert);
	}
	public void delete(int id){
		em.remove(id);
	}
	public void update(Concert concert){
		em.persist(concert);
	}
	@SuppressWarnings("unchecked")
	public List<Concert> findAll(){
	 return (List<Concert>)	em.createQuery("select c from " + Concert.class.getName() + " c").getResultList();	
	}
	public Concert findOne(int id){
		return em.find(Concert.class, id);
	}
}
