package ru.netology;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Хлеб", "Чипсы", "Рис"};
    static int[] prices = {50, 60, 100};

    static File saveFile = new File("basket.txt");

    static File saveLogFile = new File("log.csv");

    static File saveForJson = new File("basket.json");

    public static void main(String[] args) throws Exception {
        ClientLog clientLog = new ClientLog();
        Basket basket = null;
        //TODO проверка на наличие файла CSV
        if (!saveLogFile.exists()) {
            saveLogFile.createNewFile();
        }
        //TODO проверка на наличие файла Json
        if (saveForJson.exists()) {
            basket = Basket.loadFromJson(saveForJson);
        } else {
            basket = new Basket(products, prices);
        }
        clientLog.logStart();
        while (true) {
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + " " + products[i] + " " + prices[i] + " руб/штука");
            }
            System.out.println(" ");
            System.out.println("Введите номер товара и его количество через пробел или введите end");
            System.out.println(" ");
            String input = scanner.nextLine();
            if ("end".equals(input) || "End".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            int numberProducts = Integer.parseInt(parts[0]) - 1;
            int productCount = 1;
            if (parts.length > productCount) {
                basket.addToCart(numberProducts, Integer.parseInt(parts[1]));
                clientLog.log(numberProducts + 1, Integer.parseInt(parts[1]));
            } else {
                basket.addToCart(numberProducts, productCount);
                clientLog.log(numberProducts, productCount);
            }
        }
        basket.printCart();
        System.out.println("Сумма товаров в корзине: " + basket.getSum());
        clientLog.logEnd();
        basket.saveToJson(saveForJson);
        clientLog.exportAxCSV(saveLogFile);
    }
}