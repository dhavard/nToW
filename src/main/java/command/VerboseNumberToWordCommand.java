package command;

import application.Options;
import convert.VerboseNumberToWordConverter;

/**
 * Singleton Command of type Options which will convert user integer input into English word equivalent using commas and hyphens.
 */
public enum VerboseNumberToWordCommand implements Command<Options> {

    INSTANCE;

    /**
     * Convert the supplied user integer input into English word equivalent and output it to the console.
     * Input is expected to be an integer with no non-digit characters present other than commas, decimals, and spaces.
     * {@inheritDoc}
     */
    @Override
    public void execute(Options arg) {
        System.out.println();
        System.out.println(arg.getInput());
        System.out.println(VerboseNumberToWordConverter.INSTANCE.convert(arg.getInput()));
        System.out.println();
    }


    /**
     * Supports the '--v' command key.
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Options arg) {
        return arg.getCommandType().equalsIgnoreCase("--v");
    }
}
