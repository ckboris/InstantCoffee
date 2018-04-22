package Bean;

import Pojo.Coffee;
import Service.CoffeeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author Candace
 * 
 * Handles searching the database and presenting results.
 */
@ManagedBean
@RequestScoped
public class SearchBean {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ViewCoffeeBean.class.getName());
    
    @EJB
    private CoffeeService coffeeService;
    private String searchQuery;
    private List<Coffee> results = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public SearchBean() {
    }
    
    /**
     * Init method.
     */
    @PostConstruct
    public void init() {
    } 

    /**
     * Display the results of a given search query.
     */
    public void displaySearch() {
        results.clear();
        for (String str : stringHandler(searchQuery)) {
            searchCoffees(str);
        }
    }

    /**
     * Perform a search of the Coffee database table based on the given
     * search String(s), and add the results to a List.
     * 
     * @param str The search string.
     */
    public void searchCoffees(String str) {
        List<Coffee> coffees = coffeeService.searchByAll(str);
        try {
            for (Coffee i : coffees) {
                results.add(i);
            }
        } catch (Exception e) {
            LOG.log(Level.INFO, "Error performing search", e);

        }
    }

    /**
     * Split apart a search query into individuals strings, and handle 
     * empty search queries. 
     * 
     * @param str The search query entered in the search bar.
     * @return A List of Strings representing the parts of the search.
     */
    public List<String> stringHandler(String str) {
            List<String> searchTerms = new ArrayList<>();
            try {
                if (str != null && !str.isEmpty()) {
                    searchTerms.addAll(Arrays.asList(str.split(" ")));
                }
            } catch (Exception e) {
                LOG.log(Level.INFO, "Null search term", e);
            }
            return searchTerms;
    }

    /* Getters and Setters */
    public CoffeeService getCoffeeService() {
        return coffeeService;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Coffee> getResults() {
        return results;
    }

    public void setResults(List<Coffee> results) {
        this.results = results;
    }
    
}
