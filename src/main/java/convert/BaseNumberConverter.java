package convert;

/**
 * Interface used as a container for static final attributes common among Converter classes which deal with numbers
 */
public interface BaseNumberConverter extends Converter {
    String NEGATIVE = "negative ";
    String AND = " and";
    String SPACE = " ";
    String COMMA = ",";
    String EMPTY = "";
    String PERIOD = ".";
    String HYPHEN = "-";

    String[] DIGITS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    String[] DIGITS_TENS = {"zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    String[] PLACES = {" hundred", " thousand", " million", " billion", " trillion", " quadrillion", " quintillion"};
}
