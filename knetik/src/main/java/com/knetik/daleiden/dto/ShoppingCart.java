package com.knetik.daleiden.dto;

import java.util.Currency;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Shopping cart data model.
 */
@Entity
public class ShoppingCart {
	private long id;
    
	private List<CartItem> items;
    
	private Currency currency;
	
	/**
	 * Creates a new instance of a cart.
	 * 
	 * @param currency the currency for the cart
	 * @param items initial list of items for the cart
	 */
	public ShoppingCart(Currency currency, List<CartItem> items) {
		this.currency = currency;
		this.items = items;
	}
	
	ShoppingCart() {
		// JPA constructor
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
    
    public void setId(long id) {
    	this.id = id;
    }

	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	public List<CartItem> getItems() {
		return items;
	}
    
    public void setItems(List<CartItem> items) {
    	this.items = items;
    }
}
