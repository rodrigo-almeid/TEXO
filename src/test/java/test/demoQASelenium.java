package test;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pojo.Album;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class demoQASelenium {
    //Projeto de Testes E2E (Web)
    @Test
    public void validarjsonplaceholder() {
        System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("https://jsonplaceholder.typicode.com/");
        browser.findElement(By.xpath("/html/body/header/nav/ul/li[1]/a")).click();
        Actions actions = new Actions(browser);
        actions.moveToElement(browser.findElement(By.xpath("/html/body/div/main/ul/li[2]/a")));
        actions.perform();
        browser.findElement(By.xpath("/html/body/div/main/ul/li[2]/a")).click();
        String aux = browser.findElement(By.xpath("//html")).getText();
        List<Album> list = Arrays.asList(new GsonBuilder().create().fromJson(aux, Album[].class));
        Optional<Album> selecionado = list.stream().filter(item -> item.getId().equals("6")).findFirst();
        if (selecionado.isPresent()) {
            Assertions.assertEquals("1", selecionado.get().getAlbumId());
            Assertions.assertEquals("accusamus ea aliquid et amet sequi nemo", selecionado.get().getTitle());
            Assertions.assertEquals("https://via.placeholder.com/600/56a8c2", selecionado.get().getUrl());
            Assertions.assertEquals("https://via.placeholder.com/150/56a8c2", selecionado.get().getThumbnailUrl());
        }
        browser.close();

    }
}