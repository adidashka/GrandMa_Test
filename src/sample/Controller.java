package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonTask4;

    @FXML
    private Button buttonTask1;

    @FXML
    private Button buttonTask2;

    @FXML
    private Button buttonTask3;

    @FXML
    private TextField textN;

    @FXML
    private TextField textFrameWidth;

    @FXML
    private TextField textFrameHeight;

    @FXML
    private TextField textPicWidth;

    @FXML
    private TextField textPicHeight;

    @FXML
    private TextField textPicX;

    @FXML
    private TextField textPicY;

    @FXML
    private TextField textDollars;

    @FXML
    private TextArea textRes;

    @FXML
    private Button buttonClean;

    @FXML
    void initialize() {
        buttonTask1.setOnAction(event -> {
            try {
                int n = Integer.parseInt(textN.getText().trim());
                Task1 task1 = new Task1(n);
                String s = "Исходный массив:\n";
                s = s + task1.printArrayToString();
                task1.rotation90();
                s = s + "\nМассив перевенрнутый на 90 градусов:\n";
                s = s + task1.printArrayToString();
                textRes.setText(s);
            }
            catch (Exception e){
                textRes.setText("Введено не корректное значение, введите целое число");
            }
        });

        buttonTask2.setOnAction(event -> {
            try {
                int frameWidth = Integer.parseInt(textFrameWidth.getText().trim());
                int frameHeight = Integer.parseInt(textFrameHeight.getText().trim());
                int picWidth = Integer.parseInt(textPicWidth.getText().trim());
                int picHeight = Integer.parseInt(textPicHeight.getText().trim());
                int picX = Integer.parseInt(textPicX.getText().trim());
                int picY = Integer.parseInt(textPicY.getText().trim());

                if ((frameWidth>=picWidth+picX)& (frameHeight>=picHeight+picY)) {
                    Task2 task2 = new Task2(frameWidth, frameHeight, picWidth, picHeight, picX, picY);
                    String s = "Исходный массив:\n";
                    s = s + task2.printArrayToString();
                    task2.imageShift();
                    s = s + "\nПолученный массив:\n";
                    s = s + task2.printArrayToString();
                    textRes.setText(s);
                }
                else textRes.setText("Введено не корректное значение (сумма pic_width и pic_x не должна превышать frame_width, \n" +
                        "а также сумма pic_height и pic_y не должна превышать frame_height)");
            }
            catch (Exception e){
                textRes.setText("Введено не корректное значение, введите целые числа");
            }

        });

        buttonTask3.setOnAction(event -> {
            try {
                double dollar = Double.parseDouble(textDollars.getText().trim());
                Task3 task3 = new Task3(dollar);
                String s = task3.moneyToString();
                textRes.setText(s);
            }
            catch (Exception e){
                textRes.setText("Введено не корректное значение, введите число");
            }


        });

        buttonTask4.setOnAction(event -> {
            try {
                Task4 task4 = new Task4();
                task4.calculateTests();
                textRes.setText("Задание выполнено, результат в файле Result.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonClean.setOnAction(event -> {
            textRes.clear();
        });
    }

}
