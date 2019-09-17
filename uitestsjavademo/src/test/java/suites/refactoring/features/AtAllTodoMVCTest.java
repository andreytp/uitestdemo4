package suites.refactoring.features;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static suites.refactoring.features.PageObject.*;

public class AtAllTodoMVCTest extends AtTodoMVCPage {

    @Test
    public void testAddTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        assertTaskHaveExactTexts("1", "2", "3", "4");
        assertKeysLeftHaveActiveItems(4);
    }

    @Test
    public void testDeleteTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        delete("3");
        assertTaskHaveExactTexts("1", "2", "4");
        assertKeysLeftHaveActiveItems(3);
    }

    @Test
    public void testEditTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        editByText("3", "33");
        assertTaskHaveExactTexts("1", "2", "33", "4");

    }

    @Test
    public void testStartEditWithEscTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        escapeEditByText("3", "33");
        assertTaskHaveExactTexts("1", "2", "3", "4");

    }

    @Test
    public void testMarkCompletedTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        toggle("3");
        assertTaskHaveExactTexts("1", "2", "3", "4");
        assertKeysLeftHaveActiveItems(2);
        assertClearCompleted();

    }

    @Test
    public void testMarkOutCompletedTask() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        toggle("3");
        assertTaskHaveExactTexts("1", "2", "3", "4");
        assertKeysLeftHaveActiveItems(1);
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
        assertKeysLeftHaveActiveItems(0);
    }

    @Test
    public void testMarkOutCompletedAllTask() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        toggleAll();
        assertTaskHaveExactTexts("1", "2", "3", "4");
        assertKeysLeftHaveActiveItems(4);

    }


}
