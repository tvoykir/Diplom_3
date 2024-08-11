package registration;


import user.User;
import user.UserApi;
import browser.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import pom.Login;
import pom.MainPage;
import pom.Registration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class UserRegistrationTest {
    private WebDriver driver;
    private static User user;
    private UserApi userApi;
    private String accessToken;


    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
        userApi = new UserApi();
        user = User.getGeneratedUser();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void testUserRegistration() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.clickRegistrationPath();
        Registration registerPage = new Registration(driver);
        assertTrue(registerPage.successfulRegistrationClient(user));
    }

    @After
    public void teardown() {
        ValidatableResponse loginResponse = userApi.loginUser(user);
        accessToken = loginResponse.extract().path("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
        userApi.deleteUser(accessToken);
        driver.quit();
    }
}
