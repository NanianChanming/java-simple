package com.nanian.simple.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CrawlerUtils {

    public static HttpURLConnection connect(String address) throws Exception {
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        // 建立实际的连接
        connection.connect();

        return connection;
    }


    public static ByteArrayOutputStream parseContent(HttpURLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //kb mb 缓存1M
        byte[] buffer = new byte[1024 * 1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        return outputStream;
    }

    public static void main(String[] args) throws Exception {
        // url
        HttpURLConnection connect = connect("");
        ByteArrayOutputStream outputStream = parseContent(connect);
        byte[] bytes = outputStream.toByteArray();
        // 文件写出路径
        FileOutputStream fileOutputStream = new FileOutputStream(new File(""));
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
