package praktikum.steps;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import praktikum.Constants;
import praktikum.DriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvertisementSteps {

    @Допустим("пользователь нажимает кнопку Создать объявление")
    public void clickCreateAdButton() {
        DriverManager.initDriver();
        DriverManager.getMainPage().clickCreateAdButton();
    }

    @Когда("пользователь заполняет форму объявления")
    public void fillingAdForm() {
        DriverManager.initDriver();
        DriverManager.getCreateListingPage().enterAdName(Constants.ADNAME);
    }

    @Когда("пользователь загружает фото")
    public void addingImage() {
        DriverManager.initDriver();
        DriverManager.getCreateListingPage().inputFile(Constants.TEST_IMAGE_PATH);
    }

    @Когда("пользователь нажимает кнопку Опубликовать")
    public void clickPublishButton() {
        DriverManager.initDriver();
        DriverManager.getCreateListingPage().clickPublishButton();
    }

    @Тогда("пользователь видит созданное объявление")
    public void resultCreateAdSuccess() {
        DriverManager.initDriver();
        assertTrue(DriverManager.getProfilePage().isMyAdHeaderDisplayed(), "Мои объявления должны отображаться");
        assertTrue(DriverManager.getProfilePage().isAdNameDisplayed(Constants.ADNAME), "Название объявления должно отображаться");
    }

    @Когда("пользователь нажимает кнопку редактирования объявления")
    public void clickEditButton() {
        DriverManager.initDriver();
        DriverManager.refreshPages();
        DriverManager.getProfilePage().clickEditAdButton();
        DriverManager.refreshPages();
    }

    @Когда("пользователь изменяет название объявления на новое название")
    public void editingAdForm() {
        DriverManager.initDriver();
        DriverManager.getCreateListingPage().enterAdNameClear(Constants.NEW_ADNAME);
    }

    @Когда("пользователь нажимает кнопку Сохранить")
    public void savingAdForm() {
        DriverManager.initDriver();
        DriverManager.getProfilePage().clickSaveButton();
        DriverManager.refreshPages();
    }

    @Тогда("пользователь видит объявление с новым названием")
    public void resultEditAdSuccess() {
        DriverManager.initDriver();
        assertTrue(DriverManager.getProfilePage().isMyAdHeaderDisplayed(), "Мои объявления должны отображаться");
        assertTrue(DriverManager.getProfilePage().isAdNameDisplayed(Constants.NEW_ADNAME), "Название объявления должно быть Новое название");
    }

    @io.cucumber.java.After
    public void tearDown() {
        DriverManager.closeDriver();
    }
}