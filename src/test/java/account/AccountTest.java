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

public class AccountTest {
    private WebDriver driver;
    private static User user;
    private UserApi userApi;
    private String accessToken;

    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
        userApi = new UserApi();
        user = User.getGeneratedUser();

        ValidatableResponse response = userApi.createUser(user);
        accessToken = response.extract().path("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void testPersonalAccount(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.positiveLogin(user);
        mainPage.clickPersonAccount();
        Account account = new Account(driver);
        assertTrue("Неуспешный переход через 'Личный кабинет'", account.successfulExitPersonalAccount());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void testExitFromPersonalAccount(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.positiveLogin(user);
        mainPage.clickPersonAccount();
        Account account = new Account(driver);
        account.clickExitButton();
        assertTrue("Неуспешный выход через 'Личный кабинет'", loginPage.loginPage());
    }

    @After
    public void teardown() {
        userApi.deleteUser(accessToken);
        driver.quit();
    }
}