/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Pojo.Coffee;
import Service.CoffeeService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Candace
 */
@ManagedBean
@ViewScoped
public class EditCoffeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ViewCoffeeBean.class.getName());
    @EJB
    private CoffeeService coffeeService; 
    private Coffee coffee;
    
    public String load() {
        try {
            coffee = coffeeService.load(1L);
            return null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not load Coffee to edit");
            return "/";
        }
    }
    
    public String editItem() {
        try {
            coffee.setVariety(coffee.getVariety());
            coffeeService.update(coffee);
            return "/";
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not update Coffee object");
            return "/";
        }
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
    
    
}
