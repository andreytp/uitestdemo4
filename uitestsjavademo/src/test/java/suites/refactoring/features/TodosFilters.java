package suites.refactoring.features;

public enum TodosFilters {
    FILTER_ALL(0), FILTER_ACTIVE(1), FILTER_COMPLETED(2);
    private int index;

    TodosFilters(int i) {
        index = i;
    }

    int getIndex(){
        return index;
    }
}
