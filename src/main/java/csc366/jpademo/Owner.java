package csc366.jpademo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class Owner {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long id;

   @Column(name="first_name")
   private String firstName;

   @Column(name="last_name")
   private String lastName;

   @Column(unique=true, name="email")
   private String email;

   @ManyToOne
   @JoinColumn(name = "position_id", referencedColumnName = "id")
   private long position;

   @Column(name="date_appointed")
   private Date dateAppointed;

   public Owner() {
   }

   public Owner(String firstName, String lastName, String email, Date dateAppointed) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.dateAppointed = dateAppointed;
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

   public Date getDateAppointed() {
      return this.dateAppointed;
   }

   public void setDateAppointed(Date dateAppointed) {
      this.dateAppointed = dateAppointed;
   }

   public long getPosition() {
      return this.position;
   }

   public void setPosition(long position) {
      this.position = position;
   }

   public Owner id(long id) {
      this.id = id;
      return this;
   }

   public Owner firstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public Owner lastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public Owner email(String email) {
      this.email = email;
      return this;
   }

   public Owner dateAppointed(Date dateAppointed) {
      this.dateAppointed = dateAppointed;
      return this;
   }

   public Owner position(long position) {
      this.position = position;
      return this;
   }

   @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Owner)) {
            return false;
        }
        Owner owner = (Owner) o;
        return this.id == owner.id;
   }

   @Override
   public String toString() {
      return "{" +
         " id='" + getId() + "'" +
         ", firstName='" + getFirstName() + "'" +
         ", lastName='" + getLastName() + "'" +
         ", email='" + getEmail() + "'" +
         ", dateAppointed='" + getDateAppointed() + "'" +
         ", position='" + getPosition() + "'" +
         "}";
   }
}