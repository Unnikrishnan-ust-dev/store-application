package org.uststore.catalouge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uststore.catalouge.model.Product;
import org.uststore.catalouge.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product,long id) {
        if(productRepository.existsById(id)){
            return productRepository.save(product);
        }else{
            return null;
        }
    }

    public boolean deleteProduct(long id) {
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
