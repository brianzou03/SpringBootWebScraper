package com.brianzou.webscraper.service;

import com.brianzou.webscraper.model.Product;
import com.brianzou.webscraper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();

        Iterable<Product> iterable = productRepository.findAll();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            productList.add((Product) iterator.next());
        }

        return productList;
    }

}
