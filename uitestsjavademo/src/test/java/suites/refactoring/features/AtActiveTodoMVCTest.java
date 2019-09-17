package suites.refactoring.features;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static suites.refactoring.features.PageObject.*;
import static suites.refactoring.features.TodosFilters.FILTER_ACTIVE;

public class AtActiveTodoMVCTest extends AtTodoMVCPage {

    @Test
    public void testDeleteActiveTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter(FILTER_ACTIVE);
        delete("3");
        assertTaskHaveExactTexts("1", "2", "4");
        assertKeysLeftHaveActiveItems(3);
    }


    @Test
    public void testMarkCompletedTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter(FILTER_ACTIVE);
        toggle("3");
        assertTaskHaveExactTexts("1", "2", "4");
        assertKeysLeftHaveActiveItems(3);
        assertClearCompleted();

    }


    @Test
    public void testClearCompletedTaskOnActiveFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter(FILTER_ACTIVE);
        clearCompleted();
        tasks.shouldBe(empty);

    }

    @Test
    public void testMarkCompletedAllTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter(FILTER_ACTIVE);
        toggleAll();
        assertKeysLeftHaveActiveItems(0);

    }

    @Test
    public void testMarkOutCompletedAllTaskOnActiveFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter(FILTER_ACTIVE);
        toggleAll();
        assertTaskHaveExactTexts("1", "2", "3", "4");
        assertKeysLeftHaveActiveItems(4);

    }
}
