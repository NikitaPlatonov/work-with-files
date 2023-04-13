import java.io.*;

public class Basket {
    private String[] products;
    private int[] prices;

    private int[] countProduct = new int[3];

    public Basket (String[] products, int[] prices) {
        this.products = new String[3];
        this.prices = new int[3];
    }
    public void addToCart(int productNum, int amount) {
        countProduct[productNum] += amount;
    }
    public void printCart() {
        System.out.println("Ваша корзина: ");
        for (int i = 0; i < countProduct.length; i++) {
            if(countProduct[i] != 0) {
                System.out.println(products[i] + " " + prices[i] + " руб/шт");
            }
        }
    }
    public void saveTxt(File textTxt) throws IOException {
        try(FileWriter fileWriter = new FileWriter(textTxt, false)) {
            for (int i = 0; i < countProduct.length;i++) {
                if (countProduct[i] == 0) {
                    continue;
                } else {
                    fileWriter.write(products[i]);
                    System.out.println(" ");
                }
                fileWriter.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //public static Basket loadFromTxtFile(File textFile) {
//
   // }
}
