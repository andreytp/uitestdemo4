package separate_tests.refactoring;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static separate_tests.refactoring.PageObject.*;

public class AtAllTodoMVCTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testAddTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");
    }

    @Test
    public void testDeleteTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        delete("3");
        assertAre("1", "2", "4");
        assertKeysLeft("3");
    }

    @Test
    public void testEditTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        editByText("3", "33");
        assertAre("1", "2", "33", "4");

    }

    @Test
    public void testStartEditWithEscTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        escapeEditByText("3", "33");
        assertAre("1", "2", "3", "4");

    }

    @Test
    public void testMarkCompletedTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        toggle("3");
        assertAre("1", "2", "3", "4");
        assertKeysLeft("3");
        assertClearCompleted();

    }

    @Test
    public void testMarkOutCompletedTask() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        toggle("3");
        assertAre("1", "2", "3", "4");
        assertKeysLeft("1");
        assertClearCompleted();

    }

    @Test
    public void testClearCompletedTask() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        clearCompleted();
        tasks.shouldBe(empty);

    }

    @Test
    public void testMarkCompletedAllTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        toggleAll();
        assertKeysLeft("0");
    }

    @Test
    public void testMarkOutCompletedAllTask() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        toggleAll();
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

    }


}
