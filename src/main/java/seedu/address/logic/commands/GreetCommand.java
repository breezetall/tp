package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class GreetCommand extends Command {

    public static final String COMMAND_WORD = "greet";

    public static final String MESSAGE_SUCCESS = "Hello!! ^_^";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
