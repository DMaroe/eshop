package id.ac.ui.cs.advprog.eshop;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	void whenGetCreateProductPage_thenReturnsCreateProductView() throws Exception {
		mockMvc.perform(get("/product/create"))
				.andExpect(status().isOk())
				.andExpect(view().name("createProduct"))
				.andExpect(model().attributeExists("product"));
	}

	@Test
	void whenPostCreateProduct_thenProductIsCreated() throws Exception {
		Product product = new Product();
		product.setProductName("New Product");
		product.setProductQuantity(5);

		mockMvc.perform(post("/product/create")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("productName", product.getProductName())
						.param("productQuantity", String.valueOf(product.getProductQuantity())))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("list"));

		verify(productService).createOrUpdate(refEq(product));
	}

	@Test
	void whenListProducts_thenReturnsProductListView() throws Exception {
		Product product = new Product();
		product.setProductId("1");
		product.setProductName("Test Product");
		product.setProductQuantity(10);
		List<Product> productList = Collections.singletonList(product);

		given(productService.findAll()).willReturn(productList);

		mockMvc.perform(get("/product/list"))
				.andExpect(status().isOk())
				.andExpect(view().name("productList"))
				.andExpect(model().attributeExists("products"))
				.andExpect(model().attribute("products", productList));
	}

	@Test
	void whenGetEditProductPage_thenReturnsEditProductView() throws Exception {
		Product product = new Product();
		product.setProductId("1");
		product.setProductName("Test Product");
		product.setProductQuantity(10);

		given(productService.findById("1")).willReturn(product);

		mockMvc.perform(get("/product/edit/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("createProduct"))
				.andExpect(model().attributeExists("product"))
				.andExpect(model().attribute("product", product));
	}



	@Test
	void whenPostUpdateProduct_thenProductIsUpdated() throws Exception {
		Product product = new Product();
		product.setProductId("1");
		product.setProductName("Updated Product");
		product.setProductQuantity(15);

		mockMvc.perform(post("/product/update/1")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("productId", "1")
						.param("productName", "Updated Product")
						.param("productQuantity", "15"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("list"));

		verify(productService).update(eq("1"), refEq(product));
	}


	@Test
	void whenDeleteProduct_thenProductIsDeleted() throws Exception {
		mockMvc.perform(get("/product/delete/1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("list"));

		verify(productService).deleteById("1");
	}
}
