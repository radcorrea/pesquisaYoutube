package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class pesquisaYoutubeTest {

    private WebDriver driver;
    private ChromeOptions options;
    private WebDriverWait wait;
    private static final String XPATH_CAMPO_PESQUISA = "//input[@id='search']";
    private static final String XPATH_BOTAO_PESQUISA = "//button[@id='search-icon-legacy']";
    private static final String XPATH_VIDEO = "//*[@id='dismissible']//yt-formatted-string[text()='Trio Parada Dura - Vivendo Aqui No Mato (Ao Vivo) ft. Zé Neto & Cristiano']";

    @BeforeTest
    public void setUp() {
        options = new ChromeOptions();
        // options.addArguments("headless"); // descomente essa linha para rodar no modo headless (com o navegador em segundo plano)
        options.addArguments("--window-size=1920,1080");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.youtube.com");
    }

    /*
    Funcionalidade: Pesquisa no YouTube

    Cenário: Pesquisar um termo no YouTube
    Dado que o navegador está aberto no YouTube
    Quando eu insiro "Vivendo aqui no mato" no campo de pesquisa
    E eu clico no botão de pesquisa
    Então os resultados da pesquisa para "Vivendo aqui no mato" são exibidos

    */

     @Test
    public void testPesquisaYoutube() {

        System.out.println("Aguardando o campo de pesquisa ser visivel");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_CAMPO_PESQUISA)));

        System.out.println("Clicando no campo de pesquisa");
        driver.findElement(By.xpath(XPATH_CAMPO_PESQUISA)).click();

        System.out.println("Escrevendo no campo de pesquisa");
        driver.findElement(By.xpath(XPATH_CAMPO_PESQUISA)).sendKeys("Vivendo aqui no mato");

        System.out.println("Clicando no botão pesquisar");
        driver.findElement(By.xpath(XPATH_BOTAO_PESQUISA)).click();

        System.out.println("Aguardando a pesquisa ser feita e verificando se o elemento está em tela");
        WebElement elementoVideo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_VIDEO)));
        
        String tituloVideo = elementoVideo.getText();
        System.out.println("Obtendo o texto do título do vídeo " + tituloVideo);

        System.out.println("Verificando se o título do vídeo é igual ao esperado");
        Assert.assertEquals(tituloVideo, "Trio Parada Dura - Vivendo Aqui No Mato (Ao Vivo) ft. Zé Neto & Cristiano", "O título do vídeo é diferente do esperado.");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
