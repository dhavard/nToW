package command;

import application.Options;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class VerboseNumberToWordCommandTest {

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
    public void execute_printsToConsole()
    {
        VerboseNumberToWordCommand.INSTANCE.execute(Options.INSTANCE.setInput("234"));
        verify(System.out, times(2)).println(anyString());
        verify(System.out, times(2)).println();
    }

    @Test public void supports_canary()
    {
        assertTrue("VerboseNumberToWordCommand key should be '--v'", VerboseNumberToWordCommand.INSTANCE.supports(Options.INSTANCE.setCommandType("--v")));
        assertFalse("VerboseNumberToWordCommand key should be not collide with NumberToWordCommand key", VerboseNumberToWordCommand.INSTANCE.supports(Options.INSTANCE.setCommandType("--n")));
    }
}
