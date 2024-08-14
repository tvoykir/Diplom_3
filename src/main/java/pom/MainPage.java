package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By mainPageLoginXPath = By.xpath(".//*[text()='Войти в аккаунт']");
    private final By personAccountXPath = By.xpath(".//*[text()='Личный Кабинет']");
    private final By confirmOrderXPath = By.xpath(".//*[text()='Оформить заказ']");
    private final By mainPageBunXPath = By.xpath(".//*[@class='text text_type_main-default' and text()='Булки']");
    private final By mainPageSaucePath = By.xpath(".//*[@class='text text_type_main-default' and text()='Соусы']");
    private final By mainPageFillingXPath = By.xpath(".//*[@class='text text_type_main-default' and text()='Начинки']");
    private final By mainPageIngredientXPath = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[@class='text text_type_main-default']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public MainPage clickMainPageLogin() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(mainPageLoginXPath).click();
        return this;
    }

    public boolean checkPlaceOrder() {
        WebElement confirmOrderButton = wait.until(ExpectedConditions.presenceOfElementLocated(confirmOrderXPath));
        return confirmOrderButton.isEnabled();
    }

    public void clickPersonAccount() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(personAccountXPath).click();
    }

    public String selectAndReturnIngredient(String ingredient) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        switch (ingredient) {
            case "Булки":
                WebElement bunElement = wait.until(ExpectedConditions.elementToBeClickable(mainPageBunXPath));
                if (!bunElement.isEnabled()) {
                    bunElement.click();
                }
                break;
            case "Соусы":
                WebElement sauceElement = wait.until(ExpectedConditions.elementToBeClickable(mainPageSaucePath));
                sauceElement.click();
                break;
            case "Начинки":
                WebElement fillingElement = wait.until(ExpectedConditions.elementToBeClickable(mainPageFillingXPath));
                fillingElement.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid ingredient: " + ingredient);
        }

        WebElement selectedIngredientElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPageIngredientXPath));
        return selectedIngredientElement.getText();
    }

}
