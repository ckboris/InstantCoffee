/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Pojo.Coffee;
import Service.CoffeeService;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Candace
 */
@ManagedBean
@ViewScoped
public class ViewCoffeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private Coffee coffee;
    @EJB
    private CoffeeService coffeeService;
    // Load an Coffee using its id
    public void init() {
        try {
            coffee = coffeeService.load(id);
        } catch (Exception e) {
            System.out.println("Could not load object"); 
            // Just an example, in reality we would use a logger
        }
    }
    // Submit a modified Coffee (by merging)
    public String submit() {
        try {
            coffeeService.update(coffee);
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Could not save object"); 
            return "";
            // Just an example, in reality we would use a logger
        }
    }
    //Getter and setters for coffee and id fields, but not coffeeService    

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
