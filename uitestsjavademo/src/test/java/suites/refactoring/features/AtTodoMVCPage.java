package suites.refactoring.features;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;

import static com.codeborne.selenide.Selenide.open;

public class AtTodoMVCPage extends BaseTest {


    @Before
    public void openPage() {

        Configuration.fastSetValue = true;
        Configuration.screenshots =  true;

        open("http://todomvc4tasj.herokuapp.com/#");

        waitForUsePage();

    }

}
