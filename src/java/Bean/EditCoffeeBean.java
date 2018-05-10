package Bean;

import Pojo.Coffee;
import Service.BrandService;
import Service.CoffeeService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Candace
 * 
 * Handles edits made to Coffee objects.
 */
@ManagedBean
@ViewScoped
public class EditCoffeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ViewCoffeeBean.class.getName());
    @EJB
    private CoffeeService coffeeService; 
    private Coffee coffee;
    private long id;
    
    @EJB
    private BrandService brandService;

    /**
     * Empty constructor.
     */
    public EditCoffeeBean() {
    }
    
    /**
     * Constructor that takes a Coffee object.
     * 
     * @param coffee The Coffee object being edited.
     */
    public EditCoffeeBean(Coffee coffee) {
        this.coffee = coffee;
    }
    
    /**
     * Init method. Loads the Coffee object by ID.
     * 
     * @return Null if the Coffee object with a given ID is loaded, 
     * otherwise redirect to index.
     */
    public String init() {
        try {
            //coffee = coffeeService.load(1L);
            coffee = coffeeService.load(id);
            return null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error loading the Coffee object to edit", e);
            return "/";
        }
    }
    
    /**
     * Save edits to a given Coffee object. 
     * 
     * @return To ListCoffee view if the edit is successful, 
     * otherwise redirect to index.
     */
    public String submit() {
        try {
            coffee.setVariety(coffee.getVariety());
            coffeeService.update(coffee);
            return "/ListCoffee.xhtml?faces-redirect=true";
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not update Coffee object");
            return "/";
        }
    }
    
    /**
     * Delete a given Coffee object.
     * 
     * @return To ListCoffee view if the edit is successful, 
     * otherwise redirect to index.
     */
    public String delete() {
        System.out.println("In delete");
        try {
            System.out.println("In try");
            brandService.delete(coffee.getBrand());
            //coffeeService.delete(coffee);
            return "/ListCoffee.xhtml?faces-redirect=true";
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not delete Coffee object, ", e);
            return "/";
        }
    } 

    /* Getters and Setters */
    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
