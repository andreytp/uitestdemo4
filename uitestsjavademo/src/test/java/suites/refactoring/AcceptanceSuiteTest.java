package suites.refactoring;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import suites.refactoring.categories.End2End;
import suites.refactoring.categories.Smoke;
import suites.refactoring.features.AtActiveTodoMVCTest;
import suites.refactoring.features.AtAllSmokeTodoMVCTest;
import suites.refactoring.features.AtCompletedTodoMVCTest;

@RunWith(Categories.class)
@Suite.SuiteClasses({AtAllSmokeTodoMVCTest.class, AtActiveTodoMVCTest.class, AtActiveTodoMVCTest.class, AtCompletedTodoMVCTest.class})
@Categories.ExcludeCategory({Smoke.class, End2End.class})
public class AcceptanceSuiteTest {
}
