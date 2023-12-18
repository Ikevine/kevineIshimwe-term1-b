package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import dto.doMathRequest;
import controller.mathController;
import assets.mathOperator;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)

public class DoMathUnitTesting {

    private MockMvc mockMvc;

    @Mock
    private mathOperator mathOperator;

    @InjectMocks
    private mathController mathController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    public void testDoMathEndpoint() throws Exception {
        // Given
        doMathRequest request = new doMathRequest(5, 4, "*");
        double expectedResult = 20.0;

        // Mocking the behavior of the mathOperator
        when(mathOperator.doMath(request.getOperand1(), request.getOperand2(), request.getOperation()))
                .thenReturn(expectedResult);

        // When
        mockMvc.perform(post("/api/doMath")
                        .content("{\"operand1\": 5, \"operand2\": 4, \"operation\": \"*\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calcResponse").value(expectedResult));

        // Then
        // Verify that mathOperator.doMath method was called with the correct arguments
        verify(mathOperator, times(1)).doMath(request.getOperand1(), request.getOperand2(), request.getOperation());
        // Verify that there are no more interactions with mathOperator
        verifyNoMoreInteractions(mathOperator);
    }
}
