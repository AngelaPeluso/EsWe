package com.soprasteria;

import java.io.BufferedReader;
import java.io.IOException;

public class Utils {

    public static String insert(BufferedReader in, String whatInsert) throws IOException {
        String toReturn;
        boolean flag;
        do {
            System.out.print("Inserisci " + whatInsert + ": ");
            toReturn = in.readLine();
            flag = "".equals(toReturn);
            if (flag) {
                System.out.println("Inserisci " + whatInsert + "!");
            }
        } while (flag);
        return toReturn;
    }

    public static int parseNumber(BufferedReader in) throws IOException {
        int toReturn = 0;
        boolean flag;
        do {
            try {
                toReturn = Integer.parseInt(in.readLine());
                if (toReturn >= 0) {
                    flag = false;
                } else {
                    flag = true;
                    System.out.println("Inserisci un numero positivo!");
                }
            } catch (NumberFormatException ex) {
                flag = true;
                System.out.println("Inserisici un numero positivo!");
            }
        } while (flag);
        return toReturn;
    }
}
