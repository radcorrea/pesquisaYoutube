package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class pesquisaYoutubeTest {

    private WebDriver driver;
    private ChromeOptions options;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        options = new ChromeOptions();
        options.addArguments("headless"); // descomente essa linha para rodar no modo headless (com o navegador em segundo plano)
        options.addArguments("--window-size=1920,1080");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.youtube.com");
    }

    @Test
    public void testPesquisaYoutube() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search']")));
        driver.findElement(By.xpath("//input[@id='search']")).click();
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Vivendo aqui no mato");
        driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
