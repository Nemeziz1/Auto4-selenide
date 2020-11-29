package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardWithDeliveryTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement request = $(".form");
        request.$("[class='input__inner'] [type='text']").setValue("Москва");
        LocalDate date = LocalDate.now().plusDays(5);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateInForm = date.format(dateTimeFormatter);
        request.$("[class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        request.$("[class='input__box'] [placeholder='Дата встречи']").setValue(dateInForm);
        request.$("[data-test-id=name] input.input__control").setValue("Корякин Андрей");
        request.$("[class='input__box'] [name='phone']").setValue("+79999999999");
        request.$("[data-test-id=agreement]").click();
        request.$("[class='button__content'] [class='button__text']").click();
        $(byText(dateInForm)).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldSubmitRequestWithDropdownLists() {
        SelenideElement request = $(".form");
        request.$("[class='input__inner'] [type='text']").setValue("Сан");
        $(byText("Санкт-Петербург")).click();
        request.$("[class='icon-button__text'] [class='icon icon_size_m icon_name_calendar icon_theme_alfa-on-white']").click();
        request.$("[class='input__box'] [placeholder='Дата встречи']").click();
        $$("td.calendar__day").find(Condition.exactText("6")).click();
        request.$("[data-test-id=name] input.input__control").setValue("Корякин Андрей");
        request.$("[class='input__box'] [name='phone']").setValue("+79999999999");
        request.$("[data-test-id=agreement]").click();
        request.$("[class='button__content'] [class='button__text']").click();
        $(byText("06.12.2020")).waitUntil(Condition.visible, 15000);
    }
}
