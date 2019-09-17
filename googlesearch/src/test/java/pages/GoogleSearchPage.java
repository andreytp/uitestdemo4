package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class GoogleSearchPage {

    public static SelenideElement queryArea = $("[name='q']");
    public static ElementsCollection searchResults = $$("#search div.ellip");
    public static SelenideElement imFeelingLuckyButton = $$("[name='btnI']").get(1);
    public static SelenideElement searchButton = $$("[name='btnK']").get(1);

    @Step
    public static void openPage(String url) {
        open(url);
    }

    @Step
    public static SelenideElement setQueryValue(String queryText) {
        return queryArea.setValue(queryText);
    }

   @Step
    public static void assertSearchResultsQuantity(int resultsQuantity) {
        searchResults.shouldHave(sizeGreaterThanOrEqual(resultsQuantity));
    }

    @Step
    public static void assertUrlNotInitial() {
        Wait().until(ExpectedConditions.not(urlContains("google.com")));
    }

    @Step
    public static void assertSearchResultContainsQueryTextGreaterThanOrEqualOne(String queryText) {
        $$(withText(queryText)).shouldBe(sizeGreaterThanOrEqual(1));
    }
}