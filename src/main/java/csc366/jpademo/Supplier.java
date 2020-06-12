package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;

import javax.validation.constraints.NotNull;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="address")
    private String address;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
        name = "SUPPLIER_LOCATION",
        joinColumns = { @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "ID") },
        inverseJoinColumns = { @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID") }
    )
    private List<Location> locations = new ArrayList<>();

    @OneToMany(
        mappedBy = "supplier",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<ShipmentOrder> shipmentOrders = new ArrayList<>();

    public Supplier() { }

    public Supplier(String address) {
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Location> getLocations() {
        return this.locations;
    }
    public void addLocation(Location l) {
        this.locations.add(l);
    }
    public void removeLocation(Location l) {
        this.locations.remove(l);
    }

    public List<ShipmentOrder> getShipmentOrders() {
        return this.shipmentOrders;
    }
    public void addShipmentOrder(ShipmentOrder s) {
        this.shipmentOrders.add(s);
        // s.setSupplier(this);
    }
    public void removeShipmentOrder(ShipmentOrder s) {
        this.shipmentOrders.remove(s);
        s.setSupplier(null);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Supplier.class.getSimpleName() + "[" , "]");
        // sj.add(id.toString()).add(address).add("locations="+locations.toString()).add("shipment orders="+shipmentOrders.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier)) return false;
        return id != null && id.equals(((Supplier) o).getId());
    }
}