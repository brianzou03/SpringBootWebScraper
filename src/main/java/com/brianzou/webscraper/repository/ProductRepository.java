package com.brianzou.webscraper.repository;

import com.brianzou.webscraper.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
