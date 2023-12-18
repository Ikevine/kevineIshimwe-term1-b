package dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class doMathRequest {
    private double operand1;
    private double operand2;
    private String operation;
}

