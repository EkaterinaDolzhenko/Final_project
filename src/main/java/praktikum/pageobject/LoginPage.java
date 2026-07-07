package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AuthBasePage {

    //Кнопка Нет аккаунта
    protected final By noAccountButton = By.xpath("//button[text()='Нет аккаунта']");
    //Кнопка Войти
    protected final By loginButton = By.xpath("//button[text()='Войти']");


    @Step("Нажатие на кнопку Нет аккаунта")
    public void clickNoAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(noAccountButton));
        driver.findElement(noAccountButton).click();
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Ожидание загрузки страницы логина")
    public void waitForLoginPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }


    public LoginPage(WebDriver driver) {
        super(driver);
    }
}