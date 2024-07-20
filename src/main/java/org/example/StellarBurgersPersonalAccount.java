package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StellarBurgersPersonalAccount {
    private WebDriver driver;

    // кнопка "Конструктор"
    private By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");

    // логотип
    private By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    // кнопка "Выход"
    private By exitButton = By.xpath(".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']");

    // Текст "В этом разделе вы можете изменить свои персональные данные"
    private By personalAccountText = By.xpath(".//p[@class='Account_text__fZAIn text text_type_main-default']");

    public StellarBurgersPersonalAccount(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on the constructor button") // метод кликает по кнопке "Конструктор"
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Click on the logo") // метод кликает по логотипу
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Click on the exit button") // метод кликает по кнопке "Выйти"
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("Get text from personal account page") // метод вернет текст из личного кабинета
    public String getPersonalAccountText() {
        return  driver.findElement(personalAccountText).getText();
    }

}
