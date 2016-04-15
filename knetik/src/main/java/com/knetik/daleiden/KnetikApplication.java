package com.knetik.daleiden;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.knetik.daleiden.dto.CartItemRepository;
import com.knetik.daleiden.dto.Item;
import com.knetik.daleiden.dto.ItemRepository;
import com.knetik.daleiden.dto.ShoppingCartRepository;

/**
 * Test application to launch the Spring web container and load the REST services.
 */
@SpringBootApplication
public class KnetikApplication {
	
	private static final Logger log = LoggerFactory.getLogger(KnetikApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KnetikApplication.class, args);
	}

	/**
	 * Sets up the demonstration database data. Clears out all existing data and creates 5 test items that can
	 * be added to carts. To find out what items are set up, connect to MySQL and run the following commands:
	 * <code>
	 * USE knetik_test;
	 * 
	 * SELECT * FROM item;
	 * <code>
	 * 
	 * @param cartRepo JPA repository instance for shopping cart metadata
	 * @param cartItemRepo JPA repository instance for shopping cart item data
	 * @param itemRepo JPA repository instance for available items data
	 * 
	 * @return the executor for setting up the demo data
	 */
	@Bean
	public CommandLineRunner demo(ShoppingCartRepository cartRepo,
								  CartItemRepository cartItemRepo,
								  ItemRepository itemRepo) {
		return (args) -> {
			cartItemRepo.deleteAll();
			cartRepo.deleteAll();
			itemRepo.deleteAll();
			
			itemRepo.save(new Item(new BigDecimal(100.45).setScale(2, RoundingMode.HALF_UP), Currency.getInstance("USD"), "Test Item 1"));
			itemRepo.save(new Item(new BigDecimal(234.23).setScale(2, RoundingMode.HALF_UP), Currency.getInstance("USD"), "Test Item 2"));
			itemRepo.save(new Item(new BigDecimal(399.77).setScale(2, RoundingMode.HALF_UP), Currency.getInstance("USD"), "Test Item 3"));
			itemRepo.save(new Item(new BigDecimal(400.0).setScale(2, RoundingMode.HALF_UP), Currency.getInstance("USD"), "Test Item 4"));
			itemRepo.save(new Item(new BigDecimal(567.89).setScale(2, RoundingMode.HALF_UP), Currency.getInstance("USD"), "Test Item 5"));
            log.info("Cleared database and set up sample items");
		};
	}
}
