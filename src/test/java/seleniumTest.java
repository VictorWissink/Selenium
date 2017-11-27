

import com.websystique.springboot.SpringBootCRUDApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = SpringBootCRUDApp.class,
        loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class seleniumTest {
    private final WebDriver driver = new ChromeDriver();

    @Test
    public void firstSeleniumTest() {

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                driver.get("localhost:8080/SpringBootCRUDApp/");
                return d.getTitle().toLowerCase().startsWith("crud");
            }
        });

        driver.close();
    }

    @Test
    public void addUser() {
        String divID = "alert-success";
        String name = "Tom";
        int age = 20;
        int salary = 30000;

        driver.get("localhost:8080/SpringBootCRUDApp/");

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {

                WebElement uNameInput = d.findElement(By.id("uname"));
                WebElement ageInput = d.findElement(By.id("age"));
                WebElement salaryInput = d.findElement(By.id("salary"));

                uNameInput.clear();
                ageInput.clear();
                salaryInput.clear();

                uNameInput.sendKeys(name);
                ageInput.sendKeys(age + "");
                salaryInput.sendKeys(salary + "");

                WebElement addButton = d.findElement(By.id("submit"));
                return !addButton.isEnabled();
            }
        });
        WebElement addButton = driver.findElement(By.id("submit"));
        addButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement element = driver.findElement(By.id(divID));
                return element.isDisplayed();
             }
        });
        driver.close();
    }






}
