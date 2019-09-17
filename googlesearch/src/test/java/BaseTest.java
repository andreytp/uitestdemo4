import org.junit.Before;

import static pages.GoogleSearchPage.openPage;

public class BaseTest {
    @Before
    public void openGoogleSearchPage() {
        openPage("https://google.com/ncr");
    }
}
