package csc366.jpademo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Product {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long id;

   @Column(name="name")
   private String name;

   @Column(name="price")
   private float price;

   public Product() {
   }

   public Product(String name, float price) {
      this.name = name;
      this.price = price;
   }

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public float getPrice() {
      return this.price;
   }

   public void setPrice(float price) {
      this.price = price;
   }

   public Product id(long id) {
      this.id = id;
      return this;
   }

   public Product name(String name) {
      this.name = name;
      return this;
   }

   public Product price(float price) {
      this.price = price;
      return this;
   }

   @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id;
   }

   @Override
   public String toString() {
      return "{" +
         " id='" + getId() + "'" +
         ", name='" + getName() + "'" +
         ", price='" + getPrice() + "'" +
         "}";
   }


}