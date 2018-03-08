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
 *
 * @author Candace
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
    
    //TESTING
    //private Long brandId;
    
    /**
     * Initialize values in NewCoffee view.
     */
    @PostConstruct
    public void init() {
        try {
            //brand = new Brand();
            brandList = brandService.findAllBrands();
            //brandId = 1L;
            coffee = new Coffee();
           //brandIdList = brandService.FindAllIds();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error initializing the Coffee object or Brand ID list", e);
        }
    }

    //TESTING
    /*
    public NewCoffeeBean(Coffee coffee, Brand brand) {
        this.coffee = coffee;
        this.brand = brand;
    }
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
            System.out.println("Details: " + 
                    coffee.getVariety() + ", " + 
                    coffee.getId() + ", " +
                    coffee.getBrand().getId()
                    );
            coffee.setBrand(brandService.load(coffee.getBrand().getId()));
            coffeeService.create(coffee);
            
            //TESTING
            //coffeeService.persistCoffee(brandId, coffee);
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
    
    /* Getters and Setters for fields */
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

    public void setBrandIdList(List<Long> brandList) {
        this.brandIdList = brandList;
    }

    public Roast getRoast() {
        return roast;
    }

    public void setRoast(Roast roast) {
        this.roast = roast;
    }

    //TESTING
    /*
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
    */

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
    
    
}
