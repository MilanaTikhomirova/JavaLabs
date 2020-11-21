package com.company;

import java.io.*;


public class Ward {
    private int badsAmount;
    private String surname;
    private boolean isBadFree;

    public Ward(){ }

    public Ward(int badsAmount, String surname, boolean isBadFree) {
        this.badsAmount = badsAmount;
        this.surname = surname;
        this.isBadFree = isBadFree;
    }

    public Ward(Ward ward){
        this.badsAmount = ward.badsAmount;
        this.isBadFree = ward.isBadFree;
        this.surname = ward.surname;
    }
    public void initFromConsole() throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "Utf-8"));
        System.out.println("Фамилия");
        this.surname = reader.readLine();

        System.out.println("Количество кроватей");
        this.badsAmount = Integer.parseInt(reader.readLine());

        System.out.println("Есть ли свободные кровати");
        String isBadFree = reader.readLine();

        if("Да".equals(isBadFree)){
            this.isBadFree = true;
        }
        else
        if("Нет".equals(isBadFree)){
            this.isBadFree = false;
        }
        else System.out.println("Error");

    }

    public int getBadsAmount() {
        return badsAmount;
    }

    public void setBadsAmount(int badsAmount) {
        this.badsAmount = badsAmount;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isBadOpen() {
        return isBadFree;
    }

    public void setBadOpen(boolean isBadFree) {
        this.isBadFree = isBadFree;
    }

    public static int getOverageAmountOfBeds(Ward[] wards){
        int amount = 0;
        for (int i = 0; i<wards.length; i++) {
            amount= amount + wards[i].badsAmount;
        }
        return amount;
    }

    public static int getWardsWithFreeBeds(Ward[] wards){
        int amount = 0;
        for(int i = 0; i < wards.length; i++){
            if(wards[i].isBadFree)
                ++amount;
        }
        return amount;
    }
    public String printData()
    {
        String output = "\nКоличество кроватей - " + this.badsAmount + '\n'
                + "Фамилия врача - " + this.surname + '\n' + "Есть ли свободные места - " + this.isBadFree + '\n';

        return output;
    }
    public void writeToFile(String fileName) throws IOException{
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file, true);
        writer.append(printData());
        writer.flush();
        writer.close();
    }
    public static void readFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        int flag;
        char[] buffer = new char[1];
        FileReader reader = new FileReader(file);
        do {
            flag = reader.read(buffer);
            System.out.print(buffer[0]);
        }while (flag == 1);
        reader.close();
    }
}
