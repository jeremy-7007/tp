package seedu.address.model.task;

import seedu.address.model.id.UniqueId;

/**
 * An immutable class that represents the assignment of a task to a student. Can keep track of whether
 * the student has done the task.
 */
public class TaskCompletion {
    private final UniqueId agentId;
    private final UniqueId taskId;
    private final boolean isDone;

    public TaskCompletion(UniqueId agentId, UniqueId taskId) {
        this.agentId = agentId;
        this.taskId = taskId;
        this.isDone = false;
    }

    public TaskCompletion(UniqueId agentId, UniqueId taskId, boolean isDone) {
        this.agentId = agentId;
        this.taskId = taskId;
        this.isDone = isDone;
    }

    public UniqueId getAgentId() {
        return agentId;
    }

    public UniqueId getTaskId() {
        return taskId;
    }

    /**
     * Returns whether the task has been done by the agent
     *
     * @return whether the task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a String representation of whether the task is done by the agent
     *
     * @return "1" if the task is done, "0" otherwise
     */
    public String getDoneString() {
        return isDone ? "1" : "0";
    }

    /**
     * Marks the task as done
     */
    public TaskCompletion markAsDone() {
        return new TaskCompletion(agentId, taskId, true);
    }

    /**
     * Marks the task as not done
     */
    public TaskCompletion markAsUndone() {
        return new TaskCompletion(agentId, taskId, false);
    }

    /**
     * Used to compare two TaskCompletion objects. TaskCompletion objects are the same if
     * they have the same agent and task (regardless of done status).
     *
     * @param other the other object to compare this object to
     * @return whether the other object is the same as this object
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof TaskCompletion)) {
            return false;
        }

        TaskCompletion otherAssignment = (TaskCompletion) other;
        boolean isSameStudent = this.agentId.equals(otherAssignment.agentId);
        boolean isSameTask = this.taskId.equals(otherAssignment.taskId);
        return isSameStudent && isSameTask;
    }

    @Override
    public int hashCode() {
        System.out.println("In hash code");
        int agentIdHashCode = agentId.getUuid().toString().hashCode();
        int taskIdHashCode = taskId.getUuid().toString().hashCode();
        return agentIdHashCode + taskIdHashCode;
    }

    @Override
    public String toString() {
        return agentId.getUuid().toString() + "+" + taskId.getUuid().toString() + "+" + getDoneString();
    }
}
