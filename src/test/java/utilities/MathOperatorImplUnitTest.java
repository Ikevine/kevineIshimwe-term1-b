package utilities;

import com.kevine.utilities.InvalidOperationException;
import com.kevine.utilities.MathOperatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MathOperatorImplUnitTest {

    @Test
    public void shouldReturnExceptionIfImpossibleDivision() {
        MathOperatorImpl mathOperator = new MathOperatorImpl();
        assertThrows(InvalidOperationException.class, () -> mathOperator.doMath(1, 0, "/"));
    }

    @Test
    public void shouldDivideOperands() throws InvalidOperationException {
        MathOperatorImpl mathOperator = new MathOperatorImpl();
        double result = mathOperator.doMath(10, 5, "/");
        assertEquals(2, result);
    }

    @Test
    public void shouldAddOperands() throws InvalidOperationException {
        MathOperatorImpl mathOperator = new MathOperatorImpl();
        double result = mathOperator.doMath(2, 4, "+");
        assertEquals(6, result);
    }

    @Test
    public void shouldLogOperands() throws InvalidOperationException {
        // Mocking
        MathOperatorImpl mathOperator = mock(MathOperatorImpl.class);
        when(mathOperator.doMath(2, 2, "log")).thenReturn(4.0);

        // Act
        double result = mathOperator.doMath(2, 2, "log");

        // Assert
        assertEquals(16, result);
    }
}