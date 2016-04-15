package com.knetik.daleiden.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Default currency conversion service for this exercise.
 */
@Service
public class DefaultCurrencyConversionService implements CurrencyConversionService {

	@Override
	public BigDecimal convert(BigDecimal amount, Currency source, Currency target) {
		Assert.notNull(amount);
		Assert.notNull(source);
		Assert.notNull(target);
		
		// Dummy conversion
		return new BigDecimal(amount.doubleValue() * 1.5).setScale(2, RoundingMode.HALF_UP);
	}

}
