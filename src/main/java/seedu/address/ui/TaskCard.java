package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label priority;
    @FXML
    private Label status;
    @FXML
    private Label description;
    @FXML
    private Label deadline;


    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().taskName);
        priority.setText(task.getPriority().toString());
        status.setText(task.getStatus().toString());
        description.setText(task.getDescription().taskDescription);
        deadline.setText(task.getDeadline().toString());

        String settings = "-fx-padding: 1 3 1 3; -fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 12;";

        if (task.getPriority().toString().equalsIgnoreCase("low")) {
            priority.setStyle("-fx-background-color: #00ff00;" + settings);
        } else if (task.getPriority().toString().equalsIgnoreCase("medium")) {
            priority.setStyle("-fx-background-color: #ffae00;" + settings);
        } else if (task.getPriority().toString().equalsIgnoreCase("high")) {
            priority.setStyle("-fx-background-color: #ff0000;" + settings);
        }

        if (task.getStatus().toString().equalsIgnoreCase("Done")) {
            status.setStyle("-fx-background-color: #00ff00;" + settings);
        } else if (task.getStatus().toString().equalsIgnoreCase("Not Done")) {
            status.setStyle("-fx-background-color: #ff0000;" + settings);
        }
    }
}
