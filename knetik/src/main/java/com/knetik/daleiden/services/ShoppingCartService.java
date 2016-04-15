package com.knetik.daleiden.services;

import java.util.Currency;

import com.knetik.daleiden.dto.CartItem;
import com.knetik.daleiden.dto.Item;
import com.knetik.daleiden.dto.ShoppingCart;

/**
 * Interface for shopping cart service provider implementations.
 */
public interface ShoppingCartService {
	public ShoppingCart validateCart(Long id);
	
	public ShoppingCart createCart(Currency currency);
	
	public Item validateCartItem(CartItem cartItem);
	
	public void addCartItem(Long cartId, CartItem itemToAdd);
}
