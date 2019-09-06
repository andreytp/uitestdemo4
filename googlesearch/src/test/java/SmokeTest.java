import com.codeborne.selenide.CollectionCondition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;

public class SmokeTest {

    @Test
    public void smokeAccertCountSearchResultsTest()
    {
         open("https://google.com/ncr");
         $("[name=\"q\"]").setValue("selenide").pressEnter();
         $$("#search div.ellip").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(6));

    }
    @Test
    public void smokeImFeelingLuckyButtonTest()
    {
         open("https://google.com/ncr");
         $("[name=\"q\"]").setValue("selenide").pressEscape();
         $$("[name=\"btnI\"]").get(1).click();
         $(By.tagName("body")).shouldBe(not(empty));


    }

    @Test
    public void smokeGoogleSearchButtonTest() {
        open("https://google.com/ncr");
        $("[name=\"q\"]").setValue("selenide").pressEscape();
        $$("[name=\"btnK\"]").get(1).click();
        $$("#search div.ellip").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(6));


    }

}
