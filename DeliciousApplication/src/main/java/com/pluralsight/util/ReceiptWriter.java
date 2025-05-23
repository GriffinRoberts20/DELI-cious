package com.pluralsight.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    public static void writeReceipt(String receipt){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String receiptFilePath = "src/main/resources/receipts/"+LocalDateTime.now().format(formatter)+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFilePath))) {
            writer.write(receipt);
            writer.close();
            System.out.println("Successfully saved receipt");
        } catch (Exception e) {
            // Wrap and rethrow exceptions as runtime
            throw new RuntimeException(e);
        }
    }
}
