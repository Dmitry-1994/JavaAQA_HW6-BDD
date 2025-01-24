package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PageTransit {
    private final SelenideElement headText = $(byText("Пополнение карты"));
    private final SelenideElement amountField = $("[data-test-id=amount] input");
    private final SelenideElement fromField = $("[data-test-id=from] input");
    private final SelenideElement execute = $("[data-test-id=action-transfer] .button__content");

    public PageTransit() {
        headText.shouldBe(Condition.visible);
    }

    public PageDashboard moneyTransit(String amount, String from) {
        amountField.setValue(amount);
        fromField.setValue(from);
        execute.click();
        return new PageDashboard();
    }

}
