/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Pojo.Coffee;
import Service.CoffeeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Candace
 */
@ManagedBean
public class SearchBean {
    //Declare (but don't instantiate) CoffeeService with @EJB annotation and serialVersionUID
    private static final long serialVersionUID = 1L;
    
    @EJB
    private CoffeeService coffeeService;
    private String searchQuery;
    private List<Coffee> results = new ArrayList<>();

    //Empty, no-args constructor goes here

    @PostConstruct
    public void init() {

    }

    public void displaySearch() {
        results.clear();
        for (String str : strHandler(searchQuery)) {
            searchCoffees(str);
        }
    }

    public void searchCoffees(String str) {
        List<Coffee> coffees = coffeeService.searchByAll(str);
        try {
            for (Coffee i : coffees) {
                results.add(i);
            }
        } catch (Exception e) {
            System.out.println("Couldn't find coffee.");
        }
    }

    public List<String> strHandler(String str) {
            List<String> searchTerms = new ArrayList<>();
            try {
                if (str != null && !str.isEmpty()) {
                    searchTerms.addAll(Arrays.asList(str.split(" ")));
                }
            } catch (Exception ex) {
                System.out.println("Null search term.");
            }
            return searchTerms;
    }

    //Getters and setters for fields

    public CoffeeService getCoffeeService() {
        return coffeeService;
    }

    public void setCoffeeService(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
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
