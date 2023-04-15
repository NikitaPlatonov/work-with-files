import java.io.*;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1;
    final private String[] products;
    final private int[] prices;

    private int[] countProduct;

    private int sum = 0;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.countProduct = new int[products.length];
    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        Basket basket = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream obt = new ObjectInputStream(fis)) {
            basket = (Basket) obt.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return basket;
    }

    public void saveBin(File file) throws IOException {
        try (OutputStream out = new FileOutputStream(file); ObjectOutputStream obt = new ObjectOutputStream(out)) {
            obt.writeObject(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
