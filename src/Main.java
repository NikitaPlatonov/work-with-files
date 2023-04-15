import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Хлеб", "Чипсы", "Рис"};
    static int[] prices = {50, 60, 100};
    static File saveBin = new File("basket.bin");


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Basket basket = null;
        if (saveBin.exists()) {
            basket = Basket.loadFromBinFile(saveBin);
        } else {
            basket = new Basket(products, prices);
        }
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
                basket.addToCart(numberProducts, Integer.parseInt(parts[1]) - 1);
            }
            basket.addToCart(numberProducts, productCount);
        }
        basket.printCart();
        System.out.println("Сумма товаров в корзине: " + basket.getSum());
        basket.saveBin(saveBin);
    }
}