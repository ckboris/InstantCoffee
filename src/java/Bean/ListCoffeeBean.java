/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Pojo.Coffee;
import Service.CoffeeService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Candace
 */
@ManagedBean
@RequestScoped
public class ListCoffeeBean {
    @EJB
    private CoffeeService coffeeService;
    private static final long serialVersionUID = 1L;
    //logger 
    private List<Coffee> coffeeList = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public ListCoffeeBean() {
    }
    
    /**
     * Load all Coffee objects from database, or throw an error if they cannot 
     * be loaded.
     */
    public void loadCoffeeList() {
        try {
            coffeeList = coffeeService.findAll();
        } catch (Exception ex) {
            System.out.print("Coffee List cannot be loaded.");
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
