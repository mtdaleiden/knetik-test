package com.knetik.daleiden.dto;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository interface for inventory items.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
