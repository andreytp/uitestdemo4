package separate_tests.refactoring;

public class Task {
    private Boolean taskType;
    private String title;

    Task(boolean taskType, String title){
        this.taskType = taskType;
        this.title = title;
    }

    Task(TaskType taskType, String title){
        this.taskType = taskType == TaskType.COMPLETED;
        this.title = title;
    }

    @Override
    public String toString(){
        return String.format("{\\\"completed\\\":%s,\\\"title\\\":\\\"%s\\\"}",taskType.toString(),title);
    }
}
