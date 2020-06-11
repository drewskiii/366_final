package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;

import javax.validation.constraints.NotNull;

@Entity
public class ShipmentOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_shipped")
    private Date dateShipped;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = true)
    private Supplier supplier;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateShipped() {
        return this.dateShipped;
    }
    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public Product getProduct() {
        return this.product;
    }
    public void setProduct(Product p) {
        this.product = p;
    }
    
    public Location getLocation() {
        return this.location;
    }
    public void setLocation(Location l) {
        this.location = l;
        // l.addShipmentOrder(this);
    }

    public Supplier getSupplier() {
        return this.supplier;
    }
    public void setSupplier(Supplier s) {
        this.supplier = s;
        s.addShipmentOrder(this);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , ShipmentOrder.class.getSimpleName() + "[" , "]");
        // sj.add(id.toString()).add(dateShipped.toString).add("product="+product.toString()).add("location="+location.toString().add("supplier="+supplier.toString()));
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShipmentOrder)) return false;
        return id != null && id.equals(((ShipmentOrder) o).getId());
    }
}