package Bean;

import Pojo.Coffee;
import Service.CoffeeService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

/**
 * @author Candace
 * 
 * Handles displaying information about a single Coffee object.
 */
@ManagedBean
@ViewScoped
public class ViewCoffeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ViewCoffeeBean.class.getName());
    private long id;
    private Coffee coffee;
    @EJB
    private CoffeeService coffeeService;
    
   /**
    * Init method. Loads a Coffee object given an ID, or throws 
    * an error if that Coffee object cannot be loaded.
    */
    public void init() {
        try {
            coffee = coffeeService.load(id);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error loading the Coffee object", e);
        }
    }

    /* Getters and Setters */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
}
