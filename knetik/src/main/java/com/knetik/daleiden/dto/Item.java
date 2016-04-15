package com.knetik.daleiden.dto;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Data model of an item that can be added to a shopping cart.
 */
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @JsonIgnore
	private Currency currency;
    
    @JsonIgnore
	private BigDecimal unitPrice;
    
	private String description;
	
	/**
	 * Creates a new instance of an item.
	 * 
	 * @param price price for the item
	 * @param currency currency in which the price is expressed
	 * @param description textual description of the item
	 */
	public Item(BigDecimal price, Currency currency, String description) {
		this.unitPrice = price;
		this.currency = currency;
		this.description = description;
	}
	
	Item() {
		//JPA constructor
	}

	/**
	 * @return the unique ID of the item
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the currency in which the item's price is expressed
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @return the price for the item
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @return the textual description of the item
	 */
	public String getDescription() {
		return description;
	}
}
