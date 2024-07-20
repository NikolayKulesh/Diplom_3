package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StellarBurgersRegister {
    private WebDriver driver;
    private WebDriver yandexDriver;

    // поле "Имя"
    private By nameField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='text']");

    // поле "Email"
    private By emailRegisterField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='text' and @value='']");

    // поле "Пароль"
    private By passwordRegisterField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='password']");

    // кнопка "Зарегистрироваться"
    private By registerButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    // кнопка "Войти"
    private By enterButton = By.xpath(".//a[@class='Auth_link__1fOlj']");

    // ошибка "Некорректный пароль"
    private By invalidPassword = By.xpath(".//p[@class='input__error text_type_main-default']");

    public StellarBurgersRegister(WebDriver driver) {
        this.driver = driver;
    }

    // метод заполняет поле "Имя"
    public void enterName(String nameValue) {
        driver.findElement(nameField).sendKeys(nameValue);
    }

    // метод заполняет поле "Email"
    public void enterEmail(String emailValue) {
        driver.findElement(emailRegisterField).sendKeys(emailValue);
    }

    // метод заполняет поле "Пароль"
    public void enterPassword(String passwordValue) {
        driver.findElement(passwordRegisterField).sendKeys(passwordValue);
    }

    // метод кликает на кнопку "Зарегестрироваться"
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Click on the enter button") // метод кликает на кнопку "Войти"
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Get the error text of an incorrect password") // метод получает текс ошибки некорректного пароля
    public String getInvalidPasswordText() {
        return driver.findElement(invalidPassword).getText();
    }

    @Step("Register a user") // метод регистрирует пользователя
    public void registerUser(String nameValue, String emailValue, String passwordValue) {
        enterName(nameValue);
        enterEmail(emailValue);
        enterPassword(passwordValue);
        clickRegisterButton();
    }
}
