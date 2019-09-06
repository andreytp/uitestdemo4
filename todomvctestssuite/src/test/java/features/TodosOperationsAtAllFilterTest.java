package features;

import categories.Buggy;
import features.ancillary.TaskType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.codeborne.selenide.CollectionCondition.empty;
import static features.pages.PageObject.*;

public class TodosOperationsAtAllFilterTest extends AtTodoMVCPageWithClearedDataAfterEachTest {
//region Filter ALL tests
    @Test
    public void testDeleteTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        delete("3");
        assertAre("1", "2", "4");
        assertKeysLeft("3");
    }

    //general filter
    @Test
    public void testAddTask() {
        given(TaskType.ACTIVE, "1", "2", "3", "4");
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");
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
//endregion

//region Filter ACTIVE tests

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

//endregion

//region Filter COMPLETED tests
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

//endregion

//region Buggy test

    @Category(Buggy.class)
    @Test
    public void testFail() {
        given(TaskType.COMPLETED, "1", "2", "3", "4");
        setFilter("completed");
        toggle("3");
        assertAre("1", "2", "3");
    }

//endregion

}
