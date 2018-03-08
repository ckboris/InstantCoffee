/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Pojo.Brand;
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
public class BrandService {
    @PersistenceContext(unitName="InstantCoffeePU")
    private EntityManager em;
    
    public Brand load(Long id) {
        return em.find(Brand.class, id);
    }
    public void create(Brand brand) {
        em.persist(brand);
    } 
    public List<String> findAllNames() {
        Query q = em.createNamedQuery("Brand.FindAllNames");
        List<String> brands = (List<String>) q.getResultList();
        return brands;
    }
    
    public List<Brand> findAllBrands() {
        Query q = em.createNamedQuery("Brand.FindAllBrands");
        List<Brand> brands = (List<Brand>) q.getResultList();
        return brands;
    }
    
    public List<Long> FindAllIds() {
        Query q = em.createNamedQuery("Brand.FindAllIds");
        List<Long> brandIds = (List<Long>) q.getResultList();
        return brandIds;
    }
    
    // The rest of the queries
}
