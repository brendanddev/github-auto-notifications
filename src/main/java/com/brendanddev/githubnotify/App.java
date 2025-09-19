package com.brendanddev.githubnotify;

import com.brendanddev.githubnotify.client.Client;

public class App {
    
    public static void main(String[] args) {

        Client client = new Client();

        try {
            client.sendGET("/user");
        } catch (Exception e) {
            System.out.println("Error occurred!");
        }


    }



}