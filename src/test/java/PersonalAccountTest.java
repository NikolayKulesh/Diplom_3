import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.StellarBurgersHeadPage;
import org.example.StellarBurgersLogin;
import org.example.StellarBurgersPersonalAccount;
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

public class PersonalAccountTest {
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

        userClient = new UserClient();
        Response responseCreate = userClient.create(email, password, name);
        token = new StringBuilder(responseCreate.then().extract().path("accessToken").toString());
        token.delete(0,7);
    }

    @Test // тест на переход в личный кабинет по клику на кнопку «Личный кабинет»
    @DisplayName("Check the transition to your personal account")
    @Description("Check the transition to your personal account by clicking on the Personal Account button")
    public void goToPersonalAccountTest() {
        String expectedText = "В этом разделе вы можете изменить свои персональные данные";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.enterUser(email, password);

        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersPersonalAccount objStellarBurgersPersonalAccount = new StellarBurgersPersonalAccount(driver);
        String actualText = objStellarBurgersPersonalAccount.getPersonalAccountText();

        MatcherAssert.assertThat("Должны были перейти в личный кабинет", expectedText, is(actualText));
    }

    @Test // тест на переход из личного кабинета по клику на "Конструктор"
    @DisplayName("Check the transition by clicking on the Constructor button")
    @Description("Check the transition to the Constructor section by clicking on the Constructor button")
    public void switchingFromPersonalAccountClickConstructorButtonTest() {
        String expectedText = "Соберите бургер";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.enterUser(email, password);

        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersPersonalAccount objStellarBurgersPersonalAccount = new StellarBurgersPersonalAccount(driver);
        objStellarBurgersPersonalAccount.clickConstructorButton();

        String actualText = objStellarBurgersHeadPage.getAssembleBurgerText();

        MatcherAssert.assertThat("Должны были перейти в конструктор", expectedText, is(actualText));
    }

    @Test // тест на переход из личного кабинета по клику на логотип
    @DisplayName("Check the transition by clicking on the logo")
    @Description("Check the transition to the Head page by clicking on the Logo Stellar Burgers")
    public void switchingFromPersonalAccountClickLogoTest() {
        String expectedText = "Оформить заказ";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.enterUser(email, password);

        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersPersonalAccount objStellarBurgersPersonalAccount = new StellarBurgersPersonalAccount(driver);
        objStellarBurgersPersonalAccount.clickLogoButton();

        String actualText = objStellarBurgersHeadPage.getPlaceOrderText();

        MatcherAssert.assertThat("Должны были перейти на главную страницу", expectedText, is(actualText));
    }

    @Test // тест на выход из личного кабинета по клику на "Выход"
    @DisplayName("Check the exit from your personal account")
    @Description("check the exit from your personal account by clicking on Exit")
    public void clickExitButtonTest() {
        String expectedText = "Вход";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);
        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersLogin objStellarBurgersLogin = new StellarBurgersLogin(driver);
        objStellarBurgersLogin.enterUser(email, password);

        objStellarBurgersHeadPage.clickPersonalAccountButton();

        StellarBurgersPersonalAccount objStellarBurgersPersonalAccount = new StellarBurgersPersonalAccount(driver);
        objStellarBurgersPersonalAccount.clickExitButton();

        String actualText = objStellarBurgersLogin.getEnterHeadingText();

        MatcherAssert.assertThat("Должны были перейти в форму входа", expectedText, is(actualText));
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
