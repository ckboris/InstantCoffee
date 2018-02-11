/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Candace
 */

@Entity
@Table(name="BRAND")
@NamedQueries({
    @NamedQuery(name="Brand.FindAllNames", query="SELECT b.name FROM Brand b"),
    @NamedQuery(name="Brand.FindAllBrands", query="SELECT b FROM Brand b"),
    // Whatever other @NamedQueries go in here, comma-separated.
})
public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name="NAME")
    private String name; 
    
    @Column(name="SUPPLIER")
    private String supplier;
  
    @OneToMany(mappedBy="brand", cascade=CascadeType.ALL)
    private List<Coffee> varieties;
    
    public Brand() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<Coffee> getVarieties() {
        return varieties;
    }

    public void setVarieties(ArrayList<Coffee> varieties) {
        this.varieties = varieties;
    }

}
