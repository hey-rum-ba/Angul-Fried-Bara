package com.example.foodorderapp;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSHelper {
    public String sendSms() {
        try {
            // Construct data
            String apiKey = "apikey=" + "NzI3MjM1NWEzMTRhNjk0NzZiNzAzNjU4Nzk3MTY3Nzk=";
            String message = "&message=" + "This is your message";
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + "917978638416";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error in SMS service "+e);
            return "Error "+e;
        }
    }
    StrictMode.ThreadPolicy st = new StrictMode.ThreadPolicy.Builder().permitAll().build();
}
