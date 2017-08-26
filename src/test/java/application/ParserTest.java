package application;

import com.google.common.base.Splitter;
import command.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ParserTest {

    @Before
    public void setup() {
        Options.INSTANCE.setInput(null).setCommandType(null);
    }

    @Test
    public void parse_noInput() {
        Options opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split(""));
        assertEquals("--h", opts.getCommandType());
        assertEquals(null, opts.getInput());
        assertThat(opts.getCommand(), instanceOf(HelpCommand.class));
    }

    @Test
    public void parse_helpIsDefault() {
        Options opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("gfasgdsa"));
        assertEquals("--h", opts.getCommandType());
        assertThat(opts.getCommand(), instanceOf(HelpCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("gfasgdsa  sdfsd -n 234234 sfw, w ergdfs"));
        assertEquals("--h", opts.getCommandType());
        assertThat(opts.getCommand(), instanceOf(HelpCommand.class));
    }

    @Test
    public void parse_forwardOrder() {
        Options opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("--n 23424"));
        assertEquals("--n", opts.getCommandType());
        assertEquals("23424", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(NumberToWordCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("--v 7567567567"));
        assertEquals("--v", opts.getCommandType());
        assertEquals("7567567567", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(VerboseNumberToWordCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("--h"));
        assertEquals("--h", opts.getCommandType());
        assertThat(opts.getCommand(), instanceOf(HelpCommand.class));
    }

    @Test
    public void parse_reverseOrder() {
        Options opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("23424 --n"));
        assertEquals("--n", opts.getCommandType());
        assertEquals("23424", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(NumberToWordCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("7567567567 --v"));
        assertEquals("--v", opts.getCommandType());
        assertEquals("7567567567", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(VerboseNumberToWordCommand.class));
    }

    @Test
    public void parse_negativeNumbers() {
        Options opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("-23424 --n"));
        assertEquals("--n", opts.getCommandType());
        assertEquals("-23424", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(NumberToWordCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("--v -7567567567"));
        assertEquals("--v", opts.getCommandType());
        assertEquals("-7567567567", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(VerboseNumberToWordCommand.class));
    }

    @Test
    public void parse_commandMemory() {
        Options opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("-23424 --n"));
        assertEquals("--n", opts.getCommandType());
        assertEquals("-23424", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(NumberToWordCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("534565"));
        assertEquals("--n", opts.getCommandType());
        assertEquals("534565", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(NumberToWordCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("79"));
        assertEquals("--n", opts.getCommandType());
        assertEquals("79", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(NumberToWordCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("--v -7567567567"));
        assertEquals("--v", opts.getCommandType());
        assertEquals("-7567567567", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(VerboseNumberToWordCommand.class));

        opts = Parser.INSTANCE.parse(Splitter.on(' ').omitEmptyStrings().split("79"));
        assertEquals("--v", opts.getCommandType());
        assertEquals("79", opts.getInput());
        assertThat(opts.getCommand(), instanceOf(VerboseNumberToWordCommand.class));
    }
}
