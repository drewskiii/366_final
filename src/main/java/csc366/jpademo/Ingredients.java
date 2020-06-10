package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;


// Ingredients class written by Zachary Liu
@Entity  // indicates that this class maps to a database table
@Table(name = "ingredients",
	uniqueConstraints = @UniqueConstraint(columnNames={"name"})
	)

public class Ingredients {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="name")
	private String name;

	@Column(name="amount")
	private int amount;

	public Ingredients() { }

	public Ingredients(String name, int amount) {
		this.name = name;
		this.amount = amount;
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

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
	    if (o == this)
	        return true;
	    if (!(o instanceof Ingredients)) {
	        return false;
	    }
	    Ingredients ingredients = (Ingredients) o;
	    return id == ingredients.id;
	}

	@Override
	public String toString() {
	  return "{" +
	     " id='" + getId() + "'" +
	     ", name='" + getName() + "'" +
	     ", price='" + getAmount() + "'" +
	     "}";
	}
}

