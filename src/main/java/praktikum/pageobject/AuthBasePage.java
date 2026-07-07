package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

public abstract class AuthBasePage {
    // Общие поля для страниц авторизации
    // Поле Email
    protected final By emailField = By.xpath("//input[@type='text' and @placeholder='Введите Email']");
    // Поле Пароль
    protected final By passwordField = By.xpath("//input[@type='password' and @placeholder='Пароль']");

    @Step("Ввод email")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    //Добавили поле driver и wait
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    //Добавили конструктор класса page object
    public AuthBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constants.EXPLICIT_TIMEOUT);
    }
}