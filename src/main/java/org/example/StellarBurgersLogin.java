package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StellarBurgersLogin {
    private WebDriver driver;
    private WebDriver yandexDriver;

    // поле "Email"
    private By emailField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='name']");

    // поле "Пароль"
    private By passwordField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");

    // кнопка "Войти"
    private By enterButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    // кнопка "Зарегестрироваться"
    private By registerButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");

    // кнопка "Восстановить пароль"
    private By recoverPasswordButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");

    // заголовок блока "Вход"
    private By enterHeading = By.xpath(".//h2[text()='Вход']");

    public StellarBurgersLogin(WebDriver driver) {
        this.driver = driver;
    }

    // метод заполняет поле "Email"
    public void enterEmailField(String emailFieldValue) {
        driver.findElement(emailField).sendKeys(emailFieldValue);
    }

    // метод заполняет поле "Пароль"
    public void enterPasswordField(String passwordFieldValue) {
        driver.findElement(passwordField).sendKeys(passwordFieldValue);
    }

    // метод кликает на кнопку "Войти"
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Enter as a user") // метод входит конкретным пользователем
    public void enterUser(String emailValue, String passwordValue) {
        enterEmailField(emailValue);
        enterPasswordField(passwordValue);
        clickEnterButton();
    }

    @Step("Click on the register button") // кликнуть на кнопку "Зарегистрироваться"
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Click on the recover password button") // кликнуть на кнопку "Восстановить пароль"
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }

    @Step("Get the text from the header of the enter block") // метод получает текст из заголовка блока "Вход"
    public String getEnterHeadingText() {
        return  driver.findElement(enterHeading).getText();
    }
}
