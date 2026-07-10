package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends AuthBasePage {

    //Кнопка Создать аккаунт
    protected final By createAccountButton = By.xpath("//button[text()='Создать аккаунт']");
    //Поле Повторите пароль
    protected final By submitPasswordField = By.xpath("//input[@placeholder='Повторите пароль']");

    @Step("Нажатие на кнопку Создать аккаунт")
    public void clickCreateButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createAccountButton));
        driver.findElement(createAccountButton).click();
    }

    @Step("Ввод повторения пароля")
    public void enterSubmitPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitPasswordField));
        driver.findElement(submitPasswordField).sendKeys(password);
    }

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
}