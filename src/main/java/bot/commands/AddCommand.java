package bot.commands;

import java.util.List;

import bot.tasks.Deadline;
import bot.tasks.Event;
import bot.tasks.Task;
import bot.tasks.ToDo;
import bot.utility.TaskList;

public class AddCommand extends Command {
    protected static final String TASK_FORMAT = "\n\t Got it. I've added this task:\n\t\t%s\n\t ";
    private final String keyWord;
    private final String info;
    /**
     * Returns a AddCommand.
     */
    public AddCommand(String keyWord, String info) {
        this.keyWord = keyWord;
        this.info = info;
    }
    @Override
    public String execute() {
        List<Task> tasks = TaskList.showTasks();
        switch (keyWord) {
        case "todo":
            ToDo todo = new ToDo(info);
            tasks.add(todo);
            return formatMessage(todo.toString(), tasks.size());
        case "deadline":
            String[] details = info.split(" /by ", 2);
            Deadline deadline = new Deadline(details[0], details[1]);
            tasks.add(deadline);
            return formatMessage(deadline.toString(), tasks.size());
        case "event":
            details = info.split(" /at ", 2);
            Event event = new Event(details[0], details[1]);
            tasks.add(event);
            return formatMessage(event.toString(), tasks.size());
        default:
            break;
        }
        return "Hmm, this is quite the predicament. I can't comprehend the instruction.";
    }
    private String formatMessage(String objectString, int size) {
        return String.format(TASK_FORMAT, objectString) + String.format(INFORM_FORMAT, size);
    }
}
