package convert;

import org.junit.Test;
import test.BaseApplicationTest;

import static org.junit.Assert.assertEquals;

public class VerboseNumberToWordConverterTest extends BaseApplicationTest {

    @Test
    public void convert_digits() {
        assertEquals("Zero", VerboseNumberToWordConverter.INSTANCE.convert("0"));
        assertEquals("One", VerboseNumberToWordConverter.INSTANCE.convert("1"));
        assertEquals("Thirteen", VerboseNumberToWordConverter.INSTANCE.convert("13"));
        assertEquals("Nineteen", VerboseNumberToWordConverter.INSTANCE.convert("19"));
    }

    @Test
    public void convert_negative() {
        assertEquals("Negative zero", VerboseNumberToWordConverter.INSTANCE.convert("-0"));
        assertEquals("Negative one", VerboseNumberToWordConverter.INSTANCE.convert("-1"));

    }

    @Test
    public void convert_tens() {
        assertEquals("Twenty", VerboseNumberToWordConverter.INSTANCE.convert("20"));
        assertEquals("Forty-seven", VerboseNumberToWordConverter.INSTANCE.convert("47"));
        assertEquals("Eighty-five", VerboseNumberToWordConverter.INSTANCE.convert("85"));
        assertEquals("Ninety-nine", VerboseNumberToWordConverter.INSTANCE.convert("99"));
    }

    @Test
    public void convert_hundreds() {
        assertEquals("Four hundred", VerboseNumberToWordConverter.INSTANCE.convert("400"));
        assertEquals("Seven hundred and twelve", VerboseNumberToWordConverter.INSTANCE.convert("712"));
    }

    @Test
    public void convert_largeNumbers() {
        assertEquals("One thousand", VerboseNumberToWordConverter.INSTANCE.convert("1000"));
        assertEquals("Two thousand, nine hundred and forty-six", VerboseNumberToWordConverter.INSTANCE.convert("2946"));
        assertEquals("Five thousand, two hundred and thirty-seven", VerboseNumberToWordConverter.INSTANCE.convert("5237"));
        assertEquals("Three million, two thousand, nine hundred and forty-six", VerboseNumberToWordConverter.INSTANCE.convert("3002946"));
        assertEquals("Seven trillion, ten billion, two hundred million, two thousand, nine hundred and forty-six", VerboseNumberToWordConverter.INSTANCE.convert("7010200002946"));
    }

    @Test
    public void convert_maxMinValues() {
        assertEquals("Nine quintillion, two hundred twenty-three quadrillion, three hundred seventy-two trillion, thirty-six billion, eight hundred fifty-four million, seven hundred seventy-five thousand, eight hundred and seven", VerboseNumberToWordConverter.INSTANCE.convert("9223372036854775807"));
        assertEquals("Negative nine quintillion, two hundred twenty-three quadrillion, three hundred seventy-two trillion, thirty-six billion, eight hundred fifty-four million, seven hundred seventy-five thousand, eight hundred and seven", VerboseNumberToWordConverter.INSTANCE.convert("-9223372036854775807"));

        assertThrows(() -> VerboseNumberToWordConverter.INSTANCE.wordify("9223372036854775808"), IllegalArgumentException.class, VerboseNumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG);
        assertThrows(() -> VerboseNumberToWordConverter.INSTANCE.wordify("-9223372036854775808"), IllegalArgumentException.class, VerboseNumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG);

        assertEquals(NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG, VerboseNumberToWordConverter.INSTANCE.convert("9223372036854775808"));
        assertEquals(NumberToWordConverter.OUT_OF_BOUNDS_ERROR_MSG, VerboseNumberToWordConverter.INSTANCE.convert("-9223372036854775808"));
    }

    @Test
    public void convert_leadingZeros() {
        assertEquals("Five thousand, two hundred and thirty-seven", VerboseNumberToWordConverter.INSTANCE.convert("000005237"));
        assertEquals("Negative five thousand, two hundred and thirty-seven", VerboseNumberToWordConverter.INSTANCE.convert("-000005237"));
        assertEquals("One thousand", VerboseNumberToWordConverter.INSTANCE.convert("00001000"));

    }

    @Test
    public void convert_radixPresent() {
        assertEquals("Four trillion, seventy-three billion, seven hundred nine million, five hundred fifty-one thousand, six hundred and fifteen", VerboseNumberToWordConverter.INSTANCE.wordify("4,073,709,551,615"));
        assertEquals("Four hundred seven trillion, three hundred seventy billion, nine hundred fifty-five million, one hundred sixty-one thousand, five hundred", VerboseNumberToWordConverter.INSTANCE.wordify("4073709551615.00"));
        assertEquals("Four trillion, seventy-three billion, seven hundred nine million, five hundred fifty-one thousand, six hundred and fifteen", VerboseNumberToWordConverter.INSTANCE.wordify("4 073 709 551 615"));
        assertEquals("Four hundred seven trillion, three hundred seventy billion, nine hundred fifty-five million, one hundred sixty-one thousand, five hundred", VerboseNumberToWordConverter.INSTANCE.wordify("4073709551615,00"));
    }
}