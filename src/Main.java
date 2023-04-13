import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб ", "Молоко ", "Рис"};
        int[] prices = {50, 60, 100};
        int[] cntProduct = new int[3];
        int sum = 0;
        Basket basket = null;
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
                productCount += Integer.parseInt(parts[1]) - 1;
            }
            cntProduct[numberProducts] += productCount;
            sum += prices[numberProducts] * productCount;
            basket.addToCart(numberProducts, productCount);
        }
        File file = new File("basket.txt");
        basket.saveTxt(file);
        System.out.println("Ваша корзина: ");
        for (int i = 0; products.length > i; i++) {
            if (cntProduct[i] > 0) {
                System.out.println("Продукт: " + products[i] + ", " + cntProduct[i] + " шт, " + prices[i] + " руб/шт, " + "Цена: " + prices[i] * cntProduct[i] + " руб в сумме");
            }
        }
        System.out.println("Cумма всех товаров: " + sum);
    }
}