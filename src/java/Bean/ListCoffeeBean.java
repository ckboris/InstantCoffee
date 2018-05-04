package Bean;

import Pojo.Coffee;
import Service.CoffeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author Candace
 * 
 * Handles displaying Coffee objects from the database.
 */
@ManagedBean
@RequestScoped
public class ListCoffeeBean {
    @EJB
    private CoffeeService coffeeService;
    private static final long serialVersionUID = 1L;
    private List<Coffee> coffeeList = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(ViewCoffeeBean.class.getName());
    
    /**
     * Load all Coffee objects from database, or throw an error if they cannot 
     * be loaded.
     */
    public void loadCoffeeList() {
        try {
            coffeeList = coffeeService.findAll();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error loading the Coffee objects from database: ", ex);
        }
    }

    /* Getters and Setters */
    public CoffeeService getCoffeeService() {
        return coffeeService;
    }

    public void setCoffeeService(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }
}
