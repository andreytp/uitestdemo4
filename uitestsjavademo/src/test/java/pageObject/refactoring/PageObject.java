package pageObject.refactoring;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageObject {
    public static ElementsCollection tasks = $$("#todo-list li").filterBy(Condition.visible);

    @Step
    public static void toggleAll() {
        $("#toggle-all").click();
    }

    private static int getFiltersIndex(@org.jetbrains.annotations.NotNull String filter) {

        if (filter.equals("all"))
            return 0;

        if (filter.equals("active"))
            return 1;

        if (filter.equals("completed"))
            return 2;

        return 0;

    }

    @Step
    public static void setFilter(String filter) {

        $$("#filters li").get(getFiltersIndex(filter)).click();

    }

    @Step
    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public static void assertAre(String... tasksText) {
        tasks.shouldHave(exactTexts(tasksText));
    }

    @Step
    public static void assertKeysLeft(String keyLeft){
        $("#todo-count").shouldHave(exactText(keyLeft + " item" + (keyLeft.equals("1")?"":"s")+" left"));
    }

    @Step
    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    private void edit(int taskPosition, String newTaskText) {
        tasks.get(taskPosition - 1).doubleClick();
        tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

    @Step
    public static void editByText(String oldTaskText, String newTaskText) {
        startEditTask(oldTaskText, newTaskText).pressEnter();
    }

    @Step
    public static void escapeEditByText(String oldTaskText, String newTaskText) {
        startEditTask(oldTaskText, newTaskText).pressEscape();
    }

    @Step
    public static void add(String... taskText) {

        for (String task : taskText) {
            $("#new-todo").setValue(task).pressEnter();
        }

    }

    @Step
    public static void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public static void assertClearCompleted() {
        $("#clear-completed").shouldBe(Condition.visible);
    }


    public static SelenideElement startEditTask(String oldTaskText, String newTaskText){
        tasks.find(exactText(oldTaskText)).doubleClick();
        return tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText);


    }

}
