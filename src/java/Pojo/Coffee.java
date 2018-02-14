package Pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Candace
 */
@Entity
@Table(name="COFFEE")
@NamedQueries({
    @NamedQuery(name="Coffee.FindAll", query="SELECT c FROM Coffee c"),
    @NamedQuery(name = "Coffee.findByAll", query = "SELECT c FROM "
           + "Coffee c WHERE LOWER(c.variety) LIKE :searchString"
        // + " OR LOWER(c.brand) LIKE :searchString"
        )
})
public class Coffee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name="VARIETY")
    private String variety; 
    
    @Column(name="PRICE")
    private long price; 

    @ManyToOne
    private Brand brand;

    /**
     * No-args constructor.
     */
    public Coffee() {
    }

    /**
     * Get the ID of a Coffee object.
     * 
     * @return The id field of a Coffee object, as a long.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the variety (name) of a Coffee object.
     * 
     * @return The variety field of a Coffee object, as a String.
     */
    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    /**
     * Get the price of a Coffee object.
     * 
     * @return The price field of a Coffee object, as a long.
     */
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    /**
     * Get the Brand of a Coffee object.
     * 
     * @return The Brand object associated with a Coffee object.
     */
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
