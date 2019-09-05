package pageObject.refactoring;

import com.codeborne.selenide.Configuration;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.open;

public class AtTodoMVCPageWithClearedDataAfterEachTest extends BaseTest {
    @Before
    public void openPage() {

        Configuration.fastSetValue = true;
        Configuration.screenshots =  true;

        open("http://todomvc4tasj.herokuapp.com/#");

        waitForUsePage();

    }

}
