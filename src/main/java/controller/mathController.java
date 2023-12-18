package controller;

@RestController
@RequestMapping("/api")
public class mathController {

    @Autowired
    private MathOperator mathOperator;

    @PostMapping("/doMath")
    public ResponseEntity<Map<String, Double>> doMath(@RequestBody DoMathRequest request) {
        try {
            double result = mathOperator.doMath(request.getOperand1(), request.getOperand2(), request.getOperation());
            Map<String, Double> response = Collections.singletonMap("calcResponse", result);
            return ResponseEntity.ok(response);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Internal Server Error"));
        }
    }
}

