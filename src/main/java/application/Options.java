package application;

import command.Command;
import command.CommandFactory;

/**
 * Singleton container class to hold results of parsing user input for sharing within the application.
 */
public enum Options {

    INSTANCE;

    private String _input;
    private String _commandType = "--h";

    /**
     * The input the user intends for the application to convert
     */
    public String getInput() {
        return _input;
    }

    public Options setInput(String input) {
        _input = input;
        return this;
    }

    /**
     * The command key supplied by the user input which corresponds to the desired converter
     */
    public String getCommandType() {
        return _commandType == null ? "--h" : _commandType;
    }

    public Options setCommandType(String commandType) {
        _commandType = commandType;
        return this;
    }

    /**
     * The Command corresponding to the commandType
     */
    public Command<Options> getCommand() {
        return CommandFactory.INSTANCE.getCommand(this);
    }

    /**
     * Convenience method to execute the command
     */
    public Command<Options> execute() {
        Command<Options> command = getCommand();
        command.execute(this);
        return command;
    }
}
