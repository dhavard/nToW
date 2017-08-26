package convert;

/**
 * Singleton Converter which will convert user integer input into English word equivalent using commas and hyphens.
 */
public class VerboseNumberToWordConverter extends NumberToWordConverter {

    // This class can't use the Java enum style singleton pattern because of the desire for the class to extend another
    public static VerboseNumberToWordConverter INSTANCE;

    static {
        INSTANCE = new VerboseNumberToWordConverter();
    }

    protected VerboseNumberToWordConverter() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected long parseNumber(String input) {
        input = input.replace(COMMA, EMPTY).replace(PERIOD, EMPTY).replace(SPACE, EMPTY);
        return super.parseNumber(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addPlacesSeparator(long number, int placeToCheck, StringBuilder sentence) {
        if (number > 0) {
            sentence.append(COMMA);  //.append(SPACE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addDigitsSeparator(int placeToCheck, StringBuilder sentence) {
        sentence.append(HYPHEN);
    }
}
