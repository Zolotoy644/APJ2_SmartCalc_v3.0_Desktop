package edu.school21.smartcalc.serializer;

import java.io.*;

public class HistorySerializer {
    private final File historyFile;

    public HistorySerializer(String fileName) {
        historyFile = new File(fileName);
    }

    public ExpressionHistory getExpressionHistory() {
        try (FileInputStream fileInputStream = new FileInputStream(historyFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (ExpressionHistory) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ExpressionHistory();
        }
    }

    public void saveHistory(ExpressionHistory expressionHistory) {
        try (FileOutputStream outputStream = new FileOutputStream(historyFile);
             ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(expressionHistory);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void clearHistory() {
        try (FileOutputStream outputStream = new FileOutputStream(historyFile);
             ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(outputStream)) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}