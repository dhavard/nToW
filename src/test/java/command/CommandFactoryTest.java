package command;

import application.Options;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CommandFactoryTest {

    @Test
    public void getCommand_numberToWord() {
        assertThat(CommandFactory.INSTANCE.getCommand(Options.INSTANCE.setCommandType("--n")), instanceOf(NumberToWordCommand.class));
    }

    @Test
    public void getCommand_verboseNumberToWord() {
        assertThat(CommandFactory.INSTANCE.getCommand(Options.INSTANCE.setCommandType("--v")), instanceOf(VerboseNumberToWordCommand.class));
    }

    @Test
    public void getCommand_help() {
        assertThat(CommandFactory.INSTANCE.getCommand(Options.INSTANCE.setCommandType("--h")), instanceOf(HelpCommand.class));
        assertThat(CommandFactory.INSTANCE.getCommand(Options.INSTANCE.setCommandType("--help")), instanceOf(HelpCommand.class));
    }

    @Test
    public void getCommand_helpIsDefault() {
        assertThat(CommandFactory.INSTANCE.getCommand(Options.INSTANCE.setCommandType("45467342457865432576")), instanceOf(HelpCommand.class));
        assertThat(CommandFactory.INSTANCE.getCommand(Options.INSTANCE.setCommandType("asdjlfjkahwe#*(*@$HIUhklfajfdh2")), instanceOf(HelpCommand.class));
    }
}
