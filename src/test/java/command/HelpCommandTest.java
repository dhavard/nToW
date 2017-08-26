package command;

import application.Options;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HelpCommandTest {

    @Before
    public void setUpStreams() {
        System.setOut(mock(PrintStream.class));
        System.setErr(mock(PrintStream.class));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void execute_printsToConsole() {
        HelpCommand.INSTANCE.execute(Options.INSTANCE);
        verify(System.out, times(8)).println(anyString());
    }

    @Test
    public void supports_canary() {
        assertTrue("HelpCommand key should be '--h'", HelpCommand.INSTANCE.supports(Options.INSTANCE.setCommandType("--h")));
        assertTrue("HelpCommand key should work with '--help'", HelpCommand.INSTANCE.supports(Options.INSTANCE.setCommandType("--help")));
        assertTrue("HelpCommand key should work with anything as it is the default command", HelpCommand.INSTANCE.supports(Options.INSTANCE.setCommandType("asdjf;akls8202^$%(^)*(3hasdhfp09unvp")));
    }
}
