package Service;

import Pojo.Coffee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Candace
 */
@Stateless
public class CoffeeService {
    @PersistenceContext(unitName="InstantCoffeePU")
    private EntityManager em;
    
    /**
     * Load a Coffee object given an ID.
     * 
     * @param id Identifier for the Coffee object to load.
     * @return Coffee object associated with the ID.
     */
    public Coffee load(Long id) {
        return em.find(Coffee.class, id);
    }
    
    /**
     * Add a new Coffee object to the database.
     * 
     * @param coffee The new Coffee object to be persisted.
     */
    public void create(Coffee coffee) {
        em.persist(coffee);
    } 
    
    /**
     * Get all Coffee objects in the database.
     * 
     * @return A List containing all Coffee objects in the database.
     */
    public List<Coffee> findAll() {
        Query q = em.createNamedQuery("Coffee.FindAll");
        List<Coffee> coffees = (List<Coffee>) q.getResultList();
        return coffees;
    }
    
    /**
     * Update a Coffee object in the database.
     * 
     * @param coffee The Coffee object being updated.
     */
    public void update(Coffee coffee) {
        em.merge(coffee);
    }
    
    /**
     * Query the Coffee table based on a given search string.
     * 
     * @param searchString The search text to query by.
     * @return A List of Coffee objects matching the search.
     */
    public List<Coffee> searchByAll(String searchString) {
        Query q = em.createNamedQuery("Coffee.findByAll");
        //Note: This MUST MATCH THE NAME GIVEN IN THE VIEW'S VIEWPARAM!!!
        q.setParameter("searchString",("%"+searchString+"%").toLowerCase());
        List<Coffee> coffees = (List<Coffee>) q.getResultList();
        return coffees;
    }
    
    public void delete(Coffee coffee) {
        em.merge(coffee);
        em.remove(coffee);
        
    }
}
