package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
    Product product;

    @BeforeEach
    void setUp(){
        this.product = new Product();
        this.product.setProductId("eb55e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test 
    void tesGetProductId() {
        assertEquals("eb55e9f-1c39-460e-8860-71af6af63bd6", this.product.tesGetProductId());
    }

    @Test 
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.testGetProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.testGetProductQuantity());
    }

}