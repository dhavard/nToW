package command;

import application.Options;

/**
 * Singleton Command of type Options which outputs help text to the user via the console.
 */
public enum HelpCommand implements Command<Options> {

    INSTANCE;

    /**
     * Output help text to the user via the console.
     * {@inheritDoc}
     */
    @Override
    public void execute(Options arg) {
        System.out.println("This application is designed to convert numbers to words and vice versa.");
        System.out.println("Options:");
        System.out.println("--h             Print this help output.");
        System.out.println("--q             Quit the application.");
        System.out.println("--n <integer>   Convert the supplied integer into English word equivalent.");
        System.out.println("--v <integer>   Convert the supplied integer into English word equivalent with appropriate commas and hyphens. Uses less restrictive input validation.");
        System.out.println("");
        System.out.println("Subsequent inputs will assume the same options unless specified.");
    }

    /**
     * Supports any command key; meaning that this method always returns true.
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Options arg) {
        return true;
    }
}
