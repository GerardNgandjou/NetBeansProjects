/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.weatherappgui;

/**
 *
 * @author reli
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import org.json.JSONObject;

public class WeatherAppGUI extends JFrame {
    private JTextField cityField;
    private JTextArea weatherArea;
    private JButton fetchButton;
    private final String API_KEY = "656ab14b2b5ed338e9b6d2a2d650910b";
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public WeatherAppGUI() {
        setTitle("Weather App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Input Panel
        JPanel inputPanel = new JPanel();
        cityField = new JTextField(20);
        fetchButton = new JButton("Get Weather");
        inputPanel.add(new JLabel("City:"));
        inputPanel.add(cityField);
        inputPanel.add(fetchButton);
        
        // Weather Display
        weatherArea = new JTextArea();
        weatherArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(weatherArea);
        
        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Button action
        fetchButton.addActionListener(this::fetchWeather);
    }

    private void fetchWeather(ActionEvent e) {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String weatherData = getWeatherData(city);
            displayWeather(weatherData);
        } catch (Exception ex) {
            weatherArea.setText("Error fetching weather data: " + ex.getMessage());
        }
    }

    private String getWeatherData(String city) throws Exception {
        String urlString = String.format(API_URL, city, API_KEY);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        
        in.close();
        conn.disconnect();
        
        return content.toString();
    }

    private void displayWeather(String jsonData) {
        JSONObject obj = new JSONObject(jsonData);
        JSONObject main = obj.getJSONObject("main");
        JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);
        
        String weatherText = String.format(
            "Weather in %s:\n\n" +
            "Temperature: %.1f°C\n" +
            "Feels like: %.1f°C\n" +
            "Humidity: %d%%\n" +
            "Conditions: %s\n" +
            "Wind Speed: %.1f m/s",
            obj.getString("name"),
            main.getDouble("temp"),
            main.getDouble("feels_like"),
            main.getInt("humidity"),
            weather.getString("description"),
            obj.getJSONObject("wind").getDouble("speed")
        );
        
        weatherArea.setText(weatherText);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGUI app = new WeatherAppGUI();
            app.setVisible(true);
        });
    }
}
