package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

public class MainPage {

    //Кнопка Вход и регистрация
    protected final By loginToAccountButton = By.xpath("//button[text()='Вход и регистрация']");
    // Имя профиля
    protected final By profileName = By.xpath(".//h3[contains(text(), 'User.')]");
    //Кнопка Выйти
    protected final By logOutButton = By.xpath("//button[text()='Выйти']");
    //Кнопка Разместить объявление
    protected final By createAdButton = By.xpath("//button[text()='Разместить объявление']");
    //Кнопка Входа в личный кабинет
    protected final By profileButton = By.xpath("//button[@class='circleSmall']");
    //Ошибка для регистрации с тем же email
    protected final By errorEmailMessage = By.xpath("//span[text()='Ошибка']");
    // Кнопка "Следующая страница" (стрелка)
    protected final By nextPageButton = By.xpath("//button[@class='arrowButton arrowButton--right undefined']");
    // Все объявления на странице (заголовки)
    protected final By adTitles = By.xpath(".//div[@class='description']//h2[@class='h2']");

    @Step("Клик по кнопке Вход и регистрация")
    public void clickLoginToAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginToAccountButton));
        driver.findElement(loginToAccountButton).click();
    }

    @Step("Клик по кнопке Входа в личный кабинет")
    public void clickProfileButton() {
        try {
            // Ждём, пока кнопка станет кликабельной
            wait.until(ExpectedConditions.elementToBeClickable(profileButton));
            driver.findElement(profileButton).click();
        } catch (StaleElementReferenceException e) {
            // Если элемент stale — ищем заново
            System.out.println("Stale element в clickProfileButton, перепоиск...");
            WebElement button = driver.findElement(profileButton);
            button.click();
        }
    }

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(Constants.BASE_URL);
    }

    @Step("Проверка видимости имени профиля")
    public boolean isProfileNameDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileName));
        return driver.findElement(profileName).isDisplayed();
    }

    @Step("Нажатие на кнопку Выйти")
    public void clickLogOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
        driver.findElement(logOutButton).click();
    }

    @Step("Нажатие на кнопку Разместить объявление")
    public void clickCreateAdButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createAdButton));
        driver.findElement(createAdButton).click();
    }

    @Step("Проверка видимости сообщения об ошибке")
    public boolean isErrorEmailMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorEmailMessage));
        return driver.findElement(errorEmailMessage).isDisplayed();
    }

    @Step("Поиск и клик по объявлению '{adName}' на всех страницах")
    public void findAndClickAd(String adName) {
        int maxPages = 20;
        int currentPage = 0;

        while (currentPage < maxPages) {
            // Проверяем, есть ли объявление на текущей странице
            if (isAdDisplayed(adName)) {
                clickAdByName(adName);
                return;
            }

            // Если объявления нет — переходим на следующую страницу
            if (!goToNextPage()) {
                throw new RuntimeException("Объявление с названием '" + adName + "' не найдено");
            }

            currentPage++;
        }

        throw new RuntimeException("Достигнут лимит страниц. Объявление '" + adName + "' не найдено");
    }

    @Step("Переход на следующую страницу")
    public boolean goToNextPage() {
        try {
            WebElement nextButton = driver.findElement(nextPageButton);
            if (nextButton.isEnabled() && nextButton.isDisplayed()) {
                nextButton.click();
                // Ждём загрузки новых объявлений
                wait.until(ExpectedConditions.presenceOfElementLocated(adTitles));
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, есть ли объявление '{adName}' на странице")
    public boolean isAdDisplayed(String adName) {
        try {
            By adLocator = By.xpath(String.format(".//h2[text()='%s']", adName));
            return driver.findElements(adLocator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Кликнуть по объявлению '{adName}'")
    public void clickAdByName(String adName) {
        By adLocator = By.xpath(String.format(".//h2[text()='%s']/ancestor::div[contains(@class, 'card')]", adName));
        wait.until(ExpectedConditions.elementToBeClickable(adLocator));
        driver.findElement(adLocator).click();
    }

    @Step("Поиск объявления '{adName}' на всех страницах (без клика)")
    public boolean findAdOnAllPages(String adName) {
        int maxPages = 10;
        int currentPage = 0;

        while (currentPage < maxPages) {
            if (isAdDisplayed(adName)) {
                return true;
            }
            if (!goToNextPage()) {
                return false;
            }
            currentPage++;
        }
        return false;
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForMainPageLoaded() {
        // Ждём, пока появится кнопка "Разместить объявление"
        wait.until(ExpectedConditions.visibilityOfElementLocated(createAdButton));
        // Или ждём, пока исчезнет кнопка "Вход и регистрация"
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(loginToAccountButton));
    }

    //Добавили поле driver и wait
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Добавили конструктор класса page object
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constants.EXPLICIT_TIMEOUT);
    }
}
