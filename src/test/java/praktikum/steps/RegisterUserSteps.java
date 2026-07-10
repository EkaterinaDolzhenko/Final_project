package praktikum.steps;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.apache.commons.lang3.RandomStringUtils;
import praktikum.Constants;
import praktikum.DriverManager;
import praktikum.TestContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterUserSteps {

    private final TestContext context;

    public RegisterUserSteps(TestContext context) {
        this.context = context;
    }


    @Допустим("пользователь нажимает кнопку Нет аккаунта на странице логина")
    public void clickRegisterButtonOnLoginPage() {
        DriverManager.initDriver();
        DriverManager.getLoginPage().clickNoAccountButton();
    }

    @Когда("пользователь заполняет форму регистрации")
    public void fillingRegisterForm() {
        DriverManager.initDriver();
        String uniqueEmail = "testuser_" + RandomStringUtils.randomAlphabetic(5) + "@test.com";
        context.setCreatedEmail(uniqueEmail);
        DriverManager.getRegisterPage().enterEmail(uniqueEmail);
        DriverManager.getRegisterPage().enterPassword(Constants.PASSWORD);
        DriverManager.getRegisterPage().enterSubmitPassword(Constants.PASSWORD);
    }

    @Когда("пользователь заполняет форму регистрации с тем же email")
    public void fillingRegisterFormWithSameEmail() {
        DriverManager.initDriver();
        String sameEmail = context.getCreatedEmail();
        if (sameEmail == null) {
            throw new IllegalStateException("Email не найден в контексте! Сначала зарегистрируй пользователя.");
        }
        DriverManager.getRegisterPage().enterEmail(sameEmail);
        DriverManager.getRegisterPage().enterPassword(Constants.PASSWORD);
        DriverManager.getRegisterPage().enterSubmitPassword(Constants.PASSWORD);
    }

    @Когда("пользователь нажимает кнопку Зарегистрироваться на странице регистрации")
    public void clickCreateButton() {
        DriverManager.initDriver();
        DriverManager.getRegisterPage().clickCreateButton();
    }

    @Когда("пользователь выходит из системы")
    public void clickLogOutButton() {
        DriverManager.initDriver();
        DriverManager.getMainPage().clickLogOutButton();
    }

    @Тогда("пользователь видит сообщение об ошибке")
    public void resultErrorRegistration() {
        DriverManager.initDriver();
        assertTrue(DriverManager.getMainPage().isErrorEmailMessageDisplayed(),
                "Ошибка не отображается после попытки регистрации с email, существующим в системе");
    }

    @io.cucumber.java.After
    public void tearDown() {
        DriverManager.closeDriver();
    }
}