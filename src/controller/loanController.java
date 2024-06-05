package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Loan;

public class loanController {

    public void docFileLoan(String loanPath, List<Loan> loanList) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(loanPath);
            try (BufferedReader br = new BufferedReader(fileReader)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] res = line.split(",");
                    String loanID = res[0];
                    String bookID = res[1];
                    String readerID = res[2];
                    String loanNo = res[3];
                    String loanDate = res[4];
                    String bookReturnAppointmentDate = res[5];
                    String bookReturnDate = res[6];
                    String status = res[7];
                    Loan loan = new Loan(loanID, bookID, readerID, loanNo, loanDate, bookReturnAppointmentDate, bookReturnDate, status);
                    loanList.add(loan);
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void ghiFileLoan(String loanPath, List<Loan> loanList) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(loanPath);
            try (BufferedWriter bw = new BufferedWriter(fileWriter)) {
                for (Loan loan : loanList) {
                    String line = loan.toString();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
    }

    public void displayLoan(DefaultTableModel tableModel, JTable table, List<Loan> loanList) {
        String[] columnNames = {"Mã mượn trả", "Mã độc giả", "Mã sách", "Số lượng mượn", "Ngày mượn", "Ngày hẹn trả", "Ngày trả", "Trạng thái"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        for (Loan loan : loanList) {
            tableModel.addRow(new Object[]{loan.getLoanID(), loan.getReaderID(), loan.getBookID(), loan.getLoanNo(), loan.getLoanDate(), loan.getBookReturnAppointmentDate(), loan.getBookReturnDate(), loan.getStatus()});
        }
    }

    public void sortLoanBy(DefaultTableModel tableModel, JTable table, List<Loan> loanList, JComboBox cbbLoanSort) {
        if (String.valueOf(cbbLoanSort.getSelectedItem()).equalsIgnoreCase("Mã mượn trả tăng dần")) {
            loanList.sort(new Comparator<Loan>() {
                @Override
                public int compare(Loan o1, Loan o2) {
                    return o1.getLoanID().compareTo(o2.getLoanID());
                }
            });
        } else if (String.valueOf(cbbLoanSort.getSelectedItem()).equalsIgnoreCase("Mã mượn trả giảm dần")) {
            loanList.sort(new Comparator<Loan>() {
                @Override
                public int compare(Loan o1, Loan o2) {
                    return o2.getLoanID().compareTo(o1.getLoanID());
                }
            });
        }
        displayLoan(tableModel, table, loanList);
    }

    public void searchLoanBy(DefaultTableModel tableModel, JTable table, List<Loan> loanList, String inputSearch, String parameter) {
        String[] columnNames = {"Mã mượn trả", "Mã độc giả", "Mã sách", "Số lượng mượn", "Ngày mượn", "Ngày hẹn trả", "Ngày trả", "Trạng thái"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        if (parameter.equalsIgnoreCase("Tất cả")) {
            displayLoan(tableModel, table, loanList);
        } else if (parameter.equalsIgnoreCase("Mã mượn trả")) {
            for (Loan loan : loanList) {
                if (loan.getLoanID().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{loan.getLoanID(), loan.getReaderID(), loan.getBookID(), loan.getLoanNo(), loan.getLoanDate(), loan.getBookReturnAppointmentDate(), loan.getBookReturnDate(), loan.getStatus()});
                }
            }
            table.setModel(tableModel);
        } else if (parameter.equalsIgnoreCase("Mã độc giả")) {
            for (Loan loan : loanList) {
                if (loan.getReaderID().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{loan.getLoanID(), loan.getReaderID(), loan.getBookID(), loan.getLoanNo(), loan.getLoanDate(), loan.getBookReturnAppointmentDate(), loan.getBookReturnDate(), loan.getStatus()});
                }
            }
            table.setModel(tableModel);
        } else if (parameter.equalsIgnoreCase("Mã sách")) {
            for (Loan loan : loanList) {
                if (loan.getBookID().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{loan.getLoanID(), loan.getReaderID(), loan.getBookID(), loan.getLoanNo(), loan.getLoanDate(), loan.getBookReturnAppointmentDate(), loan.getBookReturnDate(), loan.getStatus()});
                }
            }
            table.setModel(tableModel);
        } else if (parameter.equalsIgnoreCase("Ngày mượn")) {
            for (Loan loan : loanList) {
                if (loan.getLoanDate().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{loan.getLoanID(), loan.getReaderID(), loan.getBookID(), loan.getLoanNo(), loan.getLoanDate(), loan.getBookReturnAppointmentDate(), loan.getBookReturnDate(), loan.getStatus()});
                }
            }
            table.setModel(tableModel);
        } else if (parameter.equalsIgnoreCase("Ngày hẹn trả")) {
            for (Loan loan : loanList) {
                if (loan.getBookReturnAppointmentDate().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{loan.getLoanID(), loan.getReaderID(), loan.getBookID(), loan.getLoanNo(), loan.getLoanDate(), loan.getBookReturnAppointmentDate(), loan.getBookReturnDate(), loan.getStatus()});
                }
            }
            table.setModel(tableModel);
        } else if (parameter.equalsIgnoreCase("Trạng thái")) {
            for (Loan loan : loanList) {
                if (loan.getStatus().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{loan.getLoanID(), loan.getReaderID(), loan.getBookID(), loan.getLoanNo(), loan.getLoanDate(), loan.getBookReturnAppointmentDate(), loan.getBookReturnDate(), loan.getStatus()});
                }
            }
            table.setModel(tableModel);
        }
    }
}
