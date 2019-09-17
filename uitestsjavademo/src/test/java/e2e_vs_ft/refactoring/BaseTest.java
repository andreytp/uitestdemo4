package e2e_vs_ft.refactoring;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    @After
    public void tearDown()  {
        screenshot();
    }

    @After
    public void clearData()  {
        executeJavaScript("localStorage.clear()");
        refresh();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot()  {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    private String jsCommand = "localStorage.setItem(\"todos-troopjs\",\"[%s]\")";

    final Boolean ACTIVE = false;
    final Boolean COMPLETED = true;

    public Task aTask(String title, TaskType taskType){
        return new Task(taskType, title);
    }

    public Task aTask(String title, boolean taskType){
        return new Task(taskType, title);
    }

    public Task aTask(String title){
        return new Task(false, title);
    }

    public void given(Task...tasks){
        List<String> strings = new ArrayList<String>();
        for (Task item : tasks) {
            strings.add(item.toString());
        }
        String oneCommonString = String.join(",", strings);
        executeJavaScript(String.format(jsCommand, oneCommonString));
        refresh();

    }

    public void given(TaskType taskType, String... items) {
        given(taskType == TaskType.COMPLETED, items);

    }
    public void given(String... items) {
        given(false, items);

    }

    public void given(Boolean completed, String... items) {
        if (items.length==0)
            return;

        String resultString = "";
        for (String item : items) {
            resultString += String.format("%s{\\\"completed\\\":%s,\\\"title\\\":\\\"%s\\\"}",
                    (resultString.length()==0?"":", "),
                    completed.toString(),
                    item);
        }
        executeJavaScript(String.format(jsCommand, resultString));
        refresh();
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
