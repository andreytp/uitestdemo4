package review1;

import com.codeborne.selenide.*;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TodoMVCTest {


    @Before
    public void openPage() {

        Configuration.fastSetValue = true;
        Configuration.screenshots =  true;

        open("http://todomvc4tasj.herokuapp.com/#");

        waitForUsePage();

    }

    @After
    public void tearDown()  {
        screenshot();
    }

    @After
    public void clearData()  {

        executeJavaScript("localStorage.clear()");
        refresh();
    }

    @Test
    public void testTodoMVC() {

        add("1", "2", "3", "4");
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

        //edit task
        editByText("3", "33");
        assertAre("1", "2", "33", "4");

        editByText("33", "3");
        assertAre("1", "2", "3", "4");

        //delete
        delete("2");
        assertAre("1", "3", "4");
        assertKeysLeft("3");

        //mark completed & delete
        toggle("4");
        setFilter("completed");
        assertAre("4");
        assertKeysLeft("2");


        setFilter("active");
        assertAre("1", "3");
        assertKeysLeft("2");

        clearCompleted();
        setFilter("all");
        assertAre("1", "3");
        assertKeysLeft("2");

        //mark completed & delete
        toggleAll();
        clearCompleted();
        tasks.shouldBe(empty);

    }

    @Test
    public void testAddTask() {

        add("1", "2", "3", "4");
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

    }

    ElementsCollection tasks = $$("#todo-list li").filterBy(Condition.visible);

    private void waitForUsePage() {
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

    @Step
    private void toggleAll() {
        $("#toggle-all").click();
    }

    private int getFiltersIndex(@org.jetbrains.annotations.NotNull String filter) {

        if (filter.equals("all"))
            return 0;

        if (filter.equals("active"))
            return 1;

        if (filter.equals("completed"))
            return 2;

        return 0;

    }

    @Step
    private void setFilter(String filter) {

        $$("#filters li").get(getFiltersIndex(filter)).click();

    }

    @Step
    private void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    private void assertAre(String... tasksText) {
        tasks.shouldHave(exactTexts(tasksText));
    }

    @Step
    private void assertKeysLeft(String keyleft){
        $("#todo-count").shouldHave(exactText(keyleft + " items left"));
    }

    @Step
    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    private void edit(int taskPosition, String newTaskText) {
        tasks.get(taskPosition - 1).doubleClick();
        tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

    @Step
    private void editByText(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

    @Step
    private void add(String... taskText) {

        for (String task : taskText) {
            $("#new-todo").setValue(task).pressEnter();
        }

    }

    @Step
    private void clearCompleted() {
        $("#clear-completed").click();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot()  {
//        File screenshot = Screenshots.getLastScreenshot();
//        return screenshot == null ? null : Files.toByteArray(screenshot);
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
