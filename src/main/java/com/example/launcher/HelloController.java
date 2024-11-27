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
    private Text pressure;

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

                // Преобразование Кельвинов в Цельсии
                double tempInCelsius = obj.getJSONObject("main").getDouble("temp") - 273.15;
                double feelsLikeInCelsius = obj.getJSONObject("main").getDouble("feels_like") - 273.15;
                double tempMaxInCelsius = obj.getJSONObject("main").getDouble("temp_max") - 273.15;
                double tempMinInCelsius = obj.getJSONObject("main").getDouble("temp_min") - 273.15;

                temp.setText(String.format("%.1f °C", tempInCelsius));
                feels.setText(String.format("%.1f °C", feelsLikeInCelsius));
                tempMax.setText(String.format("%.1f °C", tempMaxInCelsius));
                tempMin.setText(String.format("%.1f °C", tempMinInCelsius));
                pressure.setText("" + obj.getJSONObject("main").getDouble("pressure") + " hPa");

            }
        });
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
