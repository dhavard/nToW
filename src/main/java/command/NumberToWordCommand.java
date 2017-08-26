package command;

import application.Options;
import convert.NumberToWordConverter;

/**
 * Singleton Command of type Options which will convert user integer input into English word equivalent.
 */
public enum NumberToWordCommand implements Command<Options> {

    INSTANCE;

    /**
     * Convert the supplied user integer input into English word equivalent without commas or hyphens and output it to the console.
     * Input is expected to be an integer with no non-digit characters present.
     * {@inheritDoc}
     */
    @Override
    public void execute(Options arg) {
        System.out.println();
        System.out.println(arg.getInput());
        System.out.println(NumberToWordConverter.INSTANCE.convert(arg.getInput()));
        System.out.println();
    }

    /**
     * Supports the '--n' command key.
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Options arg) {
        return arg.getCommandType().equalsIgnoreCase("--n");
    }
}
