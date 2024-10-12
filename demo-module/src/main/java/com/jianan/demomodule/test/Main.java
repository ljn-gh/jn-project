package com.jianan.demomodule.test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest requert = HttpRequest.newBuilder()
                .GET().uri(URI.create("https://ug.baidu.com/mcp/pc/pcsearch")).build();
        try {
            HttpResponse<String> response = httpClient.send(requert, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("11"+"22222");
    }
}
