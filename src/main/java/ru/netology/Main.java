package ru.netology;

import java.io.File;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Хлеб", "Чипсы", "Рис"};
    static int[] prices = {50, 60, 100};

    static File saveFile = new File("basket.txt");

    static File saveLogFile = new File("log.csv");

    static File saveForJson = new File("basket.json");

    static File settingXML = new File("shop.xml");

    public static void main(String[] args) throws Exception {
        ClientLog clientLog = new ClientLog();
        XMLReader xmlReader = new XMLReader();
        xmlReader.reader(new File("shop.xml"));
        Basket basket = loadFromXmlFile(xmlReader.loadEnabled, xmlReader.loadStringFileName, xmlReader.loadStringFormat);
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
        //TODO проверка нужно ли сохранять корзину.
        if (xmlReader.saveEnabled.equals("true") || xmlReader.saveEnabled.equals("True")) {
            switch (xmlReader.saveStringFormat) {
                case ("json"):
                    basket.saveToJson(new File(xmlReader.saveFile));
                    break;
                case ("txt"):
                    basket.saveTxt(new File(xmlReader.saveFile));
                    break;
            }
        }
        //TODO проверка нужно ли сохранять логи
        if (xmlReader.logEnabled.equals("true") || xmlReader.logEnabled.equals("True")) {
            clientLog.exportAxCSV(new File(xmlReader.logStringFileName));
        }
        clientLog.exportAxCSV(saveLogFile);
    }

    public static Basket loadFromXmlFile(String enabled, String fileName, String format) throws Exception {
        Basket basket = null;
        if (enabled.equals("true") || enabled.equals("True")) {
            switch (format) {
                case ("json"):
                    basket = Basket.loadFromJson(new File(fileName));
                    break;
                case ("txt"):
                    basket = Basket.loadFromTxtFile(new File(fileName));
                    break;
            }
        } else {
            basket = new Basket(products, prices);
        }
        return basket;
    }
}