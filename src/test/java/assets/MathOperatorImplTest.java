package assets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class mathOperatorImplTest {
    @Autowired
    mathOperatorImpl mathOperator;

    @Test
    public void shouldReturnExceptionIfImpossibleDivision ()  {
        InvalidOperationException exception = assertThrows(InvalidOperationException.class, () -> {
            mathOperator.doMath(1,0, "/");
        });

        String expectedMessage = "Cannot divide by 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldDivideOperands() throws InvalidOperationException {
        double result = mathOperator.doMath(10,5,"/");
        assertEquals(2, result);
    }

    @Test
    public void shouldAddOperands() throws InvalidOperationException {
        double result = mathOperator.doMath(2,4,"+");
        assertEquals(6, result);
    }

    @Test
    public void shouldLogOperands() throws InvalidOperationException {
        double result = mathOperator.doMath(2,2,"log");
        assertEquals(4, result);
    }

    @Test
    public void shouldLnOperands() throws InvalidOperationException {
        double result = mathOperator.doMath(2,4,"ln");
        assertEquals(8, result);
    }
}
