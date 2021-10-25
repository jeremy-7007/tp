package seedu.address.storage;

import seedu.address.model.id.UniqueId;
import seedu.address.model.task.TaskCompletion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Jackson-friendly version of {@link TaskCompletion}.
 */
public class JsonAdaptedTaskCompletion {
    private final String agentIdString;
    private final String taskIdString;
    private final String isDoneString;

    /**
     * Constructs a {@code JsonAdaptedTaskCompletion} with the given {@code taskCompletionString}.
     */
    @JsonCreator
    public JsonAdaptedTaskCompletion(String taskCompletionString) {
        String trimmedTaskCompletionString = taskCompletionString.trim();
        String[] wordArray = trimmedTaskCompletionString.split("\\+");

        assert wordArray.length == 3;

        this.agentIdString = wordArray[0];
        this.taskIdString = wordArray[1];
        this.isDoneString = wordArray[2];
    }

    /**
     * Converts a given {@code TaskCompletion} into this class for Jackson use.
     */
    public JsonAdaptedTaskCompletion(TaskCompletion source) {
        this.agentIdString = source.getAgentId().getUuid().toString();
        this.taskIdString = source.getTaskId().getUuid().toString();
        this.isDoneString = source.getDoneString();
    }

    @JsonValue
    public String getCombinedString() {
        return agentIdString + "+" + taskIdString + "+" + isDoneString;
    }

    /**
     * Converts this Jackson-friendly adapted TaskCompletion object into the model's {@code TaskCompletion} object.
     */
    public TaskCompletion toModelType() {
        UniqueId agentId = UniqueId.generateId(agentIdString);
        UniqueId taskId = UniqueId.generateId(taskIdString);
        boolean isDone = isDoneString.equals("1");
        return new TaskCompletion(agentId, taskId, isDone);
    }
}
