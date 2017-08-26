package application;

/**
 * Singleton class used to parse user input from the command line
 */
public enum Parser {

    INSTANCE;

    private static final int MAX_ARGS = 2;
    private static final int MAX_COMMAND_ARGS = 1;
    private static final int MAX_INPUT_ARGS = 1;

    private static final String COMMAND_MARK = "--";
    private static final String HELP_COMMAND = "--h";

    public static final String MAX_ARGS_STR = "No more than two arguments can be supplied at one time.";
    public static final String MAX_COMMAND_ARGS_STR = "Only one command can be supplied.";
    public static final String MAX_INPUT_ARGS_STR = "Only one input value can be supplied";

    /**
     * Parse the user provided arguments supplied by the commandline Splitter.
     *
     * @param args The splitter supplied arguments.
     * @return an Options object representing the parsed results
     */
    public Options parse(Iterable<String> args) {
        int commandCount = 0;
        int inputCount = 0;
        int loopCount = 0;
        for (String arg : args) {
            if (loopCount > MAX_ARGS) {
                System.out.println(MAX_ARGS_STR);
                Options.INSTANCE.setCommandType(HELP_COMMAND);
                break;
            }

            if (arg.startsWith(COMMAND_MARK)) {
                if (commandCount >= MAX_COMMAND_ARGS) {
                    System.out.println(MAX_COMMAND_ARGS_STR);
                    Options.INSTANCE.setCommandType(HELP_COMMAND);
                    break;
                } else {
                    commandCount++;
                    Options.INSTANCE.setCommandType(arg);
                }
            } else {
                if (inputCount >= MAX_INPUT_ARGS) {
                    System.out.println(MAX_INPUT_ARGS_STR);
                    Options.INSTANCE.setCommandType(HELP_COMMAND);
                    break;
                } else {
                    inputCount++;
                    Options.INSTANCE.setInput(arg);
                }
            }
            loopCount++;
        }

        return Options.INSTANCE;
    }

}
