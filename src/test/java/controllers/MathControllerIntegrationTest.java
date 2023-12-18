package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevine.dto.DoMathRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDoMathEndpointMultiplication() throws Exception {
        DoMathRequest request = new DoMathRequest(5, 4, "*");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/doMath")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.calcResponse").value(20));
    }

    @Test
    public void testDoMathEndpointDivision() throws Exception {
        DoMathRequest request = new DoMathRequest(10, 2, "/");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/doMath")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.calcResponse").value(5));
    }

    @Test
    public void testDoMathEndpointAddition() throws Exception {
        DoMathRequest request = new DoMathRequest(3, 7, "+");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/doMath")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.calcResponse").value(10));
    }

    @Test
    public void testDoMathEndpointSubtraction() throws Exception {
        DoMathRequest request = new DoMathRequest(8, 4, "-");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/doMath")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.calcResponse").value(4));
    }

    // Add more test cases for other operations and edge cases as needed
}
