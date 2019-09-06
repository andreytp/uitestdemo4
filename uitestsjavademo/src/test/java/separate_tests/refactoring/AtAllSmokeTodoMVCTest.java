package separate_tests.refactoring;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static separate_tests.refactoring.PageObject.*;

public class AtAllSmokeTodoMVCTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testTodoMVC() {

//        add("1", "2", "3", "4");
//        given(TaskType.ACTIVE,"1", "2", "3", "4");
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
