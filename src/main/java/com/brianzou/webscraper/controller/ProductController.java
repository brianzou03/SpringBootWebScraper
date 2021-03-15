package com.brianzou.webscraper.controller;

import com.brianzou.webscraper.model.Product;
import com.brianzou.webscraper.service.ProductService;
import com.brianzou.webscraper.service.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api")


public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private WebScraper webScraper;



    @RequestMapping(path = "/scrape")
    public void startScraping() {
        webScraper.getPageLinks("https://kbdfans.com/");
    }

    @RequestMapping(path = "/products")
    public List<Product> getProduct() {
        return productService.getAll();
    }


}
