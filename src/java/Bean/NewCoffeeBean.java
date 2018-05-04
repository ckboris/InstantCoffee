package Bean;

import Pojo.Brand;
import Pojo.Coffee;
import Pojo.Roast;
import Service.BrandService;
import Service.CoffeeService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;

/**
 * @author Candace
 * 
 * Handles creation of new Coffee object.
 */
@ManagedBean
@ApplicationScoped
public class NewCoffeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ViewCoffeeBean.class.getName());

    @EJB
    private CoffeeService coffeeService = new CoffeeService();
    private Coffee coffee;
    
    @EJB
    private BrandService brandService = new BrandService();
    private Brand brand;
    private List<Long> brandIdList;
    private List<Brand> brandList;
    
    private Roast roast;
    
    /**
     * Initialize values in NewCoffee view. 
     */
    @PostConstruct
    public void init() {
        try {
            // Create a list of Brands for the selectOneMenu.
            brandList = brandService.findAllBrands();
            coffee = new Coffee();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error initializing the Coffee object or Brand ID list", e);
        }
    }
 
    /**
     * Submit a new Coffee object to the database.
     * 
     * @return Redirect string if Coffee object persists correctly, otherwise
     * throw error message and return null.
     */
    public String submit() {
        try {
            /* Persist this Coffee's Brand using the ID associated with it */
            coffee.setBrand(brandService.load(coffee.getBrand().getId()));
            coffeeService.create(coffee);
            return "/ListCoffee.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error submitting Coffee", ex);
            return null;
        }
    }

    /**
     * Get a list of the available Roasts.
     * 
     * @return An array of Roast objects.
     */
    public Roast[] getRoasts() {
        return Roast.values();
    }
    
    /* Getters and Setters */
    public CoffeeService getCoffeeService() {
        return coffeeService;
    }

    public void setCoffeeService(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BrandService getBrandService() {
        return brandService;
    }

    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    public List<Long> getBrandIdList() {
        return brandIdList;
    }

    public Roast getRoast() {
        return roast;
    }

    public void setRoast(Roast roast) {
        this.roast = roast;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
    
    
}
