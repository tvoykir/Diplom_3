package ingredient;


import browser.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import pom.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTabParamTest {
    private WebDriver driver;

    @Parameterized.Parameter
    public String ingredient;

    @Parameterized.Parameters()
    public static Object[] ingredientData() {
        return new Object[]{
                "Булки",
                "Соусы",
                "Начинки"};
    }

    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
    }

    @Test
    @DisplayName("Переходы к разделам: Булки, Соусы, Начинки")
    public void testSelectionIngredient() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        assertEquals(ingredient, mainPage.changeIngredient(ingredient));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}