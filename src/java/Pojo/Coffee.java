package Pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    
    @Pattern(regexp ="^[A-Za-z0-9][a-zA-Z'\\[\\]\\-\\s\\.\\,()]*([a-zA-Z0-9\\.])", 
             message="Illegal characters in name.")
    @Size(min= 0, max=128, message="Name cannot be over 128 characters.")
    @NotNull(message="Name can't be blank.")
    @Column(name="VARIETY")
    private String variety; 
    
    @Column(name="PRICE")
    private long price; 

    @NotNull
    @ManyToOne
    private Brand brand;

    @Column(name="ROAST")
    @Enumerated(EnumType.STRING)
    private Roast roast;
    
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

    public Roast getRoast() {
        return roast;
    }

    public void setRoast(Roast roast) {
        this.roast = roast;
    }
}
