package login;

import browser.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.Login;
import pom.MainPage;
import pom.Registration;
import user.User;
import user.UserApi;

import static org.junit.Assert.assertTrue;

public class EntryButtonToLogInTest {
    private WebDriver driver;
    private UserApi userApi;
    private static User user;
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void testEntryLogInButton(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        assertTrue("Неуспешный вход в приложение через кнопку 'Войти в аккаунт'",
                loginPage.positiveLogin(user));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testEntryThrowPersonalAccount(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonAccount();
        Login loginPage = new Login(driver);
        assertTrue("Неуспешный вход в приложение через кнопку 'Личный кабинет'", loginPage.positiveLogin(user));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testEntryThrowLogInRegistration(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.clickRegistrationPath();
        Registration registerPage = new Registration(driver);
        registerPage.clickEnterButton();
        assertTrue("Неуспешный вход в приложение через форму регистрации", loginPage.positiveLogin(user));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testEntryThrowRecoveryForm(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.clickRecoverPasswordLink();
        loginPage.clickRecoverPasswordButton();
        assertTrue("Неуспешный вход в приложение через восстановление пароля",
                loginPage.positiveLogin(user));
    }

    @After
    public void teardown() {
        userApi.deleteUser(accessToken);
        driver.quit();
    }
}