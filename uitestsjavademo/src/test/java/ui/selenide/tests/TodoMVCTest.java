package ui.selenide.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.sun.javafx.scene.traversal.TopMostTraversalEngine;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;

public class TodoMVCTest {
    @Test
    public void createTask(){
        open("http://todomvc4tasj.herokuapp.com/#");
        $("#new-todo").setValue("do something").pressEnter();
        $("#new-todo").setValue("do something else").pressEnter();
        $$("#todo-list li").shouldHave(exactTexts("do something", "do something else"));
        $("#toggle-all").click();
        $("#clear-completed").click();
        $$("#todo-list li").shouldBe(empty);

    }
}
