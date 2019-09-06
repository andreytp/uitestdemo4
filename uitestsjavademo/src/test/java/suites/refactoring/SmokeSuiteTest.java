package suites.refactoring;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import suites.refactoring.categories.Smoke;
import suites.refactoring.features.AtAllSmokeTodoMVCTest;

@RunWith(Categories.class)
@Suite.SuiteClasses(AtAllSmokeTodoMVCTest.class)
@Categories.IncludeCategory(Smoke.class)
public class SmokeSuiteTest {
}
