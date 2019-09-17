package suites.refactoring.features;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import suites.refactoring.categories.End2End;
import suites.refactoring.categories.Smoke;

import static com.codeborne.selenide.CollectionCondition.empty;
import static suites.refactoring.features.PageObject.*;
import static suites.refactoring.features.TodosFilters.*;

@Category({Smoke.class, End2End.class})
public class AtAllSmokeTodoMVCTest extends AtTodoMVCPage {

    @Test
    public void testTodoMVC() {

//        add("1", "2", "3", "4");
//        given(TaskType.ACTIVE,"1", "2", "3", "4");
        given(aTask("1", ACTIVE),
                aTask("2"),
                aTask("3", ACTIVE),
                aTask("4", ACTIVE));

        assertTaskHaveExactTexts("1", "2", "3", "4");
        assertKeysLeftHaveActiveItems(4);

        //edit task
        editByText("3", "33");
        assertTaskHaveExactTexts("1", "2", "33", "4");

        editByText("33", "3");
        assertTaskHaveExactTexts("1", "2", "3", "4");

        escapeEditByText("3", "33");
        assertTaskHaveExactTexts("1", "2", "3", "4");


        //delete
        delete("2");
        assertTaskHaveExactTexts("1", "3", "4");
        assertKeysLeftHaveActiveItems(3);

        //mark completed & delete
        toggle("4");
        setFilter(FILTER_COMPLETED);
        assertTaskHaveExactTexts("4");
        assertKeysLeftHaveActiveItems(2);


        setFilter(FILTER_ACTIVE);
        assertTaskHaveExactTexts("1", "3");
        assertKeysLeftHaveActiveItems(2);

        clearCompleted();
        setFilter(FILTER_ALL);
        assertTaskHaveExactTexts("1", "3");
        assertKeysLeftHaveActiveItems(2);

        //mark completed & delete
        toggleAll();
        clearCompleted();
        tasks.shouldBe(empty);


    }
}
