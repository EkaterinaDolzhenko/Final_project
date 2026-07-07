package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

public class ProfilePage {

    // Заголовок раздела Мои объявления
    protected final By myAdHeader = By.xpath("//h1[contains(text(), 'Мои объявления')]");
    // Название объявления
    protected final By adNameHeader = By.xpath("//div[@class='description']//h2[@class='h2']");
    // Кнопка редактирования объявления
    protected final By editAdButton = By.xpath("//button[@class='editButton']");
    // Кнопка Сохранить изменения
    protected final By saveButton = By.xpath("//button[text()='Сохранить изменения']");

    @Step("Проверка видимости заголовка раздела Мои объявления")
    public boolean isMyAdHeaderDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(myAdHeader));
        return driver.findElement(myAdHeader).isDisplayed();
    }

    @Step("Проверка, что название объявления '{adName}' отображается на странице")
    public boolean isAdNameDisplayed(String adName) {
        try {
            By adNameLocator = By.xpath(String.format(
                    ".//div[@class='description']//h2[@class='h2' and text()='%s']", adName));
            wait.until(ExpectedConditions.visibilityOfElementLocated(adNameLocator));
            return driver.findElement(adNameLocator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Нажатие на кнопку Сохранить изменения")
    public void clickSaveButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        driver.findElement(saveButton).click();
    }

    @Step("Нажатие на кнопку редактирования объявления")
    public void clickEditAdButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editAdButton));
        driver.findElement(editAdButton).click();
    }


    //Добавили поле driver и wait
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Добавили конструктор класса page object
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constants.EXPLICIT_TIMEOUT);
    }
}
