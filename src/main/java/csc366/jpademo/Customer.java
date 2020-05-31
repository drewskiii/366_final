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
import javax.persistence.OrderColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(name = "customer",     // may be omitted for default naming
       // requires @Column(name=...) 
       uniqueConstraints = @UniqueConstraint(columnNames={"cid"})
)

public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;  // note: no annotation, still included in underlying table
    
    @NotNull
    @Column(unique=true, name="last_name")
    private String lastName;

    @Column(name="city")
    private String city;  // note: no annotation, still included in underlying table
    
    @Column(name="state")
    private String state;  // note: no annotation, still included in underlying table
    

    @OneToMany(mappedBy = "customer",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Transaction> transaction = new ArrayList<>();

    public Customer() {}

    public Customer(String firstName, String lastName, String city, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
        
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Customer.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(firstName).add(lastName).add(city).add(state);
	return sj.toString();
    }
}