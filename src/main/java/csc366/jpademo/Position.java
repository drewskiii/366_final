package csc366.jpademo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "position")
public class Position {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;

   @NotNull
   @Column
   private String title;

   @NotNull
   @Column
   private double payRate;

   public Position(String title, double payRate) {
      this.title = title;
      this.payRate = payRate;
   }

   public Long getId() {
      return id;
   }

   public String getTitle() {
      return title;
   }

   public double getPayRate() {
      return payRate;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setPayRate(double payRate) {
      this.payRate = payRate;
   }

   @Override
   public String toString() {
      return "Position{" +
       "id=" + id +
       ", title='" + title + '\'' +
       ", payRate=" + payRate +
       '}';
   }
}
