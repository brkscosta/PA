/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRKsCosta
 */
public class Bot {

    private String start_url = "";

    public Bot(String string) {
        this.start_url = string;
    }

    public String start() throws MalformedURLException, IOException {

        String html = getHTML(this.start_url);
        System.out.println(html);
        return html;
    }

    private String getHTML(String start_url) throws MalformedURLException, IOException {
        URL obj = new URL(start_url);
        HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

        httpConnection.setRequestMethod("GET");

        httpConnection.setRequestProperty("User-Agent", "Bot/1.0");

        int responseCode = 0;
        try {
            responseCode = httpConnection.getResponseCode();
        } catch (IOException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (responseCode == 200) {

            StringBuffer response;
            try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(
                    httpConnection.getInputStream()))) {
                String responseLine;
                response = new StringBuffer();
                while ((responseLine = responseReader.readLine()) != null) {
                    response.append(responseLine).append("\n");
                }
            }

            // print result
            return response.toString();
        }
        return null;

    }

}
