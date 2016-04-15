package com.knetik.daleiden.dto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Data model for an item that has been added to a shopping cart.
 */
@Entity
public class CartItem {
	private long id;
    
    @JsonIgnore
	private ShoppingCart cart;
    
    private Item item;
    
	private int quantity;
	
	private BigDecimal unitPrice;
	
	/**
	 * Creates a new instance of a cart item.
	 * 
	 * @param cart the cart to which the item is being added
	 * @param item the item being added
	 * @param quantity the quantity of the item being added
	 * @param unitPrice the unit price for the item in cart currency
	 */
	public CartItem(ShoppingCart cart, Item item, int quantity, BigDecimal unitPrice) {
		this.cart = cart;
		this.item = item;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	CartItem() {
		// JPA constructor
	}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
    
    public void setId(long id) {
    	this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="cartId")
	public ShoppingCart getCart() {
		return cart;
	}
	
    public void setCart(ShoppingCart cart) {
    	this.cart = cart;
    }
    
    @ManyToOne
    @JoinColumn(name="itemId")
	public Item getItem() {
		return item;
	}
    
    public void setItem(Item item) {
    	this.item = item;
    }

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}
