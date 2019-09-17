package gmailtests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GmailPage {

    private static final SelenideElement buttonNext = $("#identifierNext");
    private static final SelenideElement accountField = $("[type = 'email']");
    private static final SelenideElement passwordField = $("[type='password']");
    private static final SelenideElement buttonCompose = $(byText("Compose"));
    private static final SelenideElement emailToField = $("[name='to']");
    private static final SelenideElement emailSubjectField = $(byName("subjectbox"));
    private static final SelenideElement emailButtonSend = $("div.dC");
    private static final SelenideElement buttonRefresh = $("[title='Refresh']");
    private static final SelenideElement buttonSentEmails = $("[aria-label='Sent']");

    @Step
    public static void openAuthorizeGmail(String account, String password) {
        accountField.setValue(account);
        buttonNext.click();
        passwordField.setValue(password).pressEnter();
    }

    @Step
    public static void composeSendLetter(String emailAddress, String emailSubject) {
        buttonCompose.click();
        emailToField.setValue(emailAddress);
        emailSubjectField.setValue(emailSubject);
        emailButtonSend.click();
    }

    @Step
    public static void refreshEmailList() {
        buttonRefresh.click();
    }

    @Step
    public static void assertFindEmailElement(String emailElement) {
        $$(byText(emailElement)).shouldBe(sizeGreaterThanOrEqual(1));
    }

    @Step
    public static void clickToSentEmails() {
        buttonSentEmails.click();
    }
}
