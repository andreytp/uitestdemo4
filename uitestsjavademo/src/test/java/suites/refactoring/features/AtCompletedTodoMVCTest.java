package suites.refactoring.features;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static suites.refactoring.features.PageObject.*;
import static suites.refactoring.features.TodosFilters.FILTER_COMPLETED;

public class AtCompletedTodoMVCTest extends AtTodoMVCPage {

    @Test
    public void testDeleteCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter(FILTER_COMPLETED);
        delete("3");
        assertTaskHaveExactTexts("1", "2", "4");
        assertKeysLeftHaveActiveItems(0);
    }

    @Test
    public void testMarkOutCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter(FILTER_COMPLETED);
        toggle("3");
        assertTaskHaveExactTexts("1", "2", "4");
        assertKeysLeftHaveActiveItems(1);
        assertClearCompleted();
    }

    @Test
    public void testClearCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter(FILTER_COMPLETED);
        clearCompleted();
        tasks.shouldBe(empty);
    }

    @Test
    public void testMarkCompletedAllTaskOnCompletedFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter(FILTER_COMPLETED);
        toggleAll();
        assertTaskHaveExactTexts("1", "2", "3", "4");
        assertKeysLeftHaveActiveItems(0);
    }

    @Test
    public void testMarkOutCompletedAllTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter(FILTER_COMPLETED);
        toggleAll();
        assertKeysLeftHaveActiveItems(4);
    }
}
