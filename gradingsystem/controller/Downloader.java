package edu.csustan.gradingsystem.controller;
import java.io.*;               //For input-output operations
import java.net.HttpURLConnection;  //For making a connection
import java.net.URL;            //Helps in making a URL object

public class Downloader {

    public static final void main(String[] args) throws Exception {

        //This is where to get the file and where to save it
        //for testing this is a flickr photo saved to project folder
        String site = "http://farm8.staticflickr.com/7443/10044360813_389f9f33c4_o_d.jpg";
        String filename = "testr2.jpg";

        try {
            //use URL object to connect to site
            URL url = new URL(site);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            float totalDataRead = 0;

            java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
            java.io.FileOutputStream fos = new java.io.FileOutputStream(filename);
            java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);

            byte[] data = new byte[1024];

            int i = 0;
            while ((i = in.read(data, 0, 1024)) >= 0) {
                totalDataRead = totalDataRead + i;
                bout.write(data, 0, i);
            }
            
            bout.close();
            in.close();
            
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}