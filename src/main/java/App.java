import application.Options;
import application.Parser;
import com.google.common.base.Splitter;

import java.util.Scanner;

/**
 * Commandline application which can be used to invoke a variety of String conversion functions via user input. Expected to be launched via the supplied gradle wrapper 'gradlew run'. Type '--h' to get help on how to use the application.
 */
public class App {
    private static final String QUIT = "-q";
    private static final String QUIT_DOUBLE = "--q";
    private static final String QUIT_LONG = "-quit";
    private static final String PROMPT = "nToW>> ";

    /**
     * Default execution entry point.
     */
    public static void main(String[] args) {
        System.out.println(PROMPT);
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            String userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase(QUIT) || userInput.equalsIgnoreCase(QUIT_DOUBLE) || userInput.equalsIgnoreCase(QUIT_LONG)) {
                running = false;
            } else {
                Options opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split(userInput));
                opts.execute();
            }
        }
        scan.close();
    }
}
