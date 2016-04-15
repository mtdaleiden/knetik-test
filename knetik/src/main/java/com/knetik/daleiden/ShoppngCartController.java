package com.knetik.daleiden;

import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.knetik.daleiden.dto.CartItem;
import com.knetik.daleiden.dto.ShoppingCart;
import com.knetik.daleiden.services.ShoppingCartService;

/**
 * REST controller for shopping cart operations.
 * Base URL for accessing the REST services is <code>http://localhost:8080/cart</code>.
 * 
 * The following are the REST operations that are supported:
 * <table>
 * <tr>
 * <th>Operation</th>
 * <th>Path</th>
 * <th>Query Parameters</th>
 * <th>Request Body</th>
 * <th>Result Body</th>
 * </tr>
 * <tr>
 * <td>Create cart</td>
 * <td>None</td>
 * <td>currency<br>ISO currency code</td>
 * <td>None</td>
 * <td>Created cart as JSON</td>
 * </tr>
 * <tr>
 * <td>Get cart</td>
 * <td>/{unique cart ID}</td>
 * <td>None</td>
 * <td>None</td>
 * <td>Current cart contents as JSON</td>
 * </tr>
 * <tr>
 * <td>Add item to cart</td>
 * <td>/{unique cart ID}</td>
 * <td>None</td>
 * <td>JSON of item to add<br>
 * <code>
 *  {
 *    "item": {
 *      "id": 17
 *    },
 *    "quantity": 234
 *  }
 * </code>
 * </td>
 * <td>Current cart contents as JSON</td>
 * </tr>
 * </table>
 * 
 * Location element in the response header will contain the full REST URL for the cart being operated on.
 */
@RestController
@RequestMapping("/cart")
public class ShoppngCartController {

	@Autowired 
	private ShoppingCartService shoppingCartService;
	
	/**
	 * Retrieves the shopping cart data for the specified cart.
	 * 
	 * @param cartId unique ID of the cart to be retrieved
	 * @return REST response containing cart information or error details
	 */
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	ResponseEntity<?> getCart(@PathVariable Long cartId) {
		ShoppingCart cart = shoppingCartService.validateCart(cartId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cart.getId()).toUri());
		return new ResponseEntity<>(cart, httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * Creates a new shopping cart with the given currency.
	 * 
	 * @param currency the ISO currency code for the cart
	 * @return REST response containing the newly created cart or error details
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> createCart(@RequestParam String currency) {
		Currency isoCurrency;
		try {
			isoCurrency = Currency.getInstance(currency);
		}
		catch (IllegalArgumentException iae) {
			throw new InvalidCurrencyException(currency);
		}
		
		ShoppingCart result = shoppingCartService.createCart(isoCurrency);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri());
		return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * Adds the specified item to the given cart.
	 * 
	 * @param cartId unique ID of the cart to which the item is being added
	 * @param itemToAdd the item to be added
	 * @return REST response containing the updated cart information or error details
	 */
	@RequestMapping(value = "/{cartId}", method = RequestMethod.POST)
	ResponseEntity<?> addItemToCart(@PathVariable Long cartId, @RequestBody CartItem itemToAdd) {
		shoppingCartService.addCartItem(cartId, itemToAdd);
		return getCart(cartId);
	}
}

/**
 * Exception raised when an invalid currency code is specified when creating a cart.
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidCurrencyException extends RuntimeException {
	public InvalidCurrencyException(String currencyCode) {
		super("Invalid currency '" + currencyCode + "' specified - cannot create cart");
	}
}
