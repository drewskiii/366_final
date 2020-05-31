package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(name = "manager")

public class Manager {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="hoursPerWeek")
    private int hoursPerWeek;

    @Column(unique=true, name="email")
    private String email;

    @OnetoOne(mappedBy="location")
    @JoinColumn(name = "location_id", nullable = false)
    private Locaiton location;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
    
    public Manager() { }
    
    public Manager(int hoursPerWeek, String email) {
    	this.hoursPerWeek = hoursPerWeek;
    	this.email = email;
    }
    
    public Long getId() {
	   return id;
    }
    public void setId(Long id) {
	   this.id = id;
    }
    
    public int getHoursPerWeek() {
	   return hoursPerWeek;
    }
    public void setHoursPerWeek(int hoursPerWeek) {
	   this.hoursPerWeek = hoursPerWeek;
    }

    public String getEmail() {
	   return email;
    }
    public void setEmail(String email) {
	   this.email = email;
    }

    public Location getLocation() {
	   return location;
    }
    public void setLocation(Location location) {
	   this.location = location;
    }

    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
        
    @Override
    public String toString() {
    	StringJoiner sj = new StringJoiner("," , Manager.class.getSimpleName() + "[" , "]");
    	sj.add(id.toString()).add(hoursPerWeek).add(email);
    	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Manager)) return false;
	return id != null && id.equals(((Manager) o).getId());
    }
    
}
