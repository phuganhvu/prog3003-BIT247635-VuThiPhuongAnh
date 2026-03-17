import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SumCalculator extends Application {

    @Override
    public void start(Stage stage) {

        TextField num1 = new TextField();
        TextField num2 = new TextField();
        Label result = new Label("Kết quả");
        Button btn = new Button("Tính tổng");

        btn.setOnAction(e -> {
            try {
                double a = Double.parseDouble(num1.getText());
                double b = Double.parseDouble(num2.getText());
                result.setText("Kết quả: " + (a + b));
            } catch (Exception ex) {
                result.setText("Lỗi!");
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Số thứ nhất:"), 0, 0);
        grid.add(num1, 1, 0);

        grid.add(new Label("Số thứ hai:"), 0, 1);
        grid.add(num2, 1, 1);

        grid.add(btn, 1, 2);
        grid.add(result, 1, 3);

        Scene scene = new Scene(grid, 300, 200);

        stage.setTitle("Máy tính cộng");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}