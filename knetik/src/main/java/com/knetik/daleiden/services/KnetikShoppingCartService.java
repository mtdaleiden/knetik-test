package com.knetik.daleiden.services;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knetik.daleiden.dto.CartItem;
import com.knetik.daleiden.dto.CartItemRepository;
import com.knetik.daleiden.dto.Item;
import com.knetik.daleiden.dto.ItemRepository;
import com.knetik.daleiden.dto.ShoppingCart;
import com.knetik.daleiden.dto.ShoppingCartRepository;

/**
 * Implementation of the shopping cart services for this exercise.
 */
@Service
public class KnetikShoppingCartService implements ShoppingCartService {
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CurrencyConversionService currencyService;

	@Override
	public ShoppingCart validateCart(Long id) {
		return Optional.ofNullable(this.shoppingCartRepository.findOne(id)).orElseThrow(() -> new InvalidCartException(id) );
	}

	@Override
	public ShoppingCart createCart(Currency currency) {
		return shoppingCartRepository.save(new ShoppingCart(currency, new ArrayList<CartItem>()));
	}

	@Override
	public Item validateCartItem(CartItem cartItem) {
		long itemId = cartItem.getItem().getId();
		return Optional.ofNullable(this.itemRepository.findOne(itemId)).orElseThrow(() -> new InvalidItemException(itemId));
	}

	@Override
	public void addCartItem(Long cartId, CartItem itemToAdd) {
		Item item = validateCartItem(itemToAdd);
		ShoppingCart cart = validateCart(cartId);
		itemToAdd.setUnitPrice(currencyService.convert(item.getUnitPrice(), item.getCurrency(), cart.getCurrency()));
		itemToAdd.setItem(item);
		itemToAdd.setCart(cart);
		cartItemRepository.save(itemToAdd);
	}

}
