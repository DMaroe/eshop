package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product createOrUpdate(Product product);
    List<Product> findAll();
    Product findById(String id);
    Product update(String id, Product product);
}
