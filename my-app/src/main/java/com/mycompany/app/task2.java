package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class task2 {
    public static void Ip() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ivank\\Desktop\\testing\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://api.ipify.org/?format=json");

            WebElement preElement = driver.findElement(By.tagName("pre"));
            String jsonResponse = preElement.getText();
            System.out.println("Получен JSON: " + jsonResponse);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);

            String ipAddress = (String) jsonObject.get("ip");
            System.out.println("\nВаш IP-адрес: " + ipAddress);

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}