package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.SplittableRandom;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardWithDeliveryTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement request = $(".form");
        request.$("[class='input__inner'] [type='text']").setValue("Москва");
        Calendar dateForRequest = Calendar.getInstance();
        dateForRequest.add(Calendar.DATE, 5);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateInForm = dateFormat.format(dateForRequest.getTime());
        request.$("[class='input__box'] [placeholder='Дата встречи']").doubleClick();
        request.$("[class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        request.$("[class='input__box'] [placeholder='Дата встречи']").setValue(dateInForm);
        request.$$("[class='input__inner'] [type='text']").last().setValue("Корякин Андрей");
        request.$("[class='input__box'] [name='phone']").setValue("+79999999999");
        request.$("[data-test-id=agreement]").click();
        request.$("[class='button__content'] [class='button__text']").click();
        $(byText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldSubmitRequestWithDropdownLists() {
        SelenideElement request = $(".form");
        request.$("[class='input__inner'] [type='text']").setValue("Сан");
        $(byText("Санкт-Петербург")).click();
        request.$("[class='icon-button__text'] [class='icon icon_size_m icon_name_calendar icon_theme_alfa-on-white']").click();
        Calendar dateForRequest = Calendar.getInstance();
        dateForRequest.add(Calendar.DATE, 7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateInForm = dateFormat.format(dateForRequest.getTime());
        request.$("[class='input__box'] [placeholder='Дата встречи']").doubleClick();
        request.$("[class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        request.$("[class='input__box'] [placeholder='Дата встречи']").setValue(dateInForm);
        request.$$("[class='input__inner'] [type='text']").last().setValue("Корякин Андрей");
        request.$("[class='input__box'] [name='phone']").setValue("+79999999999");
        request.$("[data-test-id=agreement]").click();
        request.$("[class='button__content'] [class='button__text']").click();
        $(byText("Успешно!")).waitUntil(Condition.visible, 15000);
    }
}
