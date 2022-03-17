package com.example.integration.service;

import com.example.integration.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> fetchProductsByCategory(final String category) {
        System.out.println("fetching products of category "+category);

        List<Product> products = new ArrayList<Product>();


        products.add(new Product("Television","Electronics"));
        products.add(new Product("Washing Machine","Household"));
        return products;
    }
}
