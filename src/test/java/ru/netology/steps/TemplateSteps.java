package ru.netology.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.page.PageDashboard;
import ru.netology.page.PageLogin;
import ru.netology.page.PageTransit;
import ru.netology.page.PageVer;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateSteps {
    private static PageLogin pageLogin;
    private static PageVer pageVer;
    private static PageDashboard pageDashboard;
    private static PageTransit pageTransit;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void authWithNameAndPassword(String login, String password) {
        pageLogin = open("http://localhost:9999", PageLogin.class);
        pageVer = pageLogin.validLogin(login, password);

    }

    @И("пользователь ввел проверочный код {string}")
    public void enterVerCode(String verCode) {
        pageDashboard = pageVer.validVer(verCode);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void validTransfer(String countOfMoney, String fromCardNumber, int cardIndex) {
        pageTransit = pageDashboard.selectTransitCard(cardIndex);
        pageDashboard = pageTransit.moneyTransit(countOfMoney, fromCardNumber);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {string} рублей")
    public void finishBalance(int cardIndex, String countOfFinishMoney) {
        var actualBalance = String.valueOf(pageDashboard.getCardBalance(cardIndex));
        var expectedBalance = countOfFinishMoney.replaceAll(" ", "");
        assertEquals(expectedBalance, actualBalance);
    }

}
