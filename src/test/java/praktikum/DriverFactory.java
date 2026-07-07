package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private WebDriver driver;

    public void setupDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            setupFirefox();
        } else {
            setupChrome();
        }
    }

    public void setupChrome() {
        WebDriverManager.chromedriver().setup(); // Автоматически скачивает и настраивает ChromeDriver
        driver = new ChromeDriver(); // Создание драйвера перед каждым тестом
    }

    public void setupFirefox() {
        WebDriverManager.firefoxdriver().setup(); // Автоматически скачивает и настраивает FirefoxDriver
        driver = new FirefoxDriver(); // Создание драйвера перед каждым тестом
    }

    public WebDriver getDriver() {
        return driver;
    }
}
