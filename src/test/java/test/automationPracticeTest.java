package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class automationPracticeTest {
      @Test
      public void validarDadosDoProduto(){
            System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver.exe");
            WebDriver browser = new ChromeDriver();
            browser.navigate().to("http://automationpractice.com/index.php");
            browser.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a")).click();
            browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[2]/span")).click();
            Assertions.assertEquals("Cotton", browser.findElement(By.xpath("//*[@id=\"center_column\"]/div/section[1]/table/tbody/tr[1]/td[2]")).getText());
            Assertions.assertEquals("Girly", browser.findElement(By.xpath("//*[@id=\"center_column\"]/div/section[1]/table/tbody/tr[2]/td[2]")).getText());
            Assertions.assertEquals("Colorful Dress", browser.findElement(By.xpath("//*[@id=\"center_column\"]/div/section[1]/table/tbody/tr[3]/td[2]")).getText());
            browser.quit();
      }

      @Test
      public void fluxoCompraDeUmProduto() throws InterruptedException {
            System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver.exe");
            WebDriver browser = new ChromeDriver();
            browser.navigate().to("http://automationpractice.com/index.php");
            browser.findElement(By.id("search_query_top")).sendKeys("FADED");
            browser.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
            browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]/span")).click();
            new WebDriverWait(browser, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")));
            browser.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
            browser.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click();
            browser.findElement(By.id("email")).sendKeys("teste@teste.com");
            browser.findElement(By.id("passwd")).sendKeys("123456");
            browser.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
            browser.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
            browser.findElement(By.id("cgv")).click();
            browser.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();
            browser.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
            browser.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
            browser.quit();

      }

}