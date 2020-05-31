package csc366.jpademo;


import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;
import java.util.Date;

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


@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)  // name = "customer_id" from customer's "mappedBy"
    private Customer customer;

    public Transaction() {}

    public Transaction(double totalPrice, Date date) {
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(float price) {
        this.totalPrice = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer c) {
        this.customer = c;
    }

    @Override
    public String toString()
    {
	StringJoiner sj = new StringJoiner("," , Transaction.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(Double.toString(totalPrice));
	    return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Transaction)) return false;
	return id != null && id.equals(((Transaction) o).getId());
    }

    @Override
    public int hashCode() {
	return 366;
    }

}