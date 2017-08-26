package test;

import com.github.stefanbirkner.fishbowl.Statement;

import static com.github.stefanbirkner.fishbowl.Fishbowl.exceptionThrownBy;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BaseApplicationTest {
    protected void assertThrows(Statement statement, Class clazz, String message) {
        Throwable exception = exceptionThrownBy(statement);
        assertThat(exception, is(instanceOf(clazz)));
        assertEquals(exception.getMessage(), message);
    }
}
