package separate_tests.refactoring;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static separate_tests.refactoring.PageObject.*;

public class AtCompletedTodoMVCTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testDeleteCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");
        delete("3");
        assertAre("1", "2", "4");
        assertKeysLeft("0");
    }


    @Test
    public void testMarkOutCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");

        toggle("3");
        assertAre("1", "2", "4");
        assertKeysLeft("1");
        assertClearCompleted();

    }


    @Test
    public void testClearCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");
        clearCompleted();
        tasks.shouldBe(empty);

    }


    @Test
    public void testMarkCompletedAllTaskOnCompletedFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter("completed");
        toggleAll();
        assertAre("1", "2", "3", "4");
        assertKeysLeft("0");

    }


    @Test
    public void testMarkOutCompletedAllTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");
        toggleAll();
        assertKeysLeft("4");

    }


}
