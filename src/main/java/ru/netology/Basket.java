package ru.netology;

import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    final private String[] products;
    final private int[] prices;

    private int[] countProduct;

    private int sum = 0;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.countProduct = new int[products.length];
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {
        Basket basket = null;
        String[] productsFromFileTxt;
        int[] pricesFromFileTxt;
        int[] countProductsFromFileTxt;
        int sumFromFileTxt;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String productsFromFile = bufferedReader.readLine();
            String pricesFromFile = bufferedReader.readLine();
            String countProductsFromFile = bufferedReader.readLine();
            String sum = bufferedReader.readLine();
            productsFromFileTxt = productsFromFile.split(" ");
            pricesFromFileTxt = Arrays.stream(pricesFromFile.split(" ")).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
            countProductsFromFileTxt = Arrays.stream(countProductsFromFile.split(" ")).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
            sumFromFileTxt = Integer.parseInt(sum);
            basket = new Basket(productsFromFileTxt, pricesFromFileTxt);
            basket.setSum(sumFromFileTxt);
            basket.setCountProduct(countProductsFromFileTxt);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return basket;
    }

    public static Basket loadFromJson(File fileJson) throws Exception {
        Basket basket = null;
        try (ObjectInputStream obt = new ObjectInputStream(new FileInputStream(fileJson))) {
            basket = (Basket) obt.readObject();
        } catch (Exception e) {
            throw new Exception("Не удалось десериализовать объект");
        }
        return basket;
    }

    public void addToCart(int productNum, int amount) {
        countProduct[productNum] += amount;
        sum += prices[productNum] * amount;
    }

    public void printCart() {
        System.out.println("Ваша корзина: ");
        for (int i = 0; i < countProduct.length; i++) {
            if (countProduct[i] != 0) {
                System.out.println(products[i] + " " + prices[i] + " руб/шт," + " У вас в корзине: " + countProduct[i] + " шт.");
            }
        }
    }

    public void saveTxt(File textTxt) throws IOException {
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(textTxt))) {
            for (String product : products) {
                outputStream.append(product).append(" ");
            }
            outputStream.newLine();
            for (int price : prices) {
                outputStream.append(String.valueOf(price)).append(" ");
            }
            outputStream.newLine();
            for (int count : countProduct) {
                outputStream.append(String.valueOf(count)).append(" ");
            }
            outputStream.newLine();
            outputStream.append(String.valueOf(sum));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveToJson(File fileJson) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileJson))) {
            out.writeObject(this);
        } catch (Exception e) {
            throw new Exception("Файл не найден");
        }
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setCountProduct(int[] countProduct) {
        this.countProduct = countProduct;
    }
}
