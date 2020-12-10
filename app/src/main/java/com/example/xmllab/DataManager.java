package com.example.xmllab;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataManager {

    public static String getRateFromFLT(String currencyCode) throws IOException {
        String rate = "Data was not retrieved";
        InputStream stream = downloadUrl(Constants.FLT_URL);
        try {
            rate = XmlParser.getRateFromFLT(stream, currencyCode);
        }
        finally {
            if (stream != null) {
                stream.close();
            }
        }
        return rate;
    }

    private static InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
