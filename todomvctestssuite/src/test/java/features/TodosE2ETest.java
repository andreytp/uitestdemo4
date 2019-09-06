package features;

import categories.Smoke;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.codeborne.selenide.CollectionCondition.empty;
import static features.pages.PageObject.*;

@Category(Smoke.class)
public class TodosE2ETest extends AtTodoMVCPageWithClearedDataAfterEachTest{
    @Test
    public void testTodoMVC() {
        given(aTask("1", ACTIVE),
                aTask("2"),
                aTask("3", ACTIVE),
                aTask("4", ACTIVE));

        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

        //edit task
        editByText("3", "33");
        assertAre("1", "2", "33", "4");

        editByText("33", "3");
        assertAre("1", "2", "3", "4");

        escapeEditByText("3", "33");
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


}
