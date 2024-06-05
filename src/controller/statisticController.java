package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Book;
import model.Loan;
import model.Punishment;

public class statisticController {

    public void ghiFileStatistic(String statisticPath , JTable table) {
        TableModel model = table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        try (PrintWriter writer = new PrintWriter(statisticPath)) {
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    writer.print(model.getValueAt(row, col));
                    if (col < colCount - 1) {
                        writer.print(",");
                    }
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void displayStatistic(DefaultTableModel tableModel, JTable table, List<Book> bookList, List<Loan> loanList, List<Punishment> punishmentList) {
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        tableModel.addColumn("Tổng số sách");
        tableModel.addColumn("Tổng số phiếu mượn sách");
        tableModel.addColumn("Sách đang cho mượn");
        tableModel.addColumn("Sách bị trễ hạn trả");
        int soSach = 0, sachDangChoMuon = 0, sachBiTre = 0;
        for (Book book : bookList) {
            soSach += Integer.valueOf(book.getAmount());
        }
        for (Loan loan : loanList) {
            if (loan.getStatus().equalsIgnoreCase("Chưa trả")) {
                sachDangChoMuon += Integer.valueOf(loan.getLoanNo());
            }
        }
        for (Punishment punishment : punishmentList) {
            if (Integer.parseInt(punishment.getDaysLate()) > 0) {
                sachBiTre += Integer.valueOf(punishment.getLoanNo());
            }
        }
        tableModel.addRow(new Object[]{soSach, loanList.size(), sachDangChoMuon, sachBiTre});
    }
}
