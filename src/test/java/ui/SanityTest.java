package ui;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Scanner;

public class SanityTest {

    public static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entre browser name: ");
        String browser = scanner.nextLine();
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                return;
        }
        driver.manage().window().setSize(new Dimension(1600,900));
        System.out.println("Browser selection done");

        driver.get ("https://master.marginedge.com/#/login");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("M@rgin!");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div/div[2]/div/div/div/form/button")).click();
        System.out.println("Login successfully done");

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

        List<WebElement> orderList = driver.findElements(By.xpath("//*[@class=\"ui-grid-canvas\"]/div"));
        Thread.sleep(2000);
        for(WebElement element:orderList){
            if(element.getAttribute("innerHTML").contains("#/order")){
                element.click();
                break;
            }
        }
        System.out.println("Found all orders");
        Thread.sleep(2000);
        driver.navigate().to("https://master.marginedge.com/#/order");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"appendToEl\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/ul/li[4]/a")).click();
        System.out.println("Select dropdown successfully");


    }
}
