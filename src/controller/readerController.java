package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Reader;

public class readerController {

    public void docFileReader(String readerPath, List<Reader> readerList) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(readerPath);
            try (BufferedReader br = new BufferedReader(fileReader)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] res = line.split(",");
                    String readerID = res[0];
                    String surname = res[1];
                    String name = res[2];
                    String identityCard = res[3];
                    String phone = res[4];
                    String cardIssueDate = res[5];
                    String job = res[6];

                    model.Reader reader = new model.Reader(readerID, surname, name, identityCard, phone, cardIssueDate, job);
                    readerList.add(reader);
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void ghiFileReader(String readerPath, List<Reader> readerList) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(readerPath);
            try (BufferedWriter bw = new BufferedWriter(fileWriter)) {
                for (Reader reader : readerList) {
                    String line = reader.toString();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
    }

    public void displayReader(DefaultTableModel tableModel, JTable table, List<Reader> readerList) {
        String[] columnNames = {"Mã độc giả", "Họ đệm",
                "Tên", "CMND", "SĐT", "Ngày cấp thẻ", "Chức vụ"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        for (model.Reader reader : readerList) {
            tableModel.addRow(new Object[]{reader.getReaderID(), reader.getSurname(), reader.getName(), reader.getIdentityCard(), reader.getPhone(), reader.getCardIssueDate(), reader.getJob()});
        }
        table.setModel(tableModel);
    }

    public void searchReader(DefaultTableModel tableModel, JTable table, List<Reader> readerList, String inputSearch, String parameter) {
        String[] columnNames = {"Mã độc giả", "Họ đệm",
                "Tên", "CMND", "SĐT", "Ngày cấp thẻ", "Chức vụ"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        if (parameter.equalsIgnoreCase("Tất cả")) {
            displayReader(tableModel, table, readerList);
        } else if (parameter.equalsIgnoreCase("Mã độc giả")) {
            for (model.Reader reader : readerList) {
                if (reader.getReaderID().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{reader.getReaderID(), reader.getSurname(), reader.getName(), reader.getIdentityCard(), reader.getPhone(), reader.getCardIssueDate(), reader.getJob()});
                }
            }
            table.setModel(tableModel);
        } else if (parameter.equalsIgnoreCase("Tên độc giả")) {
            for (model.Reader reader : readerList) {
                if (reader.getName().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{reader.getReaderID(), reader.getSurname(), reader.getName(), reader.getIdentityCard(), reader.getPhone(), reader.getCardIssueDate(), reader.getJob()});
                }
            }
            table.setModel(tableModel);
        } else if (parameter.equalsIgnoreCase("CMND")) {
            for (model.Reader reader : readerList) {
                if (reader.getIdentityCard().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{reader.getReaderID(), reader.getSurname(), reader.getName(), reader.getIdentityCard(), reader.getPhone(), reader.getCardIssueDate(), reader.getJob()});
                }
            }
            table.setModel(tableModel);
        } else if (parameter.equalsIgnoreCase("Số điện thoại")) {
            for (model.Reader reader : readerList) {
                if (reader.getPhone().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{reader.getReaderID(), reader.getSurname(), reader.getName(), reader.getIdentityCard(), reader.getPhone(), reader.getCardIssueDate(), reader.getJob()});
                }
            }
            table.setModel(tableModel);
        }
    }

    public void sortReaderBy(DefaultTableModel tableModel, JTable table, List<Reader> readerList, JComboBox cbbSortReader) {
        String parameter = String.valueOf(cbbSortReader.getSelectedItem());
        if (parameter.equals("Mã độc giả tăng dần")) {
            readerList.sort((o1, o2) -> o1.getReaderID().compareTo(o2.getReaderID()));
        } else if (parameter.equals("Mã độc giả giảm dần")) {
            readerList.sort((o1, o2) -> o2.getReaderID().compareTo(o1.getReaderID()));
        } else if (parameter.equals("Ngày cấp thẻ tăng dần")) {
            readerList.sort((o1, o2) -> o1.getCardIssueDate().compareTo(o2.getCardIssueDate()));
        } else if (parameter.equals("Ngày cấp thẻ giảm dần")) {
            readerList.sort((o1, o2) -> o2.getCardIssueDate().compareTo(o1.getCardIssueDate()));
        }
        displayReader(tableModel, table, readerList);
    }
}
