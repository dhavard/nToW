package convert;

import org.junit.Test;
import test.BaseApplicationTest;

import static org.junit.Assert.*;

public class NumberToWordConverterTest extends BaseApplicationTest {
    @Test
    public void convert_digits() {
        assertEquals("Zero", NumberToWordConverter.INSTANCE.convert("0"));
        assertEquals("One", NumberToWordConverter.INSTANCE.convert("1"));
        assertEquals("Thirteen", NumberToWordConverter.INSTANCE.convert("13"));
        assertEquals("Nineteen", NumberToWordConverter.INSTANCE.convert("19"));
    }

    @Test
    public void convert_negative() {
        assertEquals("Negative zero", NumberToWordConverter.INSTANCE.convert("-0"));
        assertEquals("Negative one", NumberToWordConverter.INSTANCE.convert("-1"));

    }

    @Test
    public void convert_tens() {
        assertEquals("Twenty", NumberToWordConverter.INSTANCE.convert("20"));
        assertEquals("Forty seven", NumberToWordConverter.INSTANCE.convert("47"));
        assertEquals("Eighty five", NumberToWordConverter.INSTANCE.convert("85"));
        assertEquals("Ninety nine", NumberToWordConverter.INSTANCE.convert("99"));
    }

    @Test
    public void convert_hundreds() {
        assertEquals("Four hundred", NumberToWordConverter.INSTANCE.convert("400"));
        assertEquals("Seven hundred and twelve", NumberToWordConverter.INSTANCE.convert("712"));
    }

    @Test
    public void convert_largeNumbers() {
        assertEquals("One thousand", NumberToWordConverter.INSTANCE.convert("1000"));
        assertEquals("Two thousand nine hundred and forty six", NumberToWordConverter.INSTANCE.convert("2946"));
        assertEquals("Five thousand two hundred and thirty seven", NumberToWordConverter.INSTANCE.convert("5237"));
        assertEquals("Three million two thousand nine hundred and forty six", NumberToWordConverter.INSTANCE.convert("3002946"));
        assertEquals("Seven trillion ten billion two hundred million two thousand nine hundred and forty six", NumberToWordConverter.INSTANCE.convert("7010200002946"));
    }

    @Test
    public void convert_maxMinValues() {
        assertEquals("Nine quintillion two hundred twenty three quadrillion three hundred seventy two trillion thirty six billion eight hundred fifty four million seven hundred seventy five thousand eight hundred and seven", NumberToWordConverter.INSTANCE.convert("9223372036854775807"));
        assertEquals("Negative nine quintillion two hundred twenty three quadrillion three hundred seventy two trillion thirty six billion eight hundred fifty four million seven hundred seventy five thousand eight hundred and seven", NumberToWordConverter.INSTANCE.convert("-9223372036854775807"));

        assertThrows(() -> NumberToWordConverter.INSTANCE.wordify("9223372036854775808"), IllegalArgumentException.class, NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG);
        assertThrows(() -> NumberToWordConverter.INSTANCE.wordify("-9223372036854775808"), IllegalArgumentException.class, NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG);

        assertEquals(NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG, NumberToWordConverter.INSTANCE.convert("9223372036854775808"));
        assertEquals(NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG, NumberToWordConverter.INSTANCE.convert("-9223372036854775808"));
    }

    @Test
    public void convert_leadingZeros() {
        assertEquals("Five thousand two hundred and thirty seven", NumberToWordConverter.INSTANCE.convert("000005237"));
        assertEquals("Negative five thousand two hundred and thirty seven", NumberToWordConverter.INSTANCE.convert("-000005237"));
        assertEquals("One thousand", NumberToWordConverter.INSTANCE.convert("00001000"));

    }

    @Test
    public void convert_radixPresent() {
        assertThrows(() -> NumberToWordConverter.INSTANCE.wordify("4,073,709,551,615"), IllegalArgumentException.class, NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG);
        assertThrows(() -> NumberToWordConverter.INSTANCE.wordify("4073709551615.00"), IllegalArgumentException.class, NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG);
        assertThrows(() -> NumberToWordConverter.INSTANCE.wordify("4 073 709 551 615"), IllegalArgumentException.class, NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG);
        assertThrows(() -> NumberToWordConverter.INSTANCE.wordify("4073709551615,00"), IllegalArgumentException.class, NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG);

        assertEquals(NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG, NumberToWordConverter.INSTANCE.convert("4,073,709,551,615"));
        assertEquals(NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG, NumberToWordConverter.INSTANCE.convert("4073709551615.00"));
        assertEquals(NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG, NumberToWordConverter.INSTANCE.convert("4 073 709 551 615"));
        assertEquals(NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG, NumberToWordConverter.INSTANCE.convert("4073709551615,00"));
    }
}