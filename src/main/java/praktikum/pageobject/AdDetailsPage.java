package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

public class AdDetailsPage {
    // Кнопка Удалить
    protected final By deleteButton = By.xpath(".//button[text()='Удалить']");

    @Step("Нажатие на кнопку Удалить")
    public void clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        driver.findElement(deleteButton).click();
    }

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AdDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constants.EXPLICIT_TIMEOUT);
    }
}