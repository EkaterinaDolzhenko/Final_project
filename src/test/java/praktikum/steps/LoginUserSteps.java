package praktikum.steps;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import praktikum.DriverManager;
import praktikum.TestDataGenerator;
import praktikum.User;
import praktikum.UserApiClient;

public class LoginUserSteps {
    private User testUser;

    @Допустим("пользователь зарегистрирован в системе")
    public void userIsRegistered() {
        testUser = TestDataGenerator.createTestUser();
        UserApiClient.registerUser(testUser);
    }

    @Когда("пользователь заполняет форму входа валидными данными")
    public void fillLoginForm() {
        DriverManager.initDriver();
        DriverManager.getLoginPage().waitForLoginPageLoaded();
        DriverManager.getLoginPage().enterEmail(testUser.getEmail());
        DriverManager.getLoginPage().enterPassword(testUser.getPassword());
    }

    @Когда("пользователь нажимает кнопку Войти на странице логина")
    public void clickEnterButton() {
        DriverManager.initDriver();
        DriverManager.getLoginPage().clickLoginButton();
    }
}