/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.net.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author BRKsCosta
 */
public class Bot {

    private String start_url = "";

    public Bot(String string) {
        this.start_url = string;
    }

    public void start() {

        String html = getHTML(this.start_url);
        System.out.println(html);
    }

    private String getHTML(String start_url) {

        String html = "";
        String line = "";

        try {
            URL url = new URL(start_url);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Bot/1.0");
            connection.setRequestProperty("Accpet-Charset", "UTF-8");

            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            while ((line = reader.readLine()) != null) {
                html += line + "\n";
            }
            html = html.trim();
            return html;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}
