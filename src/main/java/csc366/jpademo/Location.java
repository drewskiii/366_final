package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OrderColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(name = "location",     // may be omitted for default naming
       // requires @Column(name=...) 
       uniqueConstraints = @UniqueConstraint(columnNames={"address"})
)
public class Location {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true, name="address")
    private String address;  // note: no annotation, still included in underlying table
    
    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
    
    public Location() { }
    
    public Location(String address, String city, String state) {
    	this.address = address;
    	this.city = city;
    	this.state = state;
    }
    
    public Long getId() {
	   return id;
    }
    public void setId(Long id) {
	   this.id = id;
    }
    
    public String getAddress() {
	   return address;
    }
    public void setAddress(String address) {
	   this.address = address;
    }

    public String getCity() {
	   return city;
    }
    public void setCity(String city) {
	   this.city = city;
    }

    public String getState() {
	   return state;
    }
    public void setState(String state) {
	   this.state = state;
    }

    public Manager getManager() {
        return manager;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }
    
    @Override
    public String toString() {
    	StringJoiner sj = new StringJoiner("," , Location.class.getSimpleName() + "[" , "]");
    	sj.add(id.toString()).add(address).add("manager="+manager.toString());
    	return sj.toString();
    }

}
