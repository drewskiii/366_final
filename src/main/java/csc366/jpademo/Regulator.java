package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;

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
// Regulator class written by Zachary Liu
@Entity  // indicates that this class maps to a database table
@Table(name = "regulator",
	uniqueConstraints = @UniqueConstraint(columnNames={"email"})
	)

public class Regulator {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long id;

   @Column(name="first_name")
   private String firstName;

   @Column(name="last_name")
   private String lastName;

   @NotNull
   @Column(unique=true, name="email")
   private String email;

   @ManyToOne
   @JoinColumn(name = "owner_id", referencedColumnName = "id")
   private Owner owner;

   public Regulator() { }

   public Regulator(String firstName, String lastName, String email) {
   		this.firstName = firstName;
   		this.lastName = lastName;
   		this.email = email;
   }

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Owner getOwner() {
   	  return this.owner;
   }

   public void setContact(Owner owner) {
   	  this.owner = owner;
   }

   @Override
   public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Regulator)) {
            return false;
        }
        Regulator regulator = (Regulator) o;
        return this.id == regulator.id;
   }

   @Override
   public String toString() {
   		return "{" +
         " id='" + getId() + "'" +
         ", firstName='" + getFirstName() + "'" +
         ", lastName='" + getLastName() + "'" +
         ", email='" + getEmail() + "'" +
         ", contact='" + getOwner() + "'" +
         "}";
   }
}