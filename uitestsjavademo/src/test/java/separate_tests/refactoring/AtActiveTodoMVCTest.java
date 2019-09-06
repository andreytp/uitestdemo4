package separate_tests.refactoring;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static separate_tests.refactoring.PageObject.*;

public class AtActiveTodoMVCTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testDeleteActiveTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter("active");
        delete("3");
        assertAre("1", "2", "4");
        assertKeysLeft("3");
    }


    @Test
    public void testMarkCompletedTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter("active");
        toggle("3");
        assertAre("1", "2", "4");
        assertKeysLeft("3");
        assertClearCompleted();

    }


    @Test
    public void testClearCompletedTaskOnActiveFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("active");
        clearCompleted();
        tasks.shouldBe(empty);

    }

    @Test
    public void testMarkCompletedAllTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter("active");
        toggleAll();
        assertKeysLeft("0");

    }

    @Test
    public void testMarkOutCompletedAllTaskOnActiveFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("active");
        toggleAll();
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

    }
}
