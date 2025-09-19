package com.brendanddev.githubnotify.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;
import java.io.FileInputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {

    private static final String BASE_URL = "https://api.github.com";
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final String key;


    public Client() {
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
        String tempKey = null;

        try {
            Properties props = new Properties();
            props.load(new FileInputStream(".env"));
            tempKey = props.getProperty("GITHUB_TOKEN");
        } catch (Exception e) {
            e.printStackTrace();
        }
        key = tempKey;
    }



    public void sendGET(String endpoint) throws Exception {
        if (key == null || key.isEmpty()) return;
        String fullUrl = BASE_URL + endpoint;

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(fullUrl))
            .header("Accept", "application/json")
            .header("Authorization", "token " + key)
            .build();
        
        HttpResponse<String> response = 
            client.send(request, BodyHandlers.ofString());

        JsonNode jsonNode = mapper.readTree(response.body());
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        System.out.println(prettyJson);
    }


    
}
