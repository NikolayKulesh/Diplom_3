package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StellarBurgersForgotPassword {
    private WebDriver driver;

    // кнопка "Войти"
    private By enterButton = By.xpath(".//a[@class='Auth_link__1fOlj']");

    public StellarBurgersForgotPassword(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on the enter button on forgot password") // метод кликает на кнопку "Войти"
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}
