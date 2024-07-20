import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.StellarBurgersHeadPage;
import org.example.StellarBurgersLogin;
import org.example.StellarBurgersRegister;
import org.example.UserClient;
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

public class RegisterTest {
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

        //ChromeOptions options = new ChromeOptions();
        //options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        //yandexDriver = new ChromeDriver(options);
        //yandexDriver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
        //yandexDriver.get(URL);

        RestAssured.baseURI = URL;
    }

    @Test // тест проверяет успешную регистрацию
    @DisplayName("Check the successful registration")
    @Description("Test verifies that the user has successfully registered")
    public void successfulRegistrationTest() {
        userClient = new UserClient();

        String expectedText = "Вход";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickEnterAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.clickRegisterButton();

        StellarBurgersRegister objStellarBurgersRegister = new StellarBurgersRegister(driver);
        objStellarBurgersRegister.registerUser(name, email, password);

        Response responseLogin = userClient.login(email, password);

        token = new StringBuilder(responseLogin.then().extract().path("accessToken").toString());
        token.delete(0,7);

        String actualText = objStellarBurgersLogin.getEnterHeadingText();

        MatcherAssert.assertThat("Должны оказаться на странице Вход", expectedText, is(actualText));
    }

    @Test // тестр проверяет ошибку "Некорректный пароль"
    @DisplayName("Check the Incorrect password error")
    @Description("If enter an incorrect password, the error Incorrect password appears")
    public void invalidPasswordTest() {
        String expectedText = "Некорректный пароль";
        String invalidPassword = "123";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickEnterAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.clickRegisterButton();

        StellarBurgersRegister objStellarBurgersRegister = new StellarBurgersRegister(driver);
        objStellarBurgersRegister.registerUser(name, email, invalidPassword);

        String actualText = objStellarBurgersRegister.getInvalidPasswordText();

        MatcherAssert.assertThat("Ошибка: Некорректный пароль", expectedText, is(actualText));
    }

    @After
    public void tearDown() {
        if(token != null) {
            userClient.delete(String.valueOf(token));
        }
        driver.quit();
        //yandexDriver.quit();
    }
}
