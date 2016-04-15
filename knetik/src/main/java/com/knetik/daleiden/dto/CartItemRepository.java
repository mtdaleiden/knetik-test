package com.knetik.daleiden.dto;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository interface for cart items.
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
