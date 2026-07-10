package praktikum;

import org.openqa.selenium.WebDriver;
import praktikum.pageobject.*;

public class DriverManager {

    private static WebDriver driver;
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static RegisterPage registerPage;
    private static CreateListingPage createListingPage;
    private static ProfilePage profilePage;
    private static AdDetailsPage adDetailsPage;

    public static void initDriver() {
        if (driver == null) {
            try {
                DriverExtension extension = new DriverExtension();
                extension.beforeEach(null);
                driver = extension.getDriver();
                mainPage = new MainPage(driver);
                loginPage = new LoginPage(driver);
                registerPage = new RegisterPage(driver);
                createListingPage = new CreateListingPage(driver);
                profilePage = new ProfilePage(driver);
                System.out.println("Драйвер инициализирован: " + driver);
            } catch (Exception e) {
                throw new RuntimeException("Не удалось инициализировать драйвер", e);
            }
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            mainPage = null;
            loginPage = null;
            registerPage = null;
            createListingPage = null;
            profilePage = null;
            adDetailsPage = null;
            System.out.println("Драйвер закрыт");
        }
    }

    // Метод для пересоздания Page Object'ов после навигации
    public static void refreshPages() {
        if (driver != null) {
            mainPage = new MainPage(driver);
            loginPage = new LoginPage(driver);
            registerPage = new RegisterPage(driver);
            createListingPage = new CreateListingPage(driver);
            profilePage = new ProfilePage(driver);
            adDetailsPage = new AdDetailsPage(driver);
            System.out.println("Страницы пересозданы");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static MainPage getMainPage() {
        return mainPage;
    }

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static RegisterPage getRegisterPage() {
        return registerPage;
    }

    public static CreateListingPage getCreateListingPage() {return createListingPage;}

    public static ProfilePage getProfilePage() {
        return profilePage;
    }

    public static AdDetailsPage getAdDetailsPage() {
        if (adDetailsPage == null) {
            adDetailsPage = new AdDetailsPage(driver);
        }
        return adDetailsPage;
    }
}