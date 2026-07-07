package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

public class CreateListingPage {

    // Поле Название
    protected final By adNameField = By.xpath("//input[@placeholder='Название']");
    // Элемент input для загрузки файлов
    protected final By fileInput = By.xpath("//input[@type='file' and @name='img1']");
    // Кнопка Опубликовать
    protected final By publishButton = By.xpath("//button[text()='Опубликовать']");

    @Step("Заполнение названия (с очисткой)")
    public void enterAdName(String adName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(adNameField));
        driver.findElement(adNameField).clear();
        driver.findElement(adNameField).sendKeys(adName);
    }

    @Step("Заполнение названия (с очисткой)")
    public void enterAdNameClear(String adName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(adNameField));
        driver.findElement(adNameField).clear();
        driver.findElement(adNameField).sendKeys(adName);
    }

    @Step("Загрузка фото")
    public void inputFile(String filePath) {
        try {
            // Используем presenceOfElementLocated для скрытых элементов
            wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
            WebElement element = driver.findElement(fileInput);

            // Делаем элемент видимым через JavaScript
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.display='block';", element);

            // Загружаем файл
            element.sendKeys(filePath);

            // Ждём загрузки
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Ошибка при загрузке фото", e);
        }
    }

    @Step("Нажатие на кнопку Опубликовать")
    public void clickPublishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(publishButton));
        driver.findElement(publishButton).click();
    }

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CreateListingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constants.EXPLICIT_TIMEOUT);
    }
}