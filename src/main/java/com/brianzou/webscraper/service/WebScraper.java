package com.brianzou.webscraper.service;

import com.brianzou.webscraper.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;


@Service
public class WebScraper {

    private HashSet<String> links;
    @Autowired
    private ProductService productService;

    public WebScraper() {
        links = new HashSet<String>();
    }

    public void getPageLinks(String URL) {
        try {
            if (links.add(URL)) {
                System.out.println(URL);
            }

            Document document = Jsoup.connect(URL).get();

            Product product = extractProduct(document);

            if (product != null) {
                System.out.println(product.toString() + " (" + URL + ")");

                productService.save(product);
            }


            Elements linksOnPage = document.select("a[href]");

            for (Element page : linksOnPage) {
                String theUrl = page.attr("abs:href").trim();
                if (theUrl.contains("kbdfans.com") && !theUrl.endsWith("MainContent") && !theUrl.contains("login")) {
                    if (!links.contains(theUrl)) {
                        getPageLinks(theUrl);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("For '" + URL + "': " + e.getMessage());
        }

    }

    public Product extractProduct(Document document) throws IOException {
        Elements elements = document.getElementsByClass("product-single__header");
        if (!(elements == null || elements.size() == 0)) {
            for (Element element : elements) {
                try {
                    String sku = null, name = null;
                    Double price = null;
                    for (Element elm : element.children()) {
                        if (!StringUtil.isBlank(elm.toString())) {
                            if ("product-single__sku".equals(elm.attr("class"))) {
                                sku = elm.childNode(0).toString().trim();
                            } else if ("h1 product-single__title".equals(elm.attr("class"))) {
                                name = elm.childNode(0).toString().trim();
                            } else if ("product-single__prices".equals(elm.attr("class"))) {
                                price = Double.parseDouble(elm.childNode(3).childNode(0).toString().trim().substring(1));
                            }
                        }
                    }
                    if (sku != null) {
                        Product product = new Product(sku, name, price);
                        return product;
                    }
                } catch (Exception e) {
                    //  System.out.println("skipping " + URL);
                }

            }

        }

        return null;
    }


}