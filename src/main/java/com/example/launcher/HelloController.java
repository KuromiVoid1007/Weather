package com.example.launcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;
import javafx.application.Platform;


public class HelloController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text Pressure;

    @FXML
    private Text feels;

    @FXML
    private Button serch;


    @FXML
    private TextField serchCity;

    @FXML
    private Text temp;

    @FXML
    private Text tempMax;

    @FXML
    private Text tempMin;

    @FXML
    private Text textFeels;

    @FXML
    private Text textMax;

    @FXML
    private Text textMin;

    @FXML
    private Text textPressure;

    @FXML
    private Text textTemp;

    @FXML
    void initialize() {


        serch.setOnAction(event ->{
            String getUsersCity = serchCity.getText().trim();
            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUsersCity + "&appid=d200ca4300e0429570a918f9dc0283b7");

            if (!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);
                temp.setText("" + obj.getJSONObject("main").getDouble("temp"));
                feels.setText("" + obj.getJSONObject("main").getDouble("feels_like"));
                tempMax.setText("" + obj.getJSONObject("main").getDouble("temp_max"));
                tempMin.setText("" + obj.getJSONObject("main").getDouble("temp_min"));
                Pressure.setText("" + obj.getJSONObject("main").getDouble("pressure"));

                centerText(temp);
                centerText(feels);
                centerText(tempMax);
                centerText(tempMin);
                centerText(Pressure);
            }
        });
    }

    private void centerText(Text text) {
        // Привязываем `layoutX` элемента к ширине родителя
        if (text.layoutXProperty().isBound()) {
            text.layoutXProperty().unbind();
        }

        text.layoutXProperty().bind(
                rootPane.widthProperty().subtract(text.boundsInLocalProperty().get().getWidth()).divide(2)
        );
    }


    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("Такой город не найден");
        }
        return content.toString();
    }

}
