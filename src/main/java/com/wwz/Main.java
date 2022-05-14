package com.wwz;

public class Main {

    public static void main(String[] args) {
        Client client = Client.INSTANCE;
        client.start();
        System.out.println("start...");
    }

}
