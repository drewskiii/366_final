package csc366.jpademo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @NotNull
   @Column(name="first_name")
   private String firstName;

   @NotNull
   @Column(unique=true, name="last_name")
   private String lastName;

   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name = "position_id", insertable = false, updatable = false)
   private Position position;

   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name = "paidBy_id", insertable = false, updatable = false)
   private Owner paidBy;

   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name = "location_id", insertable = false, updatable = false)
   private Location location;

   @NotNull
   @Column
   private int hours;

   public Employee () {

   }


   public Employee(String firstName, String lastName,
    int hours, Location location, Owner owner, Position position) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.hours = hours;
      this.location = location;
      this.paidBy = owner;
      this.position = position;
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

   public String getLastName() {
      return lastName;
   }

   public Position getPosition() {
      return position;
   }

   public Owner getPaidBy() {
      return paidBy;
   }

   public Location getLocation() {
      return location;
   }

   public int getHours() {
      return hours;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setPosition(Position position) {
      this.position = position;
   }

   public void setPaidBy(Owner paidBy) {
      this.paidBy = paidBy;
   }

   public void setLocation(Location location) {
      this.location = location;
   }

   public void setHours(int hours) {
      this.hours = hours;
   }
}
