package com.brendanddev.githubnotify;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Client {

    private String uri = "https://api.github.com";
    private ObjectMapper mapper = new ObjectMapper();

    String key = System.getenv("GITHUB_TOKEN");






    public void sendGET(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("Accept", "application/json")
            .build();
        
        HttpResponse<String> response = 
            client.send(request, BodyHandlers.ofString());

        JsonNode jsonNode = mapper.readTree(response.body());
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        String prettyJson = writer.writeValueAsString(jsonNode);
        System.out.println(prettyJson);
    }


    
}
