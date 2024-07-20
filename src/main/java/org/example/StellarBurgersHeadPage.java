package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StellarBurgersHeadPage {
    private WebDriver driver;
    private WebDriver yandexDriver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    // текст "Соберите бургер"
    private By assembleBurger = By.xpath(".//h1[@class='text text_type_main-large mb-5 mt-10' and text()='Соберите бургер']");

    // кнопка "Личный кабинет"
    private By personalAccountButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");

    // кнопка "Войти в аккаунт"
    private By enterAccountButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    // кнопка "Булки"
    private By bunsButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Булки']");

    // кнопка "Соусы"
    private By saucesButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Соусы']");

    // кнопка "Начинки"
    private By toppingsButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Начинки']");

    // кнопка "Оформить заказ"
    private By placeOrderButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    // текст раздела "Булки"
    private By bunsText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Булки']");

    // текст раздела "Соусы"
    private By saucesText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Соусы']");

    // текст раздела "Соусы"
    private By toppingsText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Начинки']");

    public StellarBurgersHeadPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on the Personal Account button")// метод кликает на кнопку "Личный кабинет"
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Click on the sign in account button")// метод кликает на кнопку "Войти в аккаунт"
    public void clickEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    @Step("Click on the buns button")// метод кликает на кнопку "Булки"
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Click on the sauces button")// метод кликает на кнопку "Соусы"
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    @Step("Click on the toppings button")// метод кликает на кнопку "Начинки"
    public void clickToppingsButton() {
        driver.findElement(toppingsButton).click();
    }

    @Step("Get the text assemble a burger")// метод получает текст "Соберите бургер"
    public String getAssembleBurgerText() {
        return driver.findElement(assembleBurger).getText();
    }

    @Step("Get the text place order")// метод получает текст из кнопки "Оформить заказ"
    public String getPlaceOrderText() {
        return driver.findElement(placeOrderButton).getText();
    }

    @Step("Get text section name Buns") // метод получает текст из заголовка раздела "Булки"
    public String getBunsText() {
        return driver.findElement(bunsText).getText();
    }

    @Step("Get text section name Sauces") // метод получает текст из заголовка раздела "Соусы"
    public String getSaucesText() {
        return driver.findElement(saucesText).getText();
    }

    @Step("Get text section name Toppings") // метод получает текст из заголовка раздела "Начинки"
    public String getToppingsText() {
        return driver.findElement(toppingsText).getText();
    }
}
