package registration;


import user.User;
import browser.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import pom.Login;
import pom.MainPage;
import pom.Registration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class UserInvalidRegistrationTest {
    private WebDriver driver;
    private static User user;

    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
        user = User.getGeneratedUser();
        user.setPassword("gg");
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void testUserInvalidRegistration() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.clickRegistrationPath();
        Registration registerPage = new Registration(driver);
        assertTrue(registerPage.invalidRegistrationClient(user));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}