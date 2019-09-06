package features;

import org.junit.Before;

import static com.codeborne.selenide.Selenide.open;

public class AtTodoMVCPageWithClearedDataAfterEachTest extends BaseTest {

    @Before
    public void openPage() {
        open("http://todomvc4tasj.herokuapp.com/#");

        waitForUsePage();

    }


}
