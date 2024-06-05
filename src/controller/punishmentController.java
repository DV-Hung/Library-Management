package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Punishment;

public class punishmentController {

    public void docFilePunishment(String punishmentPath, List<Punishment> punishmentList) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(punishmentPath);
            try (BufferedReader br = new BufferedReader(fileReader)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] res = line.split(",");
                    String loanID = res[0];
                    String readerID = res[1];
                    String fullName = res[2];
                    String bookID = res[3];
                    String book = res[4];
                    String loanNo = res[5];
                    String daysLate = res[6];
                    String total = res[7];
                    String status = res[8];
                    Punishment punishment = new Punishment(loanID, readerID, fullName, bookID, book, loanNo, daysLate, total, status);
                    punishmentList.add(punishment);
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void ghiFilePunishment(String punishmentPath, List<Punishment> punishmentList) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(punishmentPath);
            try (BufferedWriter bw = new BufferedWriter(fileWriter)) {
                for (Punishment punishment : punishmentList) {
                    String line = punishment.toString();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
    }

    public void displayPunishment(DefaultTableModel tableModel, JTable table, List<Punishment> punishmentList) {
        String[] columnNames = {"Mã mượn", "Mã độc giả", "Tên độc giả", "Mã sách", "Tên sách", "Số lượng mượn", "Quá hạn (ngày)", "Thành tiền (đồng)", "Trạng thái sách"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        for (Punishment punishment : punishmentList) {
            tableModel.addRow(new Object[]{punishment.getLoanID(), punishment.getReaderID(), punishment.getFullName(), punishment.getBookID(), punishment.getBook(), punishment.getLoanNo(), punishment.getDaysLate(), punishment.getTotal(), punishment.getStatus()});
        }
    }
}
