import java.io.*;

public class Basket {
    final private String[] products;
    final private int[] prices;

    private int[] countProduct = new int[3];

    private int sum = 0;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
    }

    public int getSum() {
        return sum;
    }

    public int[] getCountProduct() {
        return countProduct;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    public void setCountProduct(int[] countProduct) {
        this.countProduct = countProduct;
    }

    public static Basket loadFromTxtFile(File textFile) throws  IOException {
        Basket basket = null;
        int [] count = new int[3];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String buf;
            while ((buf = bufferedReader.readLine()) != null) {
                String [] infoBasket = buf.split(" ");
                if (infoBasket.length < 2) {
                    basket.setSum(Integer.parseInt(buf));
                } else {
                    count[Integer.parseInt(infoBasket[0])] = Integer.parseInt(infoBasket[1]);
                }
            }
           basket.setCountProduct(count);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
                System.out.println(products[i] + " " + prices[i] + " руб/шт");
            }
        }
    }

    public void saveTxt(File textTxt) throws IOException {
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(textTxt, false))) {
            for (int i = 0; i < countProduct.length; i++) {
                if (countProduct[i] == 0) {
                    continue;
                } else {
                    outputStream.append(products[i]).append(String.valueOf(countProduct[i]));
                    outputStream.newLine();
                }
            }
            outputStream.append("Сумма товаров в корзине: ");
            outputStream.write(sum + " руб");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
