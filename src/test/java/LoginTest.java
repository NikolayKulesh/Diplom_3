import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.*;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.example.StellarBurgersHeadPage.URL;
import static org.hamcrest.CoreMatchers.is;

public class LoginTest {
    private WebDriver driver;
    //private WebDriver yandexDriver;
    private UserClient userClient;
    StringBuilder token;
    private String name = "Guts";
    private String email = "guts@yandex.ru";
    private String password = "123456";

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
        driver.get(URL);
        RestAssured.baseURI = URL;

        //ChromeOptions options = new ChromeOptions();
        //options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        //yandexDriver = new ChromeDriver(options);
        //yandexDriver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
        //yandexDriver.get(URL);

        userClient = new UserClient();
        Response responseCreate = userClient.create(email, password, name);
        token = new StringBuilder(responseCreate.then().extract().path("accessToken").toString());
        token.delete(0,7);
    }

    @Test // тест на вход по кнопке "Войти в аккаунт" на главной
    @DisplayName("Check the login using the Login to account button on the main page")
    @Description("Check the login using the Login to account button on the main page for a specific user")
    public void loginHeadPageTest() {
        String expectedText = "Оформить заказ";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickEnterAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.enterUser(email, password);

        String actualText = objStellarBurgersHeadPage.getPlaceOrderText();

        MatcherAssert.assertThat("На главной появляется кнопка Оформить заказ", expectedText, is(actualText));
    }

    @Test // тест на вход через кнопку «Личный кабинет»
    @DisplayName("Check the login using the Personal account button")
    @Description("Check the login using the Personal account button for a specific user")
    public void loginPersonalAccountTest() {
        String expectedText = "Оформить заказ";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.enterUser(email, password);

        String actualText = objStellarBurgersHeadPage.getPlaceOrderText();

        MatcherAssert.assertThat("На главной появляется кнопка Оформить заказ", expectedText, is(actualText));
    }

    @Test //тест на вход через кнопку в форме регистрации
    @DisplayName("Check the login using the button in the registration form")
    @Description("Check the login using the Login button in the registration form for a specific user")
    public void loginRegisterFormTest() {
        String expectedText = "Оформить заказ";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickEnterAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.clickRegisterButton();

        StellarBurgersRegister objStellarBurgersRegister = new StellarBurgersRegister(driver);
        objStellarBurgersRegister.clickEnterButton();

        objStellarBurgersLogin.enterUser(email, password);

        String actualText = objStellarBurgersHeadPage.getPlaceOrderText();

        MatcherAssert.assertThat("На главной появляется кнопка Оформить заказ", expectedText, is(actualText));
    }

    @Test // тест на вход через кнопку в форме восстановления пароля
    @DisplayName("Check the login using the button in the password recovery form")
    @Description("Check the login using the button in the password recovery form for a specific user")
    public void loginForgotPasswordFormTest() {
        String expectedText = "Оформить заказ";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickEnterAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.clickRecoverPasswordButton();

        StellarBurgersForgotPassword objStellarBurgersForgotPassword = new StellarBurgersForgotPassword(driver);
        objStellarBurgersForgotPassword.clickEnterButton();

        objStellarBurgersLogin.enterUser(email, password);

        String actualText = objStellarBurgersHeadPage.getPlaceOrderText();

        MatcherAssert.assertThat("На главной появляется кнопка Оформить заказ", expectedText, is(actualText));
    }


    @After
    public void tearDown() {
        if (token != null) {
            userClient.delete(String.valueOf(token));
        }
        driver.quit();
        //yandexDriver.quit();
    }
}
