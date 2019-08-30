package oop.refactoring;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class TodoMVCTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testTodoMVC() {

        add("1", "2", "3", "4");
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

        //edit task
        editByText("3", "33");
        assertAre("1", "2", "33", "4");

        editByText("33", "3");
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

        add("1", "2", "3", "4");
        assertAre("1", "2", "3", "4");
        assertKeysLeft("4");

    }

    ElementsCollection tasks = $$("#todo-list li").filterBy(Condition.visible);

    @Step
    private void toggleAll() {
        $("#toggle-all").click();
    }

    private int getFiltersIndex(@org.jetbrains.annotations.NotNull String filter) {

        if (filter.equals("all"))
            return 0;

        if (filter.equals("active"))
            return 1;

        if (filter.equals("completed"))
            return 2;

        return 0;

    }

    @Step
    private void setFilter(String filter) {

        $$("#filters li").get(getFiltersIndex(filter)).click();

    }

    @Step
    private void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    private void assertAre(String... tasksText) {
        tasks.shouldHave(exactTexts(tasksText));
    }

    @Step
    private void assertKeysLeft(String keyleft){
        $("#todo-count").shouldHave(exactText(keyleft + " items left"));
    }

    @Step
    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    private void edit(int taskPosition, String newTaskText) {
        tasks.get(taskPosition - 1).doubleClick();
        tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

    @Step
    private void editByText(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

    @Step
    private void add(String... taskText) {

        for (String task : taskText) {
            $("#new-todo").setValue(task).pressEnter();
        }

    }

    @Step
    private void clearCompleted() {
        $("#clear-completed").click();
    }


}
