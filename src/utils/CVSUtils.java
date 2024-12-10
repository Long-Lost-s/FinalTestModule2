package utils;

import model.ImportedPhone;
import model.OfficialPhone;
import model.Phone;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CVSUtils {
    private static final String CSV_FILE = "phones.csv"; // Path to the CSV file

    // Write phone list to CSV
    public static void writePhonesToCSV(List<Phone> phoneList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Phone phone : phoneList) {
                if (phone instanceof OfficialPhone) {
                    OfficialPhone officialPhone = (OfficialPhone) phone;
                    writer.write("Official," + officialPhone.getId() + "," + officialPhone.getName() + ","
                            + officialPhone.getPrice() + "," + officialPhone.getQuantity() + ","
                            + officialPhone.getManufacturer() + "," + officialPhone.getWarrantyPeriod() + ","
                            + officialPhone.getWarrantyScope());
                } else if (phone instanceof ImportedPhone) {
                    ImportedPhone importedPhone = (ImportedPhone) phone;
                    writer.write("Imported," + importedPhone.getId() + "," + importedPhone.getName() + ","
                            + importedPhone.getPrice() + "," + importedPhone.getQuantity() + ","
                            + importedPhone.getManufacturer() + "," + importedPhone.getImportedCountry() + ","
                            + importedPhone.getStatus());
                }
                writer.newLine(); // Write a new line after each phone
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file CSV: " + e.getMessage());
        }
    }

    // Read phones from CSV
    public static List<Phone> readPhonesFromCSV() {
        List<Phone> phoneList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals("Official")) {
                    phoneList.add(new OfficialPhone(data[1], data[2], Double.parseDouble(data[3]), Integer.parseInt(data[4]),
                            data[5], Integer.parseInt(data[6]), data[7]));
                } else if (data[0].equals("Imported")) {
                    phoneList.add(new ImportedPhone(data[1], data[2], Double.parseDouble(data[3]), Integer.parseInt(data[4]),
                            data[5], data[6], data[7]));
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc từ file CSV: " + e.getMessage());
        }
        return phoneList;
    }
}
