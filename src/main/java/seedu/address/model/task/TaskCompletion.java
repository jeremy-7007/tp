package seedu.address.model.task;

import seedu.address.model.person.Person;

/**
 * A class that represents the assignment of a task to a student. Can keep track of whether
 * the student has done the task.
 */
public class TaskCompletion {
    private final Person student;
    private final Task task;
    private boolean isDone;

    public TaskCompletion(Person student, Task task) {
        this.student = student;
        this.task = task;
        this.isDone = false;
    }

    public Person getStudent() {
        return student;
    }

    public Task getTask() {
        return task;
    }

    /**
     * Returns whether the task has been done by the student
     * @return whether the task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return task.getDescription().toString() + " assigned to " + student.getName();
    }
}
