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

import model.Book;

public class bookController {

    public void docFileBook(String bookPath, List<Book> bookList) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(bookPath);
            try (BufferedReader br = new BufferedReader(fileReader)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] res = line.split(",");
                    String bookID = res[0];
                    String bookName = res[1];
                    String pageNo = res[2];
                    String language = res[3];
                    String price = res[4];
                    String amount = res[5];
                    String publishYear = res[6];
                    String type = res[7];
                    String author = res[8];
                    String publisher = res[9];
                    Book book = new Book(bookID, bookName, pageNo, language, price, amount, publishYear, type, author, publisher);
                    bookList.add(book);
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void ghiFileBook(String bookPath, List<Book> bookList) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(bookPath);
            try (BufferedWriter bw = new BufferedWriter(fileWriter)) {
                for (Book book : bookList) {
                    String line = book.toString();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
    }

    public void displayBook(DefaultTableModel tableModel, JTable table, List<Book> bookList) {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã sách");
        tableModel.addColumn("Tên sách");
        tableModel.addColumn("Số trang");
        tableModel.addColumn("Ngôn ngữ");
        tableModel.addColumn("Giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Năm xuất bản");
        tableModel.addColumn("Thể loại");
        tableModel.addColumn("Tác giả");
        tableModel.addColumn("Nhà xuất bản");
        for (Book book : bookList) {
            tableModel.addRow(new Object[]{book.getBookID(), book.getBookName(), book.getPageNo(), book.getLanguage(), book.getPrice(),
                    book.getAmount(), book.getPublishYear(), book.getType(), book.getAuthor(), book.getPublisher()});
        }
        table.setModel(tableModel);
    }

    public void searchBook(DefaultTableModel tableModel, JTable table, List<Book> bookList, String inputSearch, String parameter) {
        String[] columnNames = {"Mã sách", "Tên sách", "Số trang", "Ngôn ngữ", "Giá trị", "Số lượng", "Năm xuất bản", "Thể loại", " Tác giả", " Nhà xuất bản"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        if (parameter.equalsIgnoreCase("Tên sách")) {
            for (Book book : bookList) {
                if (book.getBookName().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{book.getBookID(), book.getBookName(), book.getPageNo(), book.getLanguage(), book.getPrice(),
                            book.getAmount(), book.getPublishYear(), book.getType(), book.getAuthor(), book.getPublisher()});
                }
            }
        } else if (parameter.equalsIgnoreCase("Tác giả")) {
            for (Book book : bookList) {
                if (book.getAuthor().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{book.getBookID(), book.getBookName(), book.getPageNo(), book.getLanguage(), book.getPrice(),
                            book.getAmount(), book.getPublishYear(), book.getType(), book.getAuthor(), book.getPublisher()});
                }
            }
        } else if (parameter.equalsIgnoreCase("Ngôn ngữ")) {
            for (Book book : bookList) {
                if (book.getLanguage().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{book.getBookID(), book.getBookName(), book.getPageNo(), book.getLanguage(), book.getPrice(),
                            book.getAmount(), book.getPublishYear(), book.getType(), book.getAuthor(), book.getPublisher()});
                }
            }
        } else if (parameter.equalsIgnoreCase("Năm xuất bản")) {
            for (Book book : bookList) {
                if (book.getPublishYear().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{book.getBookID(), book.getBookName(), book.getPageNo(), book.getLanguage(), book.getPrice(),
                            book.getAmount(), book.getPublishYear(), book.getType(), book.getAuthor(), book.getPublisher()});
                }
            }
        } else if (parameter.equalsIgnoreCase("Thể loại")) {
            for (Book book : bookList) {
                if (book.getType().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{book.getBookID(), book.getBookName(), book.getPageNo(), book.getLanguage(), book.getPrice(),
                            book.getAmount(), book.getPublishYear(), book.getType(), book.getAuthor(), book.getPublisher()});
                }
            }
        } else if (parameter.equalsIgnoreCase("NXB")) {
            for (Book book : bookList) {
                if (book.getPublisher().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{book.getBookID(), book.getBookName(), book.getPageNo(), book.getLanguage(), book.getPrice(),
                            book.getAmount(), book.getPublishYear(), book.getType(), book.getAuthor(), book.getPublisher()});
                }
            }
        } else if (parameter.equalsIgnoreCase("Mã sách")) {
            for (Book book : bookList) {
                if (book.getBookID().toLowerCase().contains(inputSearch.toLowerCase())) {
                    tableModel.addRow(new Object[]{book.getBookID(), book.getBookName(), book.getPageNo(), book.getLanguage(), book.getPrice(),
                            book.getAmount(), book.getPublishYear(), book.getType(), book.getAuthor(), book.getPublisher()});
                }
            }
        } else if (parameter.equalsIgnoreCase("Tất cả")) {
            displayBook(tableModel, table, bookList);
        }
    }

    public void sortBookBy(DefaultTableModel tableModel, JTable table, List<Book> bookList, JComboBox cbbSort) {
        String parameter = String.valueOf(cbbSort.getSelectedItem());
        if (parameter.equals("Tăng dần theo mã sách")) {
            bookList.sort((o1, o2) -> o1.getBookID().compareTo(o2.getBookID()));
        } else if (parameter.equals("Giảm dần theo mã sách")) {
            bookList.sort((o1, o2) -> o2.getBookID().compareTo(o1.getBookID()));
        } else if (parameter.equals("Tăng dần theo số trang")) {
            bookList.sort((o1, o2) -> Integer.parseInt(o1.getPageNo()) - Integer.parseInt(o2.getPageNo()));
        } else if (parameter.equals("Giảm dần theo số trang")) {
            bookList.sort((o1, o2) -> Integer.parseInt(o2.getPageNo()) - Integer.parseInt(o1.getPageNo()));
        } else if (parameter.equals("Tăng dần theo giá bán")) {
            bookList.sort((o1, o2) -> (int) (Long.parseLong(o1.getPrice()) - Long.parseLong(o2.getPrice())));
        } else if (parameter.equals("Giảm dần theo giá bán")) {
            bookList.sort((o1, o2) -> (int) (Long.parseLong(o2.getPrice()) - Long.parseLong(o1.getPrice())));
        }
        displayBook(tableModel, table, bookList);
    }
}
