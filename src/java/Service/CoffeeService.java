/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Pojo.Coffee;
import java.util.List;
import javax.ejb.Stateless;
import javax.mail.FetchProfile.Item;
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
    
    public Coffee load(Long id) {
        return em.find(Coffee.class, id);
    }
    public void create(Coffee coffee) {
        em.persist(coffee);
    } 
    public List<Coffee> findAll() {
        Query q = em.createNamedQuery("Coffee.FindAll");
        List<Coffee> coffees = (List<Coffee>) q.getResultList();
        return coffees;
    }
    // The rest of the queries
}
