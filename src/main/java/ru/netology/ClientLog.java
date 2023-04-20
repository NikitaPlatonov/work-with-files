package ru.netology;

import com.opencsv.CSVWriter;

import java.io.*;

public class ClientLog {
    StringBuilder infActions = new StringBuilder();

    public void logEnd() {
        infActions.append("End").append(" ");
    }

    public void logStart() {
        infActions.append("Start").append(" ");
    }

    public void log(int productNum, int amount) {
        if (infActions.toString().equals("Start ")) {
            infActions.append("productNum").append(",").append("amount").append(" ");
        }
        infActions.append(productNum).append(",").append(amount).append(" ");
    }

    public boolean exportAxCSV(File textFile) throws Exception {
        if (infActions != null) {
            String[] result = infActions.toString().split(" ");
            try (CSVWriter writer = new CSVWriter(new FileWriter(textFile))) {
                //TODO такая реализация нужна, чтобы каждое отдельное действие было на новой строке
                for (String actions : result) {
                    String[] record = {actions};
                    writer.writeNext(record);
                }
                return true;
            } catch (IOException E) {
                System.out.println("Ошибка: " + E.getMessage());
                E.printStackTrace();
                return false;
            }
        } else {
            String message = "Вы хотите экспортировать в файл пустой log";
            throw new Exception(message);

        }
    }
}
