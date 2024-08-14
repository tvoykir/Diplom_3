package account;


import browser.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.Account;
import pom.Login;
import pom.MainPage;
import user.User;
import user.UserApi;

import static org.junit.Assert.assertTrue;

public class LogoTest {
    private WebDriver driver;
    private static User user;
    private UserApi userApi;
    private String accessToken;

    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
        userApi = new UserApi();
        user = User.getGeneratedUser();
        ValidatableResponse createResponse = userApi.createUser(user);
        accessToken = createResponse.extract().path("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void testConstructorLogo(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.positiveLogin(user);
        mainPage.clickPersonAccount();
        Account account = new Account(driver);
        account.clickConstructorButton();
        assertTrue("Неуспешный переход на Конструктор", mainPage.checkPlaceOrder());
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void testStellarBurgersLogo(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.positiveLogin(user);
        mainPage.clickPersonAccount();
        Account account = new Account(driver);
        account.clickStellarBurgerLogo();
        assertTrue("Неуспешный переход на логотип Stellar Burgers", mainPage.checkPlaceOrder());
    }


    @After
    public void teardown() {
        userApi.deleteUser(accessToken);
        driver.quit();
    }
}