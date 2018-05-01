package Service;

import Pojo.Brand;
import Pojo.Coffee;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Candace
 * 
 * Handles connection to Brand table.
 * 
 */
@Stateless
public class BrandService {
    @PersistenceContext(unitName="InstantCoffeePU")
    private EntityManager em;
    
    /**
     * Load a Brand object by its ID.
     * 
     * @param id The ID field of this Brand.
     * @return The Brand associated with the given ID.
     */
    public Brand load(Long id) {
        return em.find(Brand.class, id);
    }
    /**
     * Persist a new Brand object to the database.
     * 
     * @param brand The new Brand to create.
     */
    public void create(Brand brand) {
        em.persist(brand);
    } 
    
    /**
     * Find all Brand names.
     * 
     * @return A List of Brand name fields.
     */
    public List<String> findAllNames() {
        Query q = em.createNamedQuery("Brand.FindAllNames");
        List<String> brands = (List<String>) q.getResultList();
        return brands;
    }
   
    /**
     * Retrieve all Brand objects from the database.
     * 
     * @return A List of all Brand objects.
     */
    public List<Brand> findAllBrands() {
        Query q = em.createNamedQuery("Brand.FindAllBrands");
        List<Brand> brands = (List<Brand>) q.getResultList();
        return brands;
    }
    
    /**
     * Retrieve the IDs of all Brand objects in the database.
     * 
     * @return A List of Brand ID fields.
     */
    public List<Long> FindAllIds() {
        Query q = em.createNamedQuery("Brand.FindAllIds");
        List<Long> brandIds = (List<Long>) q.getResultList();
        return brandIds;
    }
    
    public void delete(Brand brand) {
        List<Coffee> coffeeToDelete = new ArrayList<>();
        Query q;
        q = em.createQuery("SELECT c FROM Coffee c WHERE c.brand.id=:id");
        q.setParameter("id",brand.getId());
        for (Coffee c :(List<Coffee>)q.getResultList()) {
            coffeeToDelete.add(c);
        }
        for (Coffee c : coffeeToDelete ) {
            q = em.createQuery("DELETE FROM Coffee WHERE id=:id");
            q.setParameter("id",c.getId());
            q.executeUpdate();
        }
        /*
        q = em.createQuery("DELETE FROM Brand b WHERE b.id=:id");
        q.setParameter("id", brand.getId());
        q.executeUpdate();
        */
    }
}
