import org.junit.Test;

import static pages.GoogleSearchPage.*;



public class GoogleSearchTest extends BaseTest {

    private final String QUERY_TEXT = "Selenide";
    private final int MIN_RESULTS_QUANTITY = 6;

    @Test
    public void testCountSearchResults() {
        setQueryValue(QUERY_TEXT).pressEnter();
        assertSearchResultsQuantity(MIN_RESULTS_QUANTITY);
    }

    @Test
    public void testImFeelingLuckyButton() {
        setQueryValue(QUERY_TEXT).pressEscape();
        imFeelingLuckyButton.click();
        assertUrlNotInitial();
        assertSearchResultContainsQueryTextGreaterThanOrEqualOne(QUERY_TEXT);
    }

    @Test
    public void testGoogleSearchButton() {
        setQueryValue(QUERY_TEXT).pressEscape();
        searchButton.click();
        assertSearchResultsQuantity(MIN_RESULTS_QUANTITY);
    }

}
