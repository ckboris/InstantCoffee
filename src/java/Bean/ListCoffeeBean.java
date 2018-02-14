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

/**
 *
 * @author Candace
 */
public class ListCoffeeBean {
    @EJB
    private CoffeeService coffeeService;
    private static final long serialVersionUID = 1L;

    //logger 
    private List<Coffee> coffeeList = new ArrayList<>();

    //Empty, no-args constructor.

    public void loadCoffeeList() {
        coffeeList = coffeeService.findAll();
    }
    
    //Getters and setters for items.
}
