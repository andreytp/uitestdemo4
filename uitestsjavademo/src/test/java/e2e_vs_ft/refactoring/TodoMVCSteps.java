package e2e_vs_ft.refactoring;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodoMVCSteps extends AtTodoMVCPageWithClearedDataAfterEachTest {
    ElementsCollection tasks = $$("#todo-list li").filterBy(Condition.visible);

    @Step
    protected void toggleAll() {
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
    protected void setFilter(String filter) {

        $$("#filters li").get(getFiltersIndex(filter)).click();

    }

    @Step
    protected void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    protected void assertAre(String... tasksText) {
        tasks.shouldHave(exactTexts(tasksText));
    }

    @Step
    protected void assertKeysLeft(String keyleft){
        $("#todo-count").shouldHave(exactText(keyleft + " items left"));
    }

    @Step
    protected void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    private void edit(int taskPosition, String newTaskText) {
        tasks.get(taskPosition - 1).doubleClick();
        tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

    @Step
    protected void editByText(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

    @Step
    protected void add(String... taskText) {

        for (String task : taskText) {
            $("#new-todo").setValue(task).pressEnter();
        }

    }

    @Step
    protected void clearCompleted() {
        $("#clear-completed").click();
    }

}
