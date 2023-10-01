package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DeliveryCardPositiveTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldOpenSours() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Санкт-Петербург");
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5);
        $("[data-test-id='date'] .input__control").setValue(planningDate);
        $("[data-test-id='name'] .input__control").setValue("Владимир Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79210000000");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text(planningDate)).shouldBe(Condition.visible);
    }
}
