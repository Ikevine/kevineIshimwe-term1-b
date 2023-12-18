package controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.kevine.dto.DoMathRequest;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class DoMathIntegrationTesting {

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void testDoMathEndpoint() {
        // Given
        DoMathRequest request = new DoMathRequest(5, 4, "*");

        // When
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:"  + "/api/doMath", request, String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("calcResponse:20.0");
    }
}
