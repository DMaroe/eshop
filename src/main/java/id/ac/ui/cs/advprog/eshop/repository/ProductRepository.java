package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private static long idCounter = 0;

    public Product create(Product product) {
        product.setProductId(String.valueOf(++idCounter));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        return productData.stream()
                .filter(product -> id.equals(product.getProductId()))
                .findFirst()
                .orElse(null);
    }

    public Product update(String id, Product product) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                product.setProductId(id);
                productData.set(i, product);
                return product;
            }
        }
        return null;
    }
    public boolean deleteById(String id) {
        return productData.removeIf(product -> product.getProductId().equals(id));
    }

}
