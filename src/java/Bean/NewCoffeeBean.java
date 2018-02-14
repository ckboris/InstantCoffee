/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Pojo.Brand;
import Pojo.Coffee;
import Service.BrandService;
import Service.CoffeeService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Candace
 */
@ManagedBean
@ViewScoped
public class NewCoffeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private CoffeeService coffeeService = new CoffeeService();
    private Coffee coffee;
    
    @EJB
    private BrandService brandService = new BrandService();
    private Brand brand;
    private List<Brand> brandList;
    
    public void init() {
        coffee = new Coffee();
        brandList = brandService.findAllBrands();
    }
    public void submit() {
        coffeeService.create(coffee);
        //Redirect string goes here 
    }

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

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
}
