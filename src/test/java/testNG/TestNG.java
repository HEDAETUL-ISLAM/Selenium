package testNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TestNG {
    public static WebDriver driver;

    @BeforeClass
    public void getURL(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.get ("https://master.marginedge.com/#/login");
    }
    @Test(priority = 1)
    public void printTitle(){
        System.out.println(driver.getTitle());
    }
    @Test(priority = 2)
    public void printUrl(){
        System.out.println(driver.getCurrentUrl());
    }
    @Test(priority = 3)
    public void Login(){
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("M@rgin!");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div/div[2]/div/div/div/form/button")).click();
        System.out.println("Login successfully done");
    }
    @Test(priority = 4)
    public void placingOrder() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().to("https://master.marginedge.com/#/vendor");
        Thread.sleep(2000);
        List<WebElement> vendorList = driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div"));
        for(WebElement element:vendorList){
            if(element.getAttribute("innerHTML").contains("#/vendor/9/")){
                element.click();
                break;
            }
        }
        System.out.println("Found vendor list");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div[3]/div[1]/button/span[2]")).click();
        System.out.println("Selected a vendor");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/form/div[1]/div[3]/div/div[1]/table/tbody[2]/tr[2]/td[6]/input")).sendKeys("1");
        System.out.println("Put a quantity value");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/form/div[2]/button[5]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"previewOrderModal\"]/div/div/div[3]/button[3]")).click();
    }
    @Test(priority = 5)
    public void orderHandel() throws InterruptedException {
        List<WebElement> orderList = driver.findElements(By.xpath("//*[@class=\"ui-grid-canvas\"]/div"));
        Thread.sleep(2000);
        for(WebElement element:orderList){
            if(element.getAttribute("innerHTML").contains("#/order")){
                element.click();
                break;
            }
        }
        System.out.println("Found all orders");
    }
    @Test(priority = 6)
    public void dropdownHandel() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().to("https://master.marginedge.com/#/order");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"appendToEl\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/ul/li[4]/a")).click();
        System.out.println("Select dropdown successfully");
    }
}
