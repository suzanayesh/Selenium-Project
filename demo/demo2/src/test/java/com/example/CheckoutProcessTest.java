package com.example;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutProcessTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Set wait to 5 seconds
    }

    @Test
    public void testCheckoutProcess() {
        try {
            driver.get("https://www.saucedemo.com/");

            // Login steps
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Wait for the product page to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

            // Continue with the checkout process
            driver.findElement(By.className("shopping_cart_link")).click();
            driver.findElement(By.id("checkout")).click();

            // Wait for the page title to be updated to 'Checkout: Your Information'
            wait.until(ExpectedConditions.titleIs("Swag Labs"));

            // Perform the necessary assertions to ensure you are on the correct page
            Assertions.assertTrue(driver.getTitle().contains("Swag Labs"), "Page title does not contain expected text 'Swag Labs'");
        } finally {
            driver.quit();
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
