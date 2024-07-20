import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.StellarBurgersHeadPage;
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

public class ConstructorButtonsTest {
    private WebDriver driver;
    //private WebDriver yandexDriver;

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
    }

    @Test // проверяем работу кнопки "Булки"
    @DisplayName("Check the operation of the Buns button")
    @Description("Check the operation of the Buns button in the constructor")
    public void bunButtonTest() {
        String expectedText = "Булки";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);

        objStellarBurgersHeadPage.clickSaucesButton();
        objStellarBurgersHeadPage.clickBunsButton();

        String actualText = objStellarBurgersHeadPage.getBunsText();

        MatcherAssert.assertThat("Название раздела Булки", expectedText, is(actualText));
    }

    @Test // проверяем работу кнопки "Соусы"
    @DisplayName("Check the operation of the Sauces button")
    @Description("Check the operation of the Sauces button in the constructor")
    public void bunSaucesTest() {
        String expectedText = "Соусы";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);

        objStellarBurgersHeadPage.clickSaucesButton();

        String actualText = objStellarBurgersHeadPage.getSaucesText();

        MatcherAssert.assertThat("Название раздела Соусы", expectedText, is(actualText));
    }

    @Test // проверяем работу кнопки "Начинки"
    @DisplayName("Check the operation of the Toppings button")
    @Description("Check the operation of the Toppings button in the constructor")
    public void bunToppingsTest() {
        String expectedText = "Начинки";

        StellarBurgersHeadPage objStellarBurgersHeadPage = new StellarBurgersHeadPage(driver);

        objStellarBurgersHeadPage.clickToppingsButton();

        String actualText = objStellarBurgersHeadPage.getToppingsText();

        MatcherAssert.assertThat("Название раздела Начинки", expectedText, is(actualText));
    }

    @After
    public void tearDown() {
        driver.quit();
        //yandexDriver.quit();
    }
}
