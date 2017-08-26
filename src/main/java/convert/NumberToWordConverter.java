package convert;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Singleton Converter which will convert user integer input into English word equivalent.
 */
public class NumberToWordConverter implements BaseNumberConverter {

    // This class can't use the Java enum style singleton pattern because of the desire for the class to be extended
    public static NumberToWordConverter INSTANCE;

    static {
        INSTANCE = new NumberToWordConverter();
    }

    protected NumberToWordConverter() {
    }

    public static final String OUT_OF_BOUNDS_ERROR_MSG = "Input must be an integer between -2^63 and 2^63 exclusively.";

    /**
     * Convert user integer input into English word equivalent.
     * {@inheritDoc}
     */
    @Override
    public String convert(String input) {
        try {
            return wordify(input);
        } catch (IllegalArgumentException iae) {
            // If a relatively normal exception occured, inform the user of it in a less damaging way.
            return iae.getMessage();
        }
    }

    /**
     * Validate that the input is able to be converted and return a representative long if able.
     *
     * @param input The String input to validate
     * @return a long representing the input
     */
    protected long validateInput(String input) {
        long number = 0;
        if (input.length() < 1) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS_ERROR_MSG);
        }

        number = parseNumber(input);

        return number;
    }

    /**
     * Parse the number in order to convert the input into a representative long.
     *
     * @param input The String input to parse
     * @return a long representing the input
     */
    protected long parseNumber(String input) {
        long number;
        if (!NumberUtils.isDigits(input)) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS_ERROR_MSG);
        } else {
            try {
                number = Long.parseLong(input);
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException(OUT_OF_BOUNDS_ERROR_MSG);
            }
        }
        return number;
    }

    /**
     * Convert anlong in String form into a String contain English word equivalent of the input.
     *
     * @param input The integer in String form to convert to words
     * @return The English word equivalent of the input
     */
    protected String wordify(String input) {
        boolean isNegative = false;
        if (input.startsWith(HYPHEN)) {
            input = input.substring(1);
            isNegative = true;
        }

        long number = validateInput(input);
        StringBuilder sentence = new StringBuilder();
        wordify(number, sentence);

        if (isNegative) {
            sentence.insert(0, NEGATIVE);
        }

        return StringUtils.capitalize(sentence.toString());
    }


    /**
     * Convert a long into a String contain English word equivalent of the input.
     *
     * @param number   The long to convert to words
     * @param sentence The StringBuilder containing the converted String so far
     * @return The English word equivalent of the input
     */
    protected void wordify(long number, StringBuilder sentence) {
        if (number == 0) {
            sentence.append(DIGITS[0]);
            return;
        }

        wordify(Math.abs(number), PLACES.length - 1, sentence);
    }

    /**
     * Recursively convert a long into a String contain English word equivalent of the input.
     *
     * @param number   The long to convert to words
     * @param placeToCheck The power of 1000 corresponding to the order of magnitude of number to convert this pass
     * @param sentence The StringBuilder containing the converted String so far
     * @return The English word equivalent of the input
     */
    protected void wordify(long number, int placeToCheck, StringBuilder sentence) {
        // Handle the thousands, millions, billions, etc places
        if (placeToCheck > 0) {
            long unit = (long) Math.pow(1000, placeToCheck);
            long value = number / unit;
            long remainder = number % unit;

            if (value > 0) {
                wordifyDigits((int) value, placeToCheck, sentence);
                sentence.append(PLACES[placeToCheck]);
                addPlacesSeparator(remainder, placeToCheck, sentence);
            }

            wordify(remainder, placeToCheck - 1, sentence);
        } else {
            wordifyDigits((int) number, placeToCheck, sentence);
        }
    }

    /**
     * Recursively convert a long into a String contain English word equivalent of the input as if the converted number parts were 999 or less.
     *
     * @param number   The long to convert to words
     * @param placeToCheck The power of 1000 corresponding to the order of magnitude of number to convert this pass
     * @param sentence The StringBuilder containing the converted String so far
     * @return The English word equivalent of the input
     */
    protected void wordifyDigits(int number, int placeToCheck, StringBuilder sentence) {
        int unit = 100;
        int value = number / unit;

        // Handle the hundreds place
        if (value > 0) {
            wordifyDigits(value, 1, sentence);
            sentence.append(PLACES[0]);
            number %= unit;
        }

        // Handle the tens & ones place
        if (number > 0) {
            addHundredSeparator(placeToCheck, sentence);

            if (number < 20) {
                sentence.append(DIGITS[number]);
            } else {
                sentence.append(DIGITS_TENS[number / 10]);
                int remainder = number % 10;
                if (remainder > 0) {
                    addDigitsSeparator(placeToCheck, sentence);
                    sentence.append(DIGITS[remainder]);
                }
            }
        }
    }

    /**
     * Add an English word separator after the order of magnitude word (e.g. million, thousand).
     * @param number   The long to convert to words
     * @param placeToCheck The power of 1000 corresponding to the order of magnitude of number to convert this pass
     * @param sentence The StringBuilder containing the converted String so far
     */
    protected void addPlacesSeparator(long number, int placeToCheck, StringBuilder sentence) {
        //NO-OP left open for extension
    }


    /**
     * Add an English word separator after the order of magnitude word 'hundred'.
     * @param placeToCheck The power of 1000 corresponding to the order of magnitude of number to convert this pass
     * @param sentence The StringBuilder containing the converted String so far
     */
    protected void addHundredSeparator(int placeToCheck, StringBuilder sentence) {
        if (sentence.length() > 0) {
            if (placeToCheck == 0) {
                sentence.append(AND);
            }
            sentence.append(SPACE);
        }
    }


    /**
     * Add an English word separator between the tens digit and the single digit (e.g. insert a space between 'Eighty five').
     * @param placeToCheck The power of 1000 corresponding to the order of magnitude of number to convert this pass
     * @param sentence The StringBuilder containing the converted String so far
     */
    protected void addDigitsSeparator(int placeToCheck, StringBuilder sentence) {
        sentence.append(SPACE);
    }
}
