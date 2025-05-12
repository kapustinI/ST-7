package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class task3 {
    public static void Weather() {
        try {
            String apiUrl = "https://api.open-meteo.com/v1/forecast?" +
                    "latitude=56&longitude=44&" +
                    "hourly=temperature_2m,rain&" +
                    "current=cloud_cover&" +
                    "timezone=Europe/Moscow&" +
                    "forecast_days=1&" +
                    "wind_speed_unit=ms";

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            StringBuilder response = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());

            JSONObject hourly = (JSONObject) jsonResponse.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            System.out.println("Прогноз погоды в Нижнем Новгороде на 24 часа:");
            System.out.println("------------------------------------------------");
            System.out.printf("%-3s %-16s %-12s %s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
            System.out.println("------------------------------------------------");


            for (int i = 0; i < times.size(); i++) {
                String time = ((String) times.get(i)).substring(11);
                double temp = (double) temperatures.get(i);
                double rain = (double) rains.get(i);

                System.out.printf("%-3d %-16s %-12.1f %.2f%n",
                        i + 1, time, temp, rain);
            }

        } catch (Exception e) {
            System.err.println("Ошибка при получении данных: " + e.getMessage());
        }
    }
}