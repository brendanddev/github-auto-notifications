package com.brendanddev.githubnotify;



public class App {

    public static void main(String[] args) {

        Client client = new Client();

        try {
            client.sendGET("https://api.github.com");
        } catch (Exception e) {
            System.out.println("Error occurred!");
        }


    }



}