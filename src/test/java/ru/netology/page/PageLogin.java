package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PageLogin {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement button = $("[data-test-id=action-login] .button__content");

    public PageVer validLogin(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        button.click();
        return new PageVer();
    }
}
