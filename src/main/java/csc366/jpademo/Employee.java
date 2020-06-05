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

   @NotNull
   @OneToMany
   private Position position;

   @NotNull
   @OneToOne
   private Owner paidBy;

   @OneToMany
   @JoinColumn
   private Location location;

   @NotNull
   @Column
   private int hours;

   public Employee(@NotNull String firstName, @NotNull String lastName,
    @NotNull Position position, @NotNull Owner paidBy, Location location,
    @NotNull int hours) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.position = position;
      this.paidBy = paidBy;
      this.location = location;
      this.hours = hours;
   }

   public Long getId() {
      return id;
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
