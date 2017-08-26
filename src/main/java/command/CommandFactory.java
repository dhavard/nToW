package command;

import application.Options;

/**
 * Singleton Factory used to get Command instances based on user supplied Options
 */
public enum CommandFactory {

    INSTANCE;

    /**
     * Get a Command of the appropriate type based on user supplied Options
     *
     * @param opts The user supplied Options used to determine the appropriate Command
     * @return The appropriately typed Command
     */
    public Command<Options> getCommand(Options opts) {
        if (NumberToWordCommand.INSTANCE.supports(opts)) {
            return NumberToWordCommand.INSTANCE;
        } else if (VerboseNumberToWordCommand.INSTANCE.supports(opts)) {
            return VerboseNumberToWordCommand.INSTANCE;
        }

        return HelpCommand.INSTANCE;
    }
}
