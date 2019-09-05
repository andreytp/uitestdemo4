package pageObject.refactoring;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static pageObject.refactoring.PageObject.*;

public class TodoMVCTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testTodoMVC() {

//        add("1", "2", "3", "4");
//        given(TaskType.ACTIVE,"1", "2", "3", "4");
        given(aTask("1", TaskType.ACTIVE),
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
    public void testDeleteActiveTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter("active");
        delete("3");
        assertAre("1", "2", "4");
        assertKeysLeft("3");
    }

    @Test
    public void testDeleteCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");
        delete("3");
        assertAre("1", "2", "4");
        assertKeysLeft("0");
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
    public void testMarkCompletedTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter("active");
        toggle("3");
        assertAre("1", "2", "4");
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
    public void testMarkOutCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");
        toggle("3");
        assertAre("1", "2", "4");
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
    public void testClearCompletedTaskOnActiveFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("active");
        clearCompleted();
        tasks.shouldBe(empty);

    }

    @Test
    public void testClearCompletedTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");
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
    public void testMarkCompletedAllTaskOnActiveFilter() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        setFilter("active");
        toggleAll();
        assertKeysLeft("0");

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
    public void testMarkOutCompletedAllTask() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        toggleAll();
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

    }

    @Test
    public void testMarkOutCompletedAllTaskOnActiveFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("active");
        toggleAll();
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

    }

    @Test
    public void testMarkOutCompletedAllTaskOnCompletedFilter() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");
        toggleAll();
        assertKeysLeft("4");

    }


}
