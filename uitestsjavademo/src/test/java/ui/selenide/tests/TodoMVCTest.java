package ui.selenide.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.sun.javafx.scene.traversal.TopMostTraversalEngine;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;

public class TodoMVCTest {
    @Test
    public void createTask(){
        open("http://todomvc4tasj.herokuapp.com/#");
        $("#new-todo").setValue("task1").pressEnter();
        $("#new-todo").setValue("task2").pressEnter();
        $("#new-todo").setValue("task3").pressEnter();
        $("#new-todo").setValue("task4").pressEnter();
        $$("#todo-list li").shouldHave(exactTexts("task1", "task2", "task3", "task4"));
        $(By.xpath("//div[contains(label/text(),'task2')]/label")).click();
        $(By.xpath("//div[contains(label/text(),'task2')]/label")).click();
        $(By.xpath("//div[contains(label/text(),'task2')]/button")).click();
        $$("#todo-list li").shouldHave(size(3));
        $(By.xpath("//div[contains(label/text(),'task4')]/label")).click();
        $(By.xpath("//div[contains(label/text(),'task4')]/input")).click();
        $("#clear-completed").click();
        $$("#todo-list li").shouldHave(size(2));
        $("#toggle-all").click();
        $("#clear-completed").click();
        $$("#todo-list li").shouldBe(empty);

    }
}
