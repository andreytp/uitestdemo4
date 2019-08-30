package oop.refactoring;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    @After
    public void tearDown() {
        screenshot();
    }

    @After
    public void clearData() {

        executeJavaScript("localStorage.clear()");
        refresh();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }


    void waitForUsePage() {
        new WebDriverWait(getWebDriver(), 3000).until(
                new ExpectedCondition<Boolean>() {
                    @NullableDecl
                    public Boolean apply(@NullableDecl WebDriver webDriver) {
                        return (Boolean) ((JavascriptExecutor) webDriver).executeScript(
                                "return " +
                                        "typeof($) == 'function' &&" +
                                        "$._data($('#new-todo').get(0), 'events').hasOwnProperty('keyup')&& " +
                                        "$._data($('#toggle-all').get(0), 'events').hasOwnProperty('change') && " +
                                        "$._data($('#clear-completed').get(0), 'events').hasOwnProperty('click')");
                    }
                }
        );
    }
}
