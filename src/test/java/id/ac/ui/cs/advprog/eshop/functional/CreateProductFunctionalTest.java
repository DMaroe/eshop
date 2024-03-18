package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    // Verifies Create Product feature when performed by the user
    @Test
    void createProduct_verify(ChromeDriver driver) throws Exception{

        // Go to site and designated path
        driver.get(this.baseUrl+"/product/create");

        // Input the arguments to the form
        driver.executeScript("arguments[0].value = 'Panadol 500gr';", driver.findElement(By.id("nameInput")));
        driver.executeScript("arguments[0].value = 3;", driver.findElement(By.id("quantityInput")));

        // Submit the form
        driver.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[type='submit']")));

        // Since the data will be represented in a row format where the name will be
        // on the first row and the quantity on the second row
        WebElement row = driver.findElement(By.xpath("//table[@class='table table-striped table-responsive-md']/tbody/tr[1]"));
        String name = row.findElement(By.xpath("./td[1]")).getText();
        String quantity = row.findElement(By.xpath("./td[2]")).getText();

        // Test if equal using assertions
        Assertions.assertEquals("Panadol 500gr", name);
        Assertions.assertEquals(3, quantity);
    }
}

