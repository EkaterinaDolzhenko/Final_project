package praktikum.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.Допустим;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonSteps {

    private User testUser;
    private final TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Допустим("пользователь открыл главную страницу")
    public void userOpensMainPage() {
        DriverManager.initDriver();
        DriverManager.getMainPage().open();
    }

    @Допустим("пользователь нажимает кнопку Вход и регистрация на главной странице")
    public void userClicksLoginButton() {
        DriverManager.initDriver();
        DriverManager.getMainPage().clickLoginToAccountButton();
        DriverManager.refreshPages();
    }

    @Тогда("пользователь видит Имя профиля на главной странице")
    public void userSeesProfileName() {
        DriverManager.initDriver();
        assertTrue(DriverManager.getMainPage().isProfileNameDisplayed(),
                "Имя профиля не отображается");
    }

    @Когда("пользователь переходит в личный кабинет")
    public void userClicksProfileButton() {
        DriverManager.initDriver();
        DriverManager.refreshPages();
        DriverManager.getMainPage().clickProfileButton();
        DriverManager.refreshPages();
    }

    @Допустим("пользователь создал объявление")
    public void userCreatedAd() {
        DriverManager.initDriver();

        testUser = TestDataGenerator.createTestUser();
        UserApiClient.registerUser(testUser);

        DriverManager.getMainPage().open();
        DriverManager.refreshPages();
        DriverManager.getMainPage().clickLoginToAccountButton();
        DriverManager.refreshPages();

        DriverManager.getLoginPage().waitForLoginPageLoaded();
        DriverManager.getLoginPage().enterEmail(testUser.getEmail());
        DriverManager.getLoginPage().enterPassword(testUser.getPassword());
        DriverManager.getLoginPage().clickLoginButton();
        DriverManager.refreshPages();

        DriverManager.getMainPage().waitForMainPageLoaded();

        DriverManager.getMainPage().clickCreateAdButton();
        DriverManager.refreshPages();
        DriverManager.getCreateListingPage().enterAdName(Constants.ADNAME);
        DriverManager.getCreateListingPage().inputFile(Constants.TEST_IMAGE_PATH);
        DriverManager.getCreateListingPage().clickPublishButton();
        DriverManager.refreshPages();

        context.setAdName(Constants.ADNAME);
    }

    @Допустим("пользователь создал объявление для удаления")
    public void userCreatedAdForDelete() {
        DriverManager.initDriver();

        // Генерируем уникальное название
        String uniqueAdName = "Boombox_" + System.currentTimeMillis();
        context.setAdNameForDelete(uniqueAdName);

        testUser = TestDataGenerator.createTestUser();
        UserApiClient.registerUser(testUser);

        DriverManager.getMainPage().open();
        DriverManager.refreshPages();
        DriverManager.getMainPage().clickLoginToAccountButton();
        DriverManager.refreshPages();

        DriverManager.getLoginPage().waitForLoginPageLoaded();
        DriverManager.getLoginPage().enterEmail(testUser.getEmail());
        DriverManager.getLoginPage().enterPassword(testUser.getPassword());
        DriverManager.getLoginPage().clickLoginButton();
        DriverManager.refreshPages();

        DriverManager.getMainPage().waitForMainPageLoaded();

        DriverManager.getMainPage().clickCreateAdButton();
        DriverManager.refreshPages();
        DriverManager.getCreateListingPage().enterAdName(uniqueAdName); // ← уникальное
        DriverManager.getCreateListingPage().inputFile(Constants.TEST_IMAGE_PATH);
        DriverManager.getCreateListingPage().clickPublishButton();
        DriverManager.refreshPages();

        // Ожидаем, что мы покинули страницу создания объявления
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_TIMEOUT);

        // Ждём, пока URL перестанет содержать "/create-listing"
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("/create-listing")
        ));

        // Дополнительно ждём загрузки главной страницы
        DriverManager.getMainPage().waitForMainPageLoaded();

        DriverManager.getMainPage().open();
        DriverManager.refreshPages();
    }

    @Допустим("пользователь ищет и нажимает на объявление в общем списке")
    public void userSearchesAdInCommonList() {
        DriverManager.initDriver();
        String adName = context.getAdNameForDelete();
        DriverManager.getMainPage().findAndClickAd(adName);
        DriverManager.refreshPages();
    }

    @Когда("пользователь нажимает кнопку удаления объявления")
    public void clickDeleteButton() {
        DriverManager.initDriver();
        DriverManager.getAdDetailsPage().clickDeleteButton();
        DriverManager.refreshPages();
    }

    @Тогда("пользователь не видит объявление")
    public void userDoesNotSeeAd() {
        DriverManager.initDriver();
        String adName = context.getAdNameForDelete();
        // Проверяем, что объявления нет на всех страницах
        assertFalse(DriverManager.getMainPage().findAdOnAllPages(adName),
                "Объявление '" + adName + "' всё ещё отображается");
    }

    @io.cucumber.java.After
    public void tearDown() {
        DriverManager.closeDriver();
    }
}