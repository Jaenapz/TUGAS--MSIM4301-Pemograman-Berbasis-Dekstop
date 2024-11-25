package Util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputOrder(String order){
        System.out.println("Pesanan  : ");
        String input = scanner.nextLine();
        return input;
    }
}
