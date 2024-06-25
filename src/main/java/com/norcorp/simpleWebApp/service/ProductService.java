package com.norcorp.simpleWebApp.service;

import com.norcorp.simpleWebApp.model.Product;
import com.norcorp.simpleWebApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    /*List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(101, "Iphone", 50000),
            new Product(102, "Samsung", 30000),
            new Product(103, "Canon Camera", 70000)
    ));*/
    public List<Product> getProducts() {
        //return products;
        return repo.findAll();
    }

    public Product getProductById(int prodId) {
        /*return products.stream()
                .filter(p -> p.getProdId() == prodId)
                .findFirst().orElse(new Product(100, "No item", 0));*/
        return repo.findById(prodId).orElse(new Product());
    }

    public void addProduct(Product product) {
        System.out.println(product);
        //products.add(product);
        repo.save(product);
    }

    public void updateProduct(Product product) {
        /*int index = 0;
        for (int i = 0; i < products.size(); i++)
            if (products.get(i).getProdId() == product.getProdId())
                index = i;

            products.set(index, product);*/
        repo.save(product);
    }

    public void deleteProduct(int prodId) {
        /*int index = 0;
        for (int i = 0; i < products.size(); i++)
            if (products.get(i).getProdId() == prodId)
                index = i;
        products.remove(index);*/
        repo.deleteById(prodId);
    }
}
