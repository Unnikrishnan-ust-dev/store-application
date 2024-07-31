package org.uststore.catalouge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uststore.catalouge.model.Product;
import org.uststore.catalouge.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalogue")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products =  productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/productbyid/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Optional<Product> product =  productService.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/productbycategory/{category}")
    public ResponseEntity<List<Product>> getProduct(@PathVariable String category) {
        List<Product> products =  productService.findByCategory(category);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable long id) {
        Product newProduct = productService.updateProduct(product,id);
        if(newProduct != null) {
            return ResponseEntity.ok(newProduct);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        boolean status = productService.deleteProduct(id);
        if(status) {
            return ResponseEntity.ok("Product with id= "+id+" Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No product with id "+id+" is present!!");
    }

}
