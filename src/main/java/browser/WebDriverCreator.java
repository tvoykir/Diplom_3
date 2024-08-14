package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.time.Duration;
import static constants.Constants.*;

public class WebDriverCreator {

    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser");
        WebDriver driver;

        if (browser == null) {
            driver = createChromeDriver();
        } else {
            switch (browser) {
                case "yandex":
                    driver = createYandexDriver();
                    break;
                case "chrome":
                default:
                    driver = createChromeDriver();
                    break;
            }
        }

        // Применение общих настроек
        configureDriver(driver);

        return driver;
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        String driverPath = Paths.get(BROWSER_DRIVERS, YANDEX_BROWSER_DRIVER_FILENAME).toString();
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(YANDEX_BROWSER_PATH);
        return new ChromeDriver(options);
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SECONDS_TIMEOUT));
        driver.navigate().to(HOME_URL);
    }
}
