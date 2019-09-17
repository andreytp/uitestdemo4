package suites.refactoring.features;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageObject {

    public static ElementsCollection tasks = $$("#todo-list li").filterBy(Condition.visible);

    @Step
    public static void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public static void setFilter(TodosFilters filter) {

        $$("#filters li").get(filter.getIndex()).click();

    }

    @Step
    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public static void assertTaskHaveExactTexts(String... tasksTexts) {
        tasks.shouldHave(exactTexts(tasksTexts));
    }

    @Step
    public static void assertKeysLeftHaveActiveItems(Integer keyLeft) {
        $("#todo-count").shouldHave(exactText(keyLeft + " item" + ("1".equals(keyLeft.toString()) ? "" : "s") + " left"));
    }

    @Step
    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").hover().click();
    }

    @Step
    public static void editByText(String oldTaskText, String newTaskText) {
        startEditTask(oldTaskText, newTaskText);
        $("#new-todo").click();

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

    @Step
    public static SelenideElement startEditTask(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        return tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText);


    }

    @Step
    private void edit(int taskPosition, String newTaskText) {
        tasks.get(taskPosition - 1).doubleClick();
        tasks.find(Condition.cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

}
