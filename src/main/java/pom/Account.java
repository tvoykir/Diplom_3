package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Account {
    private final WebDriver driver;
    private final By stellarBurgerLogoXPath = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorButtonXPath = By.xpath(".//*[text()='Конструктор']");
    private final By exitButtonXPath = By.xpath(".//*[text()='Выход']");

    public Account(WebDriver driver) {

        this.driver = driver;
    }

    public void clickStellarBurgerLogo() {

        driver.findElement(stellarBurgerLogoXPath).click();
    }
    public void clickConstructorButton() {

        driver.findElement(constructorButtonXPath).click();
    }
    public void clickExitButton() {

        driver.findElement(exitButtonXPath).click();
    }
    public boolean successfulExitPersonalAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(exitButtonXPath));

        return driver.findElement(exitButtonXPath).isEnabled();
    }
}