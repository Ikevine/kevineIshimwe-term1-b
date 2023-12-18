package com.kevine.controller;
import com.kevine.assets.InvalidOperationException;
import com.kevine.dto.DoMathRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kevine.assets.MathOperator;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MathController {

    @Autowired
    private MathOperator mathOperator;

    @PostMapping("/doMath")
    public ResponseEntity<Map<String, Double>> doMath(@RequestBody DoMathRequest request) {
        try {
            double result = mathOperator.doMath(request.getOperand1(), request.getOperand2(), request.getOperation());
            Map<String, Double> response = Collections.singletonMap("calcResponse", result);
            return ResponseEntity.ok(response);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.<String, Double>singletonMap("error", Double.valueOf(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.<String, Double>singletonMap("error", Double.valueOf("Internal Server Error")));
        }
    }
}

