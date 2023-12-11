package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SauceDemoTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    

    @Test
    public void testInvalidLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("invalidUser");
        driver.findElement(By.id("password")).sendKeys("invalidPassword");
        driver.findElement(By.id("login-button")).click();
        WebElement errorElement = driver.findElement(By.cssSelector(".error-message-container"));
        Assertions.assertTrue(errorElement.isDisplayed(), "Error message is not displayed");
        String errorMessage = errorElement.getText();
        System.out.println("Error message from website: " + errorMessage); // This will print the actual error message
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage);
    }
    


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
