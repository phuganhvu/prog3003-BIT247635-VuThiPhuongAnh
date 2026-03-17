import java.util.HashMap;

public class Bai1_HashMap {
    public static void main(String[] args) {

        HashMap<Integer, String> employees = new HashMap<>();

        // Thêm 3 nhân viên
        employees.put(101, "Anna");
        employees.put(102, "Peter");
        employees.put(103, "Mary");

        // Lấy tên nhân viên ID 102
        System.out.println("Nhân viên ID 102: " + employees.get(102));

        // Kiểm tra ID 105
        if (!employees.containsKey(105)) {
            employees.put(105, "Unknown");
        }

        System.out.println("Danh sách nhân viên: " + employees);
    }
}