

@FunctionalInterface
interface MathOperation {
    int compute(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        // Phép cộng
        MathOperation addition = (a, b) -> a + b;
        
        // Phép trừ
        MathOperation subtraction = (a, b) -> a - b;
        
        // Phép nhân
        MathOperation multiplication = (a, b) -> a * b;
        
        // Phép chia
        MathOperation division = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Không thể chia cho 0");
            }
            return a / b;
        };

        int a = 10;
        int b = 5;

        System.out.println("Kết quả phép tính với a = " + a + ", b = " + b + ":");
        System.out.println("Cộng: " + addition.compute(a, b));
        System.out.println("Trừ: " + subtraction.compute(a, b));
        System.out.println("Nhân: " + multiplication.compute(a, b));
        System.out.println("Chia: " + division.compute(a, b));
    }
}
