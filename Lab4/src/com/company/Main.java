package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Ward[] ward = new Ward[2];
        for(int i=0;i<2;i++){
            ward[i] = new Ward();
            ward[i].initFromConsole();
            ward[i].writeToFile("data.txt");
        }
        System.out.println("Readed from file");
        Ward.readFromFile("data.txt");

    }
}
