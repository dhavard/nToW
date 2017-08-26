package command;

/**
 * Interface of Generic type used in Command Pattern
 */
public interface Command<T> {

    /**
     * Execute the Command.
     *
     * @param arg The generically typed argument used to supply execution parameters
     */
    void execute(T arg);

    /**
     * Determine if the Command supports the execution parameters.
     *
     * @param arg The generically typed argument used to supply execution parameters
     * @return a boolean indicating support status
     */
    boolean supports(T arg);
}
