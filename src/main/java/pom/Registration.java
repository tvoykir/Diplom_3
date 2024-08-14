package pom;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Registration {
    private final WebDriver driver;
    private final By userNameXPath = By.xpath(".//*[@class='input__container']/div/label[text()='Имя']/following-sibling::input");
    private final By userEmailXPath = By.xpath(".//*[@class='input__container']/div/label[text()='Email']/following-sibling::input");
    private final By userPasswordXPath = By.xpath(".//*[@class='input__container']/div/label[text()='Пароль']/following-sibling::input");
    private final By registerXpath = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By enterButtonXPath = By.xpath(".//*[text()='Войти']");
    private final By enterTextXPath = By.xpath(".//*[text()='Вход']");
    private final By invalidPasswordTextXPath = By.xpath(".//*[text()='Некорректный пароль']");

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    public Registration setUserName(String name) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(userNameXPath).sendKeys(name);
        return this;
    }

    public Registration setUserEmail(String email) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(userEmailXPath).sendKeys(email);
        return this;
    }

    public Registration setUserPassword(String password) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(userPasswordXPath).sendKeys(password);
        return this;
    }

    public void clickRegisterButton() {
        driver.findElement(registerXpath).click();
    }

    public void clickEnterButton() {
        driver.findElement(enterButtonXPath).click();
    }

    public boolean successfulRegistrationClient(User user) {
        setUserName(user.getName());
        setUserEmail(user.getEmail());
        setUserPassword(user.getPassword());

        WebElement element = driver.findElement(registerXpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registerXpath));

        clickRegisterButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(enterTextXPath));
        return driver.findElement(enterTextXPath).isEnabled();
    }

    public boolean invalidRegistrationClient(User user) {
        setUserName(user.getName());
        setUserEmail(user.getEmail());
        setUserPassword(user.getPassword());

        WebElement element = driver.findElement(registerXpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registerXpath));

        clickRegisterButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidPasswordTextXPath));
        return driver.findElement(invalidPasswordTextXPath).isEnabled();
    }
}