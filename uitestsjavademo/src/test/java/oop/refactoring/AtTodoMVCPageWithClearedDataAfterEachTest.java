package oop.refactoring;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AtTodoMVCPageWithClearedDataAfterEachTest extends BaseTest{
    @Before
    public void openPage() {

        Configuration.fastSetValue = true;
        Configuration.screenshots =  true;

        open("http://todomvc4tasj.herokuapp.com/#");

        waitForUsePage();


    }



}
