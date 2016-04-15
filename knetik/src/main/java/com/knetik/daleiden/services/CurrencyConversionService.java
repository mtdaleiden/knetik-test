package com.knetik.daleiden.services;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Interface for currency conversion service provider implementations.
 */
public interface CurrencyConversionService {
	/**
	 * Converts the given amount from the source currency to the target currency.
	 * 
	 * @param amount amount to convert
	 * @param source source currency
	 * @param target target currency
	 * @return
	 */
	public BigDecimal convert(BigDecimal amount, Currency source, Currency target);
}
