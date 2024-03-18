package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){

    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb55e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb55e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());

    }

    @Test
    void testUpdateProduct(){
        // Creating a new product class, called product1
        Product product1 = new Product();
        product1.setProductId("eb55e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        // Test if the product is created and get the Id, Name, and Qty
        Product savedProduct = productRepository.findById(product1.getProductId());
        assertEquals(savedProduct.getProductId(), product1.getProductId());
        assertEquals(savedProduct.getProductName(), product1.getProductName());
        assertEquals(savedProduct.getProductQuantity(), product1.getProductQuantity());

        /*
        the Update() function on the ProductServiceImpl.java file finds the Product ID
        that wants to be updated, then replaces the previous product details with a new
        product class object details
        */

        // So, I'll start by creating a product that want to replace the current product
        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);

        // Then I replace product1 with product2
        productRepository.update(product1.getProductId(), product2);

        // Finally, check if it is equal or not, compare product2 and the one edited
        Product product2compare = productRepository.findById(product2.getProductId());
        assertEquals(product2.getProductId(), product2compare.getProductId());
        assertEquals("Sampo Cap Usep", product2compare.getProductName());
        assertEquals(50, product2compare.getProductQuantity());

    }

    @Test
    void testDeleteProduct(){
        // Creating a new product class, called product1
        Product product1 = new Product();
        product1.setProductId("eb55e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        // Test if the product is created and get the Id, Name, and Qty
        Product savedProduct = productRepository.findById(product1.getProductId());
        assertEquals(savedProduct.getProductId(), product1.getProductId());
        assertEquals(savedProduct.getProductName(), product1.getProductName());
        assertEquals(savedProduct.getProductQuantity(), product1.getProductQuantity());

        /*
        the deleteById() function requires an item ID, then it will delete it
        on the repository according to the ID. Then it will return a boolean, if it
        succeeded it returns 1 and if it fails it returns 0
         */

        // Delete the product
        Boolean status = productRepository.deleteById(product1.getProductId());
        assertEquals(Boolean.TRUE, status);

        // Check if there is still the product on the repository
        assertEquals(null, productRepository.findById(product1.getProductId()));

    }
}
