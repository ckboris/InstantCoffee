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
     * Empty constructor.
     */
    public NewCoffeeBean() {
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
            System.out.print("Exception " + ex + " occured. Coffee not submitted.");
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
    
    /**
     * Accessor method for CoffeeService.
     * 
     * @return CoffeeService object.
     */
    public CoffeeService getCoffeeService() {
        return coffeeService;
    }

    
    public void setCoffeeService(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    /**
     * Accessor method for Coffee object.
     * 
     * @return Coffee object.
     */
    public Coffee getCoffee() {
        return coffee;
    }

    /**
     * Mutator method for
     * 
     * @param coffee The Coffee object to set the coffee field to.
     */
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    /**
     * Accessor method for brand field.
     * 
     * @return The Brand object.
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Mutator method for brand field.
     * 
     * @param brand The Brand to set the brand field to.
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * Accessor method for BrandService.
     * 
     * @return The BrandService object.
     */
    public BrandService getBrandService() {
        return brandService;
    }

    /**
     * Mutator method for
     * 
     * @param brandService The BrandService to set the brandSerive field to.
     */
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * Accessor method for list of Brand IDs.
     * 
     * @return A List of BrandIDs.
     */
    public List<Long> getBrandIdList() {
        return brandIdList;
    }

    /**
     * Accessor method for roast field.
     * 
     * @return 
     */
    public Roast getRoast() {
        return roast;
    }

    /**
     * Mutator method for roast field.
     * 
     * @param roast The Roast to set the roast field to.
     */
    public void setRoast(Roast roast) {
        this.roast = roast;
    }

    /**
     * Accessor method for Brand list.
     * 
     * @return 
     */
    public List<Brand> getBrandList() {
        return brandList;
    }

    /**
     * M
     * @param brandList 
     */
    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
    
    
}
