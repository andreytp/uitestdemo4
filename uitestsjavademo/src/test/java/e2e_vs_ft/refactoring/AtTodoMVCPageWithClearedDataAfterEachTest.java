package e2e_vs_ft.refactoring;

import com.codeborne.selenide.Configuration;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AtTodoMVCPageWithClearedDataAfterEachTest extends BaseTest {
    @Before
    public void openPage() {

        Configuration.fastSetValue = true;
        Configuration.screenshots =  true;

        open("http://todomvc4tasj.herokuapp.com/#");

        waitForUsePage();

    }

}
