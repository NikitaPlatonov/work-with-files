package ru.netology;

import com.opencsv.CSVWriter;
import java.io.*;

public class ClientLog {
    StringBuilder infActions = new StringBuilder();


    public void logEnd() {
        infActions.append("end").append(",");
    }

    public void logStart() {
        infActions.append("Start").append(",");
    }

    public void log(int productNum, int amount) {
        if (infActions.toString().equals("")) {
            infActions.append("productNum").append(" ").append("amount").append(",");
        }
        infActions.append(productNum).append(" ").append(amount).append(",");
    }
    public boolean exportAxCSV(File textFile) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(textFile))) {
            writer.writeNext(infActions.toString().split(","));
            return true;
        } catch (IOException E) {
            System.out.println("Ошибка: "+ E.getMessage() );
            E.printStackTrace();
            return false;
        }
    }
}
