package view;

import com.toedter.calendar.JDateChooser;
import controller.bookController;
import controller.loanController;
import controller.punishmentController;
import controller.readerController;
import controller.statisticController;
import model.Book;
import model.Loan;
import model.Punishment;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener {

    private static String bookPath = "C:\\Library-Management\\src\\fileData\\book.txt";
    private static String loanPath = "C:\\Library-Management\\src\\fileData\\loan.txt";
    private static String punishmentPath = "C:\\Library-Management\\src\\fileData\\punishment.txt";
    private static String readerPath = "C:\\Library-Management\\src\\fileData\\reader.txt";
    private static String statisticPath = "C:\\Library-Management\\src\\fileData\\statistic.txt";
    
    bookController bController = new bookController();
    readerController rController = new readerController();
    loanController lController = new loanController();
    punishmentController pController = new punishmentController();
    statisticController sController = new statisticController();
    
    private Container container;
    private ButtonGroup buttonGroup;
    private DefaultTableModel tableModel;

    private JTextField tfSearchBook, tfBookName, tfPageNo, tfLanguage, tfPrice, tfAmount, tfAuthor,
            tfPublisher, tfReaderName, tfIdentityCard, tfPhoneNumber, tfSurname, tfReaderID1, tfReaderID2, tfBookID1, tfBookID2, tfSearchReader, tfLoanID, tfLoanNO, tfLoanSearch;

    private JButton btnSearchBook, btnAddBook, btnResetBook, btnUpdateBook, btnDeleteBook,
            btnCheckReaderID, btnCheckBookID, btnLoanBook, btnReturnBook, btnSearchLoan,
            btnResetLoan, btnPunish, btnAddReader, btnUpdateReader, btnDeleteReader, btnResetReader, btnSearchReader, btnLoanList;
    private JLabel lblBookName, lblPageNo, lblPrice, lblAmount, lblPublishYear,
            lblType, lblAuthor, lblPublisher, lblLanguage, lblReaderName, lblIdentityCard,
            lblPhoneNumber, lblPosition, lblSurname, lblReaderId, lblBookId, lblReturnAppointmentDate, lblSort,
            lblOutputReader, lblOutputBook, lblStatisticTotalBook, lblStatisticLoan, lblStatisticPunish, lblStatisticTotalLoan, lblBookID,
            lblSearchBy, lblFindReaderBy, lblSortReaderBy, lblDateRange, lblLoanID, lblBorrowedDate, lblLoanNo, lblLoanSort, lblLoanSearch;

    private JScrollPane scrollPane;
    private JComboBox cbbFindBy, cbbPublishYear, cbbSort, cbbType, cbbLoanSort, cbbLoanSearch, cbbFindReader, cbbSortReader;

    private JTable table;
    private JPanel pnlBookManagement, pnlReaderManagement, pnlLoan, pnlStatistical;
    private JTabbedPane tabbedPane;
    private JRadioButton rdoLecturer, rdoStudent;
    private JDateChooser dateRange, borrowedDate, payDate, payAppoinmentDate;
    private DateFormat df;

    private List<Book> bookList = new ArrayList<>();
    private List<Loan> loanList = new ArrayList<>();
    private List<Punishment> punishmentList = new ArrayList<>();
    private List<model.Reader> readerList = new ArrayList<>();

    public MainFrame() {
        bController.docFileBook(bookPath, bookList);
        rController.docFileReader(readerPath, readerList);
        lController.docFileLoan(loanPath, loanList);
        pController.docFilePunishment(punishmentPath, punishmentList);

        container = this.getContentPane();
        container.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1200, 720);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Library Management");

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 321, 1166, 362);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        container.add(scrollPane);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 1166, 301);

        // Quan Ly Sach
        pnlBookManagement = new JPanel();
        pnlBookManagement.setBounds(10, 41, 1166, 270);
        pnlBookManagement.setLayout(null);

        lblBookID = new JLabel("Mã Sách: ");
        lblBookName = new JLabel("Tên sách:");
        lblPageNo = new JLabel("Số trang:");
        lblLanguage = new JLabel("Ngôn ngữ:");
        lblPrice = new JLabel("Giá:");
        lblAmount = new JLabel("Số lượng:");
        lblPublishYear = new JLabel("Năm xuất bản:");
        lblType = new JLabel("Thể loại:");
        lblAuthor = new JLabel("Tác giả:");
        lblPublisher = new JLabel("Nhà xuất bản:");
        lblSort = new JLabel("Sắp xếp");
        lblSearchBy = new JLabel("Tìm kiếm theo");

        tfBookID1 = new JTextField();
        tfBookName = new JTextField();
        tfPageNo = new JTextField();
        tfLanguage = new JTextField();
        tfPrice = new JTextField();
        tfAmount = new JTextField();
        tfSearchBook = new JTextField();
        tfAuthor = new JTextField();
        tfPublisher = new JTextField();

        cbbPublishYear = new JComboBox();
        cbbType = new JComboBox();
        cbbSort = new JComboBox();
        cbbFindBy = new JComboBox();

        btnAddBook = new JButton("Thêm sách");
        btnDeleteBook = new JButton("Xóa sách");
        btnUpdateBook = new JButton("Cập nhật");
        btnResetBook = new JButton("Nhập lại");
        btnSearchBook = new JButton("Tìm kiếm");

        lblBookID.setBounds(10, 17, 73, 26);
        lblBookName.setBounds(10, 53, 73, 26);
        lblPageNo.setBounds(10, 89, 73, 26);
        lblLanguage.setBounds(10, 125, 73, 26);
        lblPrice.setBounds(10, 161, 81, 26);
        lblPublishYear.setBounds(245, 21, 81, 19);
        lblType.setBounds(245, 53, 81, 26);
        lblAuthor.setBounds(245, 89, 86, 26);
        lblPublisher.setBounds(245, 125, 86, 26);
        lblAmount.setBounds(245, 161, 73, 19);
        lblSort.setBounds(570, 17, 191, 30);
        lblSearchBy.setBounds(570, 89, 191, 30);

        tfBookID1.setBounds(70, 21, 123, 19);
        tfBookName.setBounds(70, 57, 123, 19);
        tfPageNo.setBounds(70, 93, 123, 19);
        tfLanguage.setBounds(70, 129, 123, 19);
        tfPrice.setBounds(70, 161, 123, 19);
        tfAuthor.setBounds(336, 93, 123, 19);
        tfPublisher.setBounds(336, 129, 123, 19);
        tfAmount.setBounds(336, 161, 123, 19);
        tfSearchBook.setBounds(570, 175, 470, 30);

        btnAddBook.setBounds(10, 215, 103, 26);
        btnDeleteBook.setBounds(136, 215, 96, 26);
        btnUpdateBook.setBounds(252, 215, 99, 26);
        btnSearchBook.setBounds(1048, 175, 103, 30);
        btnResetBook.setBounds(373, 215, 86, 26);

        cbbPublishYear.setBounds(336, 20, 123, 20);
        cbbType.setBounds(336, 55, 123, 23);
        cbbSort.setBounds(570, 53, 191, 30);
        cbbFindBy.setBounds(570, 125, 113, 30);
        cbbFindBy.setMaximumRowCount(7);
        cbbPublishYear.setMaximumRowCount(13);

        cbbPublishYear.setModel(new DefaultComboBoxModel(new String[]{"2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000"}));
        cbbType.setModel(new DefaultComboBoxModel(new String[]{"Giáo trình", "Tài liệu tham khảo", "Luận án", "Luận văn", "Sách bài tập"}));
        cbbSort.setModel(new DefaultComboBoxModel(new String[]{"Tăng dần theo mã sách", "Giảm dần theo mã sách", "Tăng dần theo số trang", "Giảm dần theo số trang", "Tăng dần theo giá bán", "Giảm dần theo giá bán"}));
        cbbFindBy.setModel(new DefaultComboBoxModel(new String[]{"Tất cả", "Mã sách", "Tên sách", "Tác giả", "Ngôn ngữ", "Năm xuất bản", "Thể loại", "NXB"}));

        pnlBookManagement.add(lblBookID);
        pnlBookManagement.add(lblBookName);
        pnlBookManagement.add(lblPageNo);
        pnlBookManagement.add(lblLanguage);
        pnlBookManagement.add(lblPrice);
        pnlBookManagement.add(lblAmount);
        pnlBookManagement.add(lblPublishYear);
        pnlBookManagement.add(lblType);
        pnlBookManagement.add(lblAuthor);
        pnlBookManagement.add(lblPublisher);
        pnlBookManagement.add(lblSort);
        pnlBookManagement.add(lblSearchBy);

        pnlBookManagement.add(tfBookID1);
        pnlBookManagement.add(tfBookName);
        pnlBookManagement.add(tfPageNo);
        pnlBookManagement.add(tfLanguage);
        pnlBookManagement.add(tfAmount);
        pnlBookManagement.add(tfPrice);
        pnlBookManagement.add(cbbPublishYear);
        pnlBookManagement.add(tfAuthor);
        pnlBookManagement.add(tfPublisher);
        pnlBookManagement.add(btnAddBook);
        pnlBookManagement.add(cbbType);
        pnlBookManagement.add(btnDeleteBook);
        pnlBookManagement.add(btnUpdateBook);
        pnlBookManagement.add(cbbSort);
        pnlBookManagement.add(cbbFindBy);
        pnlBookManagement.add(tfSearchBook);
        pnlBookManagement.add(btnSearchBook);
        pnlBookManagement.add(btnResetBook);
        bController.displayBook(tableModel, table, bookList);
        tabbedPane.addTab("Quản lý sách", pnlBookManagement);

        btnAddBook.addActionListener(this);
        btnResetBook.addActionListener(this);
        btnDeleteBook.addActionListener(this);
        btnUpdateBook.addActionListener(this);
        btnSearchBook.addActionListener(this);

        cbbSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bController.sortBookBy(tableModel, table, bookList, cbbSort);
            }
        });

        // Quan Ly Doc Gia
        pnlReaderManagement = new JPanel();
        pnlReaderManagement.setBounds(9, 40, 1166, 270);
        pnlReaderManagement.setLayout(null);

        lblReaderId = new JLabel("Mã độc giả:");
        lblReaderName = new JLabel("Tên:");
        lblIdentityCard = new JLabel("CMND:");
        lblPhoneNumber = new JLabel("Số điện thoại:");
        lblPosition = new JLabel("Chức vụ:");
        lblSurname = new JLabel("Họ đệm: ");
        lblSortReaderBy = new JLabel("Sắp xếp độc giả theo:");
        lblFindReaderBy = new JLabel("Tìm kiếm độc giả theo:");
        lblDateRange = new JLabel("Ngày cấp thẻ:");
        rdoLecturer = new JRadioButton("Giảng viên");
        rdoStudent = new JRadioButton("Sinh viên");

        tfReaderID1 = new JTextField();
        tfIdentityCard = new JTextField();
        tfReaderName = new JTextField();
        tfPhoneNumber = new JTextField();
        tfSurname = new JTextField();
        tfSearchReader = new JTextField();

        cbbFindReader = new JComboBox();
        cbbSortReader = new JComboBox();
        cbbFindReader.setModel(new DefaultComboBoxModel(new String[]{"Tất cả", "Mã độc giả", "Tên độc giả", "CMND", "Số điện thoại"}));
        cbbSortReader.setModel(new DefaultComboBoxModel(new String[]{"Mã độc giả tăng dần", "Mã độc giả giảm dần", "Ngày cấp thẻ tăng dần", "Ngày cấp thẻ giảm dần"}));

        cbbSortReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rController.sortReaderBy(tableModel, table, readerList, cbbSortReader);
            }
        });

        btnAddReader = new JButton("Thêm độc giả");
        btnSearchReader = new JButton("Tìm kiếm");
        btnUpdateReader = new JButton("Cập nhật");
        btnDeleteReader = new JButton("Xóa độc giả");
        btnResetReader = new JButton("Nhập lại");

        btnAddReader.addActionListener(this);
        btnSearchReader.addActionListener(this);
        btnUpdateReader.addActionListener(this);
        btnDeleteReader.addActionListener(this);
        btnResetReader.addActionListener(this);

        dateRange = new JDateChooser();
        dateRange.setDateFormatString("yyyy-MM-dd");
        df = new SimpleDateFormat("yyyy-MM-dd");

        lblReaderId.setBounds(64, 19, 80, 26);
        lblSurname.setBounds(64, 55, 80, 26);
        lblReaderName.setBounds(64, 88, 80, 26);
        lblIdentityCard.setBounds(64, 124, 80, 29);
        lblPhoneNumber.setBounds(64, 163, 80, 26);
        lblDateRange.setBounds(64, 200, 80, 26);
        dateRange.setBounds(170, 200, 246, 26);
        lblPosition.setBounds(64, 233, 80, 26);
        rdoLecturer.setBounds(170, 233, 90, 21);
        rdoStudent.setBounds(290, 233, 90, 21);
        lblSortReaderBy.setBounds(464, 110, 150, 26);
        cbbSortReader.setBounds(620, 110, 160, 26);
        lblFindReaderBy.setBounds(464, 160, 150, 26);
        cbbFindReader.setBounds(620, 160, 150, 26);
        tfReaderID1.setBounds(170, 19, 246, 26);
        tfSurname.setBounds(170, 55, 246, 26);
        tfReaderName.setBounds(170, 91, 246, 26);
        tfIdentityCard.setBounds(170, 127, 246, 26);
        tfPhoneNumber.setBounds(170, 165, 246, 26);
        tfSearchReader.setBounds(800, 160, 150, 26);
        btnAddReader.setBounds(464, 16, 153, 26);
        btnUpdateReader.setBounds(672, 16, 141, 26);
        btnDeleteReader.setBounds(464, 58, 153, 26);
        btnResetReader.setBounds(672, 58, 140, 26);
        btnSearchReader.setBounds(970, 160, 141, 26);
        rdoStudent.setSelected(true);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdoStudent);
        buttonGroup.add(rdoLecturer);

        pnlReaderManagement.add(lblReaderId);
        pnlReaderManagement.add(lblReaderName);
        pnlReaderManagement.add(lblIdentityCard);
        pnlReaderManagement.add(lblPhoneNumber);
        pnlReaderManagement.add(lblPosition);
        pnlReaderManagement.add(lblSurname);
        pnlReaderManagement.add(rdoLecturer);
        pnlReaderManagement.add(rdoStudent);
        pnlReaderManagement.add(tfReaderID1);
        pnlReaderManagement.add(tfReaderName);
        pnlReaderManagement.add(tfIdentityCard);
        pnlReaderManagement.add(tfPhoneNumber);
        pnlReaderManagement.add(tfSurname);
        pnlReaderManagement.add(tfSearchReader);
        pnlReaderManagement.add(lblSortReaderBy);
        pnlReaderManagement.add(lblFindReaderBy);
        pnlReaderManagement.add(cbbFindReader);
        pnlReaderManagement.add(cbbSortReader);
        pnlReaderManagement.add(lblDateRange);
        pnlReaderManagement.add(dateRange);
        pnlReaderManagement.add(btnAddReader);
        pnlReaderManagement.add(btnUpdateReader);
        pnlReaderManagement.add(btnDeleteReader);
        pnlReaderManagement.add(btnResetReader);
        pnlReaderManagement.add(btnSearchReader);
        tabbedPane.addTab("Quản lý độc giả", pnlReaderManagement);

        // Muon Tra Sach
        pnlLoan = new JPanel();
        pnlLoan.setLayout(null);

        lblLoanID = new JLabel("Mã mượn trả");
        lblReaderId = new JLabel("Mã độc giả:");
        lblBookId = new JLabel("Mã sách:");
        lblBorrowedDate = new JLabel("Ngày mượn:");
        lblLoanNo = new JLabel("Số lượng mượn:");
        lblReturnAppointmentDate = new JLabel("Ngày hẹn trả:");
        lblLoanSort = new JLabel("Sắp xếp danh sách phiếu mượn trả:");
        lblLoanSearch = new JLabel("Tìm kiếm phiếu mượn trả:");
        lblOutputReader = new JLabel();
        lblOutputBook = new JLabel();
        tfLoanID = new JTextField();
        tfReaderID2 = new JTextField();
        tfBookID2 = new JTextField();
        tfLoanNO = new JTextField();
        tfLoanSearch = new JTextField();

        cbbLoanSort = new JComboBox();
        cbbLoanSearch = new JComboBox();
        cbbLoanSort.setModel(new DefaultComboBoxModel(new String[]{"Mã mượn trả tăng dần", "Mã mượn trả giảm dần"}));
        cbbLoanSearch.setModel(new DefaultComboBoxModel(new String[]{"Tất cả", "Mã mượn trả", "Mã độc giả", "Mã sách", "Ngày mượn", "Ngày hẹn trả", "Trạng thái"}));

        payAppoinmentDate = new JDateChooser();
        pnlLoan.add(payAppoinmentDate);
        payAppoinmentDate.setBounds(175, 190, 148, 20);
        payAppoinmentDate.setDateFormatString("yyyy-MM-dd");

        borrowedDate = new JDateChooser();
        pnlLoan.add(borrowedDate);
        borrowedDate.setDateFormatString("yyyy-MM-dd");
        borrowedDate.setBounds(175, 160, 148, 20);
        df = new SimpleDateFormat("yyyy-MM-dd");

        btnCheckReaderID = new JButton("Kiểm tra");
        btnCheckBookID = new JButton("Kiểm tra");
        btnLoanBook = new JButton("Mượn sách");
        btnReturnBook = new JButton("Trả sách");
        btnResetLoan = new JButton("Nhập lại");
        btnPunish = new JButton("Danh sách phạt");
        btnLoanList = new JButton("Danh sách mượn trả");
        btnSearchLoan = new JButton("Tìm kiếm");

        btnCheckReaderID.addActionListener(this);
        btnCheckBookID.addActionListener(this);
        btnLoanBook.addActionListener(this);
        btnReturnBook.addActionListener(this);
        btnResetLoan.addActionListener(this);
        btnPunish.addActionListener(this);
        btnLoanList.addActionListener(this);
        btnSearchLoan.addActionListener(this);

        lblLoanID.setBounds(65, 22, 100, 20);
        lblReaderId.setBounds(65, 52, 100, 20);
        lblBookId.setBounds(65, 85, 100, 20);
        lblLoanNo.setBounds(65, 120, 148, 20);
        lblBorrowedDate.setBounds(65, 160, 148, 20);
        lblReturnAppointmentDate.setBounds(65, 190, 148, 20);
        lblOutputReader.setBounds(420, 52, 221, 20);
        lblOutputBook.setBounds(420, 85, 221, 20);
        tfLoanID.setBounds(175, 22, 138, 20);
        tfReaderID2.setBounds(175, 53, 138, 20);
        tfBookID2.setBounds(175, 86, 138, 20);
        tfLoanNO.setBounds(175, 121, 138, 20);
        btnCheckReaderID.setBounds(323, 52, 90, 21);
        btnCheckBookID.setBounds(323, 86, 90, 20);
        btnLoanBook.setBounds(565, 22, 100, 28);
        btnReturnBook.setBounds(700, 22, 87, 28);
        btnResetLoan.setBounds(819, 22, 94, 28);
        btnPunish.setBounds(750, 72, 170, 28);
        btnLoanList.setBounds(565, 72, 170, 28);

        lblLoanSort.setBounds(565, 120, 300, 28);
        cbbLoanSort.setBounds(565, 155, 170, 28);
        lblLoanSearch.setBounds(565, 185, 200, 28);
        cbbLoanSearch.setBounds(565, 215, 200, 28);
        tfLoanSearch.setBounds(800, 215, 170, 30);
        btnSearchLoan.setBounds(1000, 215, 120, 30);

        pnlLoan.add(lblLoanID);
        pnlLoan.add(lblReaderId);
        pnlLoan.add(lblBookId);
        pnlLoan.add(lblLoanNo);
        pnlLoan.add(lblBorrowedDate);
        pnlLoan.add(lblReturnAppointmentDate);
        pnlLoan.add(tfLoanID);
        pnlLoan.add(tfReaderID2);
        pnlLoan.add(tfBookID2);
        pnlLoan.add(tfLoanNO);
        pnlLoan.add(btnCheckReaderID);
        pnlLoan.add(btnCheckBookID);
        pnlLoan.add(btnLoanBook);
        pnlLoan.add(btnReturnBook);
        pnlLoan.add(btnLoanList);
        pnlLoan.add(lblOutputReader);
        pnlLoan.add(lblOutputBook);
        pnlLoan.add(btnResetLoan);
        pnlLoan.add(btnPunish);
        pnlLoan.add(lblLoanSort);
        pnlLoan.add(cbbLoanSort);
        pnlLoan.add(lblLoanSearch);
        pnlLoan.add(cbbLoanSearch);
        pnlLoan.add(btnSearchLoan);
        pnlLoan.add(tfLoanSearch);

        cbbLoanSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lController.sortLoanBy(tableModel, table, loanList, cbbLoanSort);
            }
        });

        tabbedPane.addTab("Mượn trả sách", pnlLoan);

        // Thong Ke
        pnlStatistical = new JPanel();
        pnlStatistical.setLayout(null);

        lblStatisticTotalBook = new JLabel();
        lblStatisticTotalLoan = new JLabel();
        lblStatisticLoan = new JLabel();
        lblStatisticPunish = new JLabel();

        lblStatisticTotalBook.setBounds(389, 10, 200, 28);
        lblStatisticTotalLoan.setBounds(389, 51, 200, 28);
        lblStatisticLoan.setBounds(389, 89, 200, 28);
        lblStatisticPunish.setBounds(389, 127, 200, 28);

        pnlStatistical.add(lblStatisticTotalBook);
        pnlStatistical.add(lblStatisticTotalLoan);
        pnlStatistical.add(lblStatisticLoan);
        pnlStatistical.add(lblStatisticPunish);
        tabbedPane.addTab("Thống kê", pnlStatistical);
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tabbedPane.getSelectedIndex()) {
                    case 0:
                        bController.displayBook(tableModel, table, bookList);
                        break;
                    case 1:
                        rController.displayReader(tableModel, table, readerList);
                        break;
                    case 2:
                        lController.displayLoan(tableModel, table, loanList);
                        break;
                    case 3:
                        sController.displayStatistic(tableModel, table, bookList, loanList, punishmentList);
                        sController.ghiFileStatistic(statisticPath, table);
                        break;
                }
            }
        });

        container.add(tabbedPane);
        this.setContentPane(container);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnResetBook) {
            tfBookID1.setText(null);
            tfBookName.setText(null);
            tfPageNo.setText(null);
            tfLanguage.setText(null);
            tfPrice.setText(null);
            tfAmount.setText(null);
            cbbPublishYear.setSelectedIndex(0);
            cbbType.setSelectedIndex(0);
            tfAuthor.setText(null);
            tfPublisher.setText(null);
        } else if (e.getSource() == btnAddBook) {
            Book book = new Book();
            if (tfBookID1.getText() == null || tfBookName.getText() == null || tfPageNo.getText() == null
                    || tfLanguage.getText() == null || tfPrice.getText() == null || tfAuthor.getText() == null
                    || tfPublisher.getText() == null || tfAmount.getText() == null) {
                JOptionPane.showMessageDialog(container, "Thông tin sách không được để trống");
            } else if (tfBookID1.getText().trim().isEmpty() || tfBookName.getText().trim().isEmpty() || tfPageNo.getText().trim().isEmpty()
                    || tfLanguage.getText().trim().isEmpty() || tfPrice.getText().trim().isEmpty() || tfAuthor.getText().trim().isEmpty()
                    || tfPublisher.getText().trim().isEmpty() || tfAmount.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(container, "Thông tin sách không được để trống");
            } else {
                String bookID = tfBookID1.getText();
                for (Book b : bookList) {
                    if (b.getBookID().equalsIgnoreCase(bookID)) {
                        JOptionPane.showMessageDialog(container, "Mã sách đã tồn tại");
                        return;
                    }
                }
                book.setBookID(tfBookID1.getText());
                book.setBookName(tfBookName.getText());
                book.setPageNo(tfPageNo.getText());
                book.setLanguage(tfLanguage.getText());
                book.setPrice(tfPrice.getText());
                book.setAuthor(tfAuthor.getText());
                book.setPublisher(tfPublisher.getText());
                book.setAmount(tfAmount.getText());
                book.setPublishYear(String.valueOf(cbbPublishYear.getSelectedItem()));
                book.setType(String.valueOf(cbbType.getSelectedItem()));
                bookList.add(book);
                bController.displayBook(tableModel, table, bookList);
                bController.ghiFileBook(bookPath, bookList);
            }
        } else if (e.getSource() == btnDeleteBook) {
            if (bookList.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Không có quyển sách nào trong thư viện");
                return;
            }
            String bookID = JOptionPane.showInputDialog("Nhập ID sách cần xóa: ");
            for (Book book : bookList) {
                if (book.getBookID().equalsIgnoreCase(bookID)) {
                    bookList.remove(book);
                    JOptionPane.showMessageDialog(container, "Thông tin của quyển sách đã được xóa thành công");
                    bController.displayBook(tableModel, table, bookList);
                    bController.ghiFileBook(bookPath, bookList);
                    return;
                }
            }
            if (bookID != null) {
                JOptionPane.showMessageDialog(container, "Không tìm thấy thông tin của quyển sách có ID: " + bookID);
            }
        } else if (e.getSource() == btnUpdateBook) {
            if (bookList.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Không có quyển sách nào trong thư viện");
                return;
            }
            String bookID = JOptionPane.showInputDialog("Nhập ID sách cần sửa thông tin");
            for (Book book : bookList) {
                if (book.getBookID().equalsIgnoreCase(bookID)) {
                    JFrame inputForm = new JFrame("Nhập lại thông tin sách");
                    inputForm.setSize(500, 300);
                    inputForm.setLayout(null);
                    inputForm.setLocationRelativeTo(null);
                    inputForm.setVisible(true);

                    JLabel lblNewBookName = new JLabel("Tên sách:");
                    JLabel lblNewPageNo = new JLabel("Số trang:");
                    JLabel lblNewLanguage = new JLabel("Ngôn ngữ:");
                    JLabel lblNewPrice = new JLabel("Giá:");
                    JLabel lblNewAmount = new JLabel("Số lượng:");
                    JLabel lblNewPublishYear = new JLabel("Năm xuất bản:");
                    JLabel lblNewType = new JLabel("Thể loại:");
                    JLabel lblNewAuthor = new JLabel("Tác giả:");
                    JLabel lblNewPublisher = new JLabel("Nhà xuất bản:");

                    inputForm.add(lblNewBookName);
                    inputForm.add(lblNewPageNo);
                    inputForm.add(lblNewLanguage);
                    inputForm.add(lblNewPrice);
                    inputForm.add(lblNewAmount);
                    inputForm.add(lblNewPublishYear);
                    inputForm.add(lblNewType);
                    inputForm.add(lblNewAuthor);
                    inputForm.add(lblNewPublisher);

                    lblNewBookName.setBounds(10, 21, 73, 26);
                    lblNewPageNo.setBounds(10, 53, 73, 26);
                    lblNewLanguage.setBounds(10, 89, 73, 26);
                    lblNewPrice.setBounds(10, 125, 81, 26);
                    lblNewPublishYear.setBounds(245, 21, 81, 19);
                    lblNewType.setBounds(245, 53, 81, 26);
                    lblNewAuthor.setBounds(245, 89, 86, 26);
                    lblNewPublisher.setBounds(245, 125, 86, 26);
                    lblNewAmount.setBounds(245, 161, 73, 19);

                    JTextField tfNewBookName = new JTextField();
                    JTextField tfNewPageNo = new JTextField();
                    JTextField tfNewLanguage = new JTextField();
                    JTextField tfNewPrice = new JTextField();
                    JTextField tfNewAmount = new JTextField();
                    JTextField tfNewAuthor = new JTextField();
                    JTextField tfNewPublisher = new JTextField();

                    tfNewBookName.setBounds(70, 21, 123, 19);
                    tfNewPageNo.setBounds(70, 53, 123, 19);
                    tfNewLanguage.setBounds(70, 89, 123, 19);
                    tfNewPrice.setBounds(70, 125, 123, 19);
                    tfNewAuthor.setBounds(336, 89, 123, 19);
                    tfNewPublisher.setBounds(336, 125, 123, 19);
                    tfNewAmount.setBounds(336, 161, 123, 19);

                    JComboBox cbbNewPublishYear = new JComboBox();
                    JComboBox cbbNewType = new JComboBox();

                    cbbNewPublishYear.setBounds(336, 20, 123, 20);
                    cbbNewType.setBounds(336, 55, 123, 23);
                    cbbNewPublishYear.setMaximumRowCount(13);

                    cbbNewPublishYear.setModel(new DefaultComboBoxModel(new String[]{"2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000"}));
                    cbbNewType.setModel(new DefaultComboBoxModel(new String[]{"Giáo trình", "Tài liệu tham khảo", "Luận án", "Luận văn", "Sách bài tập"}));

                    JButton btNewUpdate = new JButton("Cập nhật");
                    btNewUpdate.setBounds(190, 215, 103, 26);
                    inputForm.add(tfNewBookName);
                    inputForm.add(tfNewPageNo);
                    inputForm.add(tfNewLanguage);
                    inputForm.add(tfNewAmount);
                    inputForm.add(tfNewPrice);
                    inputForm.add(cbbNewPublishYear);
                    inputForm.add(tfNewAuthor);
                    inputForm.add(tfNewPublisher);
                    inputForm.add(cbbNewType);
                    inputForm.add(btNewUpdate);

                    btNewUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (tfNewBookName.getText() == null || tfNewPageNo.getText() == null
                                    || tfNewLanguage.getText() == null || tfNewPrice.getText() == null || tfNewAuthor.getText() == null
                                    || tfNewPublisher.getText() == null || tfNewAmount.getText() == null) {
                                JOptionPane.showMessageDialog(container, "Thông tin sách không được để trống");
                            } else if (tfNewBookName.getText().trim().isEmpty() || tfNewPageNo.getText().trim().isEmpty()
                                    || tfNewLanguage.getText().trim().isEmpty() || tfNewPrice.getText().trim().isEmpty() || tfNewAuthor.getText().trim().isEmpty()
                                    || tfNewPublisher.getText().trim().isEmpty() || tfNewAmount.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(container, "Thông tin sách không được để trống");
                            } else {
                                book.setBookName(tfNewBookName.getText());
                                book.setPageNo(tfNewPageNo.getText());
                                book.setLanguage(tfNewLanguage.getText());
                                book.setPrice(tfNewPrice.getText());
                                book.setAuthor(tfNewAuthor.getText());
                                book.setPublisher(tfNewPublisher.getText());
                                book.setAmount(tfNewAmount.getText());
                                book.setPublishYear(String.valueOf(cbbNewPublishYear.getSelectedItem()));
                                book.setType(String.valueOf(cbbNewType.getSelectedItem()));
                                JOptionPane.showMessageDialog(inputForm, "Đã cập nhật thông tin sách thành công");
                                bController.displayBook(tableModel, table, bookList);
                                bController.ghiFileBook(bookPath, bookList);
                                inputForm.setVisible(false);
                            }

                        }
                    });
                    return;
                }
            }
            if (bookID != null) {
                JOptionPane.showMessageDialog(container, "Không tìm thấy thông tin của quyển sách có ID: " + bookID);
            }
        } else if (e.getSource() == btnSearchBook) {
            if (bookList.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Không có quyển sách nào trong thư viện");
                return;
            }
            String inputSearch = tfSearchBook.getText();
            String parameter = String.valueOf(cbbFindBy.getSelectedItem());
            bController.searchBook(tableModel, table, bookList, inputSearch, parameter);
            tfSearchBook.setText(null);
        } else if (e.getSource() == btnResetReader) {
            tfReaderID1.setText(null);
            tfSurname.setText(null);
            tfReaderName.setText(null);
            tfIdentityCard.setText(null);
            tfPhoneNumber.setText(null);
            rdoStudent.setSelected(true);
            dateRange.setDate(null);
        } else if (e.getSource() == btnAddReader) {
            model.Reader reader = new model.Reader();
            if (tfReaderID1.getText() == null || tfSurname.getText() == null || tfReaderName.getText() == null
                    || tfIdentityCard.getText() == null || tfPhoneNumber.getText() == null || dateRange.getCalendar() == null) {
                JOptionPane.showMessageDialog(container, "Thông tin của độc giả không được để trống");
                return;
            }
            if (tfReaderID1.getText().trim().length() == 0 || tfSurname.getText().trim().length() == 0 || tfReaderName.getText().trim().length() == 0
                    || tfIdentityCard.getText().trim().length() == 0 || tfPhoneNumber.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(container, "Thông tin của độc giả không được để trống");
                return;
            }
            reader.setReaderID(tfReaderID1.getText());
            for (model.Reader rd : readerList) {
                if (rd.getReaderID().equalsIgnoreCase(tfReaderID1.getText())) {
                    JOptionPane.showMessageDialog(container, "Mã độc giả đã tồn tại");
                    return;
                }
            }
            reader.setSurname(tfSurname.getText());
            reader.setName(tfReaderName.getText());
            reader.setIdentityCard(tfIdentityCard.getText());
            reader.setPhone(tfPhoneNumber.getText());
            Date selectedDate = dateRange.getDate();
            reader.setCardIssueDate(df.format(selectedDate));
            if (rdoLecturer.isSelected()) {
                reader.setJob(rdoLecturer.getText());
            } else if (rdoStudent.isSelected()) {
                reader.setJob(rdoStudent.getText());
            }
            readerList.add(reader);
            rController.displayReader(tableModel, table, readerList);
            rController.ghiFileReader(readerPath, readerList);
        } else if (e.getSource() == btnDeleteReader) {
            if (readerList.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Không có độc giả nào");
                return;
            }
            String idReader = JOptionPane.showInputDialog("Nhập mã độc giả cần xóa");
            for (model.Reader reader : readerList) {
                if (reader.getReaderID().equalsIgnoreCase(idReader)) {
                    readerList.remove(reader);
                    JOptionPane.showMessageDialog(container, "Đã xóa thông tin độc giả thành công");
                    rController.displayReader(tableModel, table, readerList);
                    rController.ghiFileReader(readerPath, readerList);
                    return;
                }
            }
            if (idReader != null) {
                JOptionPane.showMessageDialog(container, "Không tìm thấy thông tin độc giả có mã độc giả: " + idReader);
            }
        } else if (e.getSource() == btnUpdateReader) {
            if (readerList.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Không có độc giả nào");
                return;
            }
            String idReader = JOptionPane.showInputDialog("Nhập mã độc giả bạn muốn sửa thông tin");
            for (model.Reader reader : readerList) {
                if (reader.getReaderID().equalsIgnoreCase(idReader)) {
                    JFrame inputForm = new JFrame("Nhập lại thông tin độc giả");
                    inputForm.setSize(600, 350);
                    inputForm.setLayout(null);
                    inputForm.setLocationRelativeTo(null);
                    inputForm.setVisible(true);

                    JLabel lblNewSurname = new JLabel("Họ đệm:");
                    JLabel lblNewName = new JLabel("Tên:");
                    JLabel lblNewCCCD = new JLabel("CCCD:");
                    JLabel lblNewPhone = new JLabel("Số điện thoại:");
                    JLabel lblNewDateRange = new JLabel("Ngày cấp thẻ:");
                    JLabel lblNewPosition = new JLabel("Chức vụ:");

                    JTextField tfNewSurname = new JTextField();
                    JTextField tfNewName = new JTextField();
                    JTextField tfNewCCCD = new JTextField();
                    JTextField tfNewPhone = new JTextField();
                    JDateChooser tfNewDateRange = new JDateChooser();
                    tfNewDateRange.setDateFormatString("yyyy-MM-dd");
                    JRadioButton rdoNewLecturer = new JRadioButton("Giảng viên");
                    JRadioButton rdoNewStudent = new JRadioButton("Sinh viên");
                    rdoNewStudent.setSelected(true);
                    ButtonGroup newButtonGroup = new ButtonGroup();

                    newButtonGroup.add(rdoNewLecturer);
                    newButtonGroup.add(rdoNewStudent);

                    JButton newUpdate = new JButton("Cập nhật");

                    lblNewSurname.setBounds(64, 25, 80, 26);
                    lblNewName.setBounds(64, 58, 80, 26);
                    lblNewCCCD.setBounds(64, 94, 80, 29);
                    lblNewPhone.setBounds(64, 133, 80, 26);
                    lblNewDateRange.setBounds(64, 170, 80, 26);
                    tfNewDateRange.setBounds(170, 170, 246, 26);
                    lblNewPosition.setBounds(64, 203, 80, 26);
                    rdoNewLecturer.setBounds(170, 203, 90, 21);
                    rdoNewStudent.setBounds(290, 203, 90, 21);

                    tfNewSurname.setBounds(170, 25, 246, 26);
                    tfNewName.setBounds(170, 61, 246, 26);
                    tfNewCCCD.setBounds(170, 97, 246, 26);
                    tfNewPhone.setBounds(170, 135, 246, 26);

                    newUpdate.setBounds(210, 230, 140, 30);
                    newUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (tfNewSurname.getText() == null || tfNewName.getText() == null
                                    || tfNewCCCD.getText() == null || tfNewPhone.getText() == null || tfNewDateRange.getCalendar() == null) {
                                JOptionPane.showMessageDialog(container, "Thông tin của độc giả không được để trống");
                                return;
                            }
                            if (tfNewSurname.getText().trim().length() == 0 || tfNewName.getText().trim().length() == 0
                                    || tfNewCCCD.getText().trim().length() == 0 || tfNewPhone.getText().trim().length() == 0) {
                                JOptionPane.showMessageDialog(container, "Thông tin của độc giả không được để trống");
                                return;
                            }
                            reader.setSurname(tfNewSurname.getText());
                            reader.setName(tfNewName.getText());
                            reader.setIdentityCard(tfNewCCCD.getText());
                            reader.setPhone(tfNewPhone.getText());
                            Date selectedDate = tfNewDateRange.getDate();
                            DateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            reader.setCardIssueDate(newDateFormat.format(selectedDate));
                            if (rdoNewLecturer.isSelected()) {
                                reader.setJob(rdoNewLecturer.getText());
                            } else if (rdoNewStudent.isSelected()) {
                                reader.setJob(rdoNewStudent.getText());
                            }
                            rController.displayReader(tableModel, table, readerList);
                            rController.ghiFileReader(readerPath, readerList);
                            inputForm.setVisible(false);
                        }
                    });
                    inputForm.add(lblNewSurname);
                    inputForm.add(lblNewName);
                    inputForm.add(lblNewCCCD);
                    inputForm.add(lblNewPhone);
                    inputForm.add(lblNewDateRange);
                    inputForm.add(lblNewPosition);

                    inputForm.add(tfNewSurname);
                    inputForm.add(tfNewName);
                    inputForm.add(tfNewCCCD);
                    inputForm.add(tfNewPhone);
                    inputForm.add(tfNewDateRange);
                    inputForm.add(rdoNewLecturer);
                    inputForm.add(rdoNewStudent);

                    inputForm.add(newUpdate);
                    return;
                }
            }
            if (idReader != null) {
                JOptionPane.showMessageDialog(container, "Không tìm thấy thông tin độc giả có mã độc giả: " + idReader);
            }

        } else if (e.getSource() == btnSearchReader) {
            if (readerList.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Không có độc giả nào");
                return;
            }
            String inputSearch = tfSearchReader.getText();
            String parameter = String.valueOf(cbbFindReader.getSelectedItem());
            rController.searchReader(tableModel, table, readerList, inputSearch, parameter);
            tfSearchReader.setText(null);
        } else if (e.getSource() == btnCheckReaderID) {
            try {
                boolean check = false;
                String idReader = tfReaderID2.getText();
                for (model.Reader reader : readerList) {
                    if (reader.getReaderID().equalsIgnoreCase(idReader)) {
                        check = true;
                        lblOutputReader.setText(reader.getName());
                        lblOutputReader.setForeground(Color.GREEN);
                        break;
                    }
                }
                if (!check) {
                    lblOutputReader.setText("Không tìm thấy độc giả");
                    lblOutputReader.setForeground(Color.RED);
                }
            } catch (Exception exception) {
                lblOutputReader.setText("Không tìm thấy độc giả");
                lblOutputReader.setForeground(Color.RED);
            }
        } else if (e.getSource() == btnCheckBookID) {
            try {
                String idBook = tfBookID2.getText();
                boolean check = false;
                for (Book book : bookList) {
                    if (book.getBookID().equalsIgnoreCase(idBook)) {
                        lblOutputBook.setText(book.getBookName());
                        lblOutputBook.setForeground(Color.GREEN);
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    lblOutputBook.setText("Không tìm thấy sách");
                    lblOutputBook.setForeground(Color.RED);
                }

            } catch (Exception exception) {
                lblOutputBook.setText("Không tìm thấy sách");
                lblOutputBook.setForeground(Color.RED);
            }
        } else if (e.getSource() == btnLoanBook) {
            Loan loan = new Loan();
            if (tfLoanID.getText() == null || tfReaderID2.getText() == null || tfBookID2.getText() == null
                    || tfLoanNO.getText() == null || borrowedDate.getCalendar() == null || payAppoinmentDate.getCalendar() == null) {
                JOptionPane.showMessageDialog(container, "Thông tin mượn trả sách không được để trống");
                return;
            }
            if (tfLoanID.getText().trim().length() == 0 || tfReaderID2.getText().trim().length() == 0 || tfBookID2.getText().trim().length() == 0 || tfLoanNO.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(container, "Thông tin mượn trả sách không được để trống");
                return;
            }
            for (Loan l : loanList) {
                if (l.getLoanID().equalsIgnoreCase(tfLoanID.getText())) {
                    JOptionPane.showMessageDialog(container, "Mã mượn trả đã tồn tại");
                    return;
                }
            }
            for (Book book : bookList) {
                if (book.getBookID().equalsIgnoreCase(tfBookID2.getText())) {
                    if (Integer.valueOf(book.getAmount()) < Integer.valueOf(tfLoanNO.getText())) {
                        JOptionPane.showMessageDialog(container, "Số lượng sách còn lại không đủ để mượn");
                        return;
                    }
                }
            }
            LocalDate date1 = borrowedDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate date2 = payAppoinmentDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long res = ChronoUnit.DAYS.between(date1, date2);
            if (res < 0) {
                JOptionPane.showMessageDialog(container, "Ngày hẹn trả không hợp lệ");
                return;
            }
            for (Book book : bookList) {
                if (book.getBookID().equalsIgnoreCase(tfBookID2.getText())) {
                    book.setAmount(String.valueOf(Integer.valueOf(book.getAmount()) - Integer.valueOf(tfLoanNO.getText())));
                    break;
                }
            }
            boolean checkReaderID = false, checkBookID = false;
            for (Book book : bookList) {
                if (book.getBookID().equalsIgnoreCase(tfBookID2.getText())) {
                    checkBookID = true;
                    break;
                }
            }
            for (model.Reader reader : readerList) {
                if (reader.getReaderID().equalsIgnoreCase(tfReaderID2.getText())) {
                    checkReaderID = true;
                    break;
                }
            }
            if (checkBookID && checkReaderID) {
                DateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date ngayMuon = borrowedDate.getDate();
                Date ngayHenTra = payAppoinmentDate.getDate();
                loan.setLoanID(tfLoanID.getText());
                loan.setReaderID(tfReaderID2.getText());
                loan.setBookID(tfBookID2.getText());
                loan.setLoanNo(tfLoanNO.getText());
                loan.setStatus("Chưa trả");
                loan.setBookReturnDate(" ");
                loan.setLoanDate(newDateFormat.format(ngayMuon));
                loan.setBookReturnAppointmentDate(newDateFormat.format(ngayHenTra));
                loanList.add(loan);
                lController.ghiFileLoan(loanPath, loanList);
                lController.displayLoan(tableModel, table, loanList);
            } else {
                JOptionPane.showMessageDialog(container, "Kiểm tra lại thông tin về mã độc giả và mã sách");
            }
        } else if (e.getSource() == btnReturnBook) {
            if (loanList.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Danh sách mượn trả hiện tại đang trống");
                return;
            }

            JFrame inputForm = new JFrame("Nhập thông tin trả sách");
            inputForm.setSize(400, 300);
            inputForm.setLayout(null);
            inputForm.setLocationRelativeTo(null);
            inputForm.setVisible(true);

            JLabel lblnewLoanId = new JLabel("Mã mượn trả:");
            JLabel lblnewReaderId = new JLabel("Mã độc giả:");
            JLabel lblnewBookId = new JLabel("Mã sách:");
            JLabel lblPayDate = new JLabel("Ngày trả:");

            JTextField tfnewLoanId = new JTextField();
            JTextField tfnewReaderID = new JTextField();
            JTextField tfnewBookID = new JTextField();

            JButton returnBook = new JButton("Trả sách");

            payDate = new JDateChooser();
            payDate.setDateFormatString("yyyy-MM-dd");
            lblnewLoanId.setBounds(65, 22, 100, 20);
            lblnewReaderId.setBounds(65, 52, 100, 20);
            lblnewBookId.setBounds(65, 85, 100, 20);
            lblPayDate.setBounds(65, 118, 100, 20);

            tfnewLoanId.setBounds(175, 23, 138, 20);
            tfnewReaderID.setBounds(175, 53, 138, 20);
            tfnewBookID.setBounds(175, 86, 138, 20);

            returnBook.setBounds(160, 160, 85, 30);
            payDate.setBounds(175, 120, 138, 20);

            returnBook.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tfnewLoanId.getText() == null || tfnewReaderID.getText() == null || tfnewBookID.getText() == null || payDate.getCalendar() == null) {
                        JOptionPane.showMessageDialog(container, "Thông tin không được để trống");
                        return;
                    }
                    if (tfnewLoanId.getText().trim().length() == 0 || tfnewReaderID.getText().trim().length() == 0 || tfnewBookID.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(container, "Thông tin không được để trống");
                        return;
                    }
                    LocalDate ngayMuon = borrowedDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate ngayHenTra = payAppoinmentDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate ngayTra = payDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    long res1 = ChronoUnit.DAYS.between(ngayMuon, ngayTra);
                    long res2 = ChronoUnit.DAYS.between(ngayHenTra, ngayTra);
                    if (res1 < 0) {
                        JOptionPane.showMessageDialog(container, "Ngày trả không hợp lệ");
                        return;
                    }
                    for (Loan loan : loanList) {
                        if (loan.getLoanID().equalsIgnoreCase(tfnewLoanId.getText()) && loan.getReaderID().equalsIgnoreCase(tfnewReaderID.getText()) && loan.getBookID().equalsIgnoreCase(tfnewBookID.getText())) {
                            loan.setStatus("Đã trả");
                            DateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            loan.setBookReturnDate(newDateFormat.format(payDate.getDate()));
                            for (Book book : bookList) {
                                if (book.getBookID().equalsIgnoreCase(tfnewBookID.getText())) {
                                    book.setAmount(String.valueOf(Integer.valueOf(book.getAmount()) + Integer.valueOf(loan.getLoanNo())));
                                    break;
                                }
                            }
                            if (res2 > 0) {
                                String fullName = "", nameBook = "";
                                for (model.Reader reader : readerList) {
                                    if (reader.getReaderID().equalsIgnoreCase(tfnewReaderID.getText())) {
                                        fullName += reader.getSurname() + " " + reader.getName();
                                        break;
                                    }
                                }
                                for (Book book : bookList) {
                                    if (book.getBookID().equalsIgnoreCase(tfnewBookID.getText())) {
                                        nameBook = book.getBookName();
                                        break;
                                    }
                                }
                                punishmentList.add(new Punishment(tfLoanID.getText(), tfnewReaderID.getText(),
                                        fullName, tfnewBookID.getText(), nameBook, tfLoanNO.getText(), String.valueOf(res2), String.valueOf(2000 * res2), loan.getStatus()));
                                pController.ghiFilePunishment(punishmentPath, punishmentList);
                            }
                            lController.displayLoan(tableModel, table, loanList);
                            lController.ghiFileLoan(loanPath, loanList);
                            inputForm.setVisible(false);
                            return;
                        }

                    }
                }
            });
            inputForm.add(lblnewLoanId);
            inputForm.add(lblnewReaderId);
            inputForm.add(lblnewBookId);
            inputForm.add(tfnewLoanId);
            inputForm.add(tfnewReaderID);
            inputForm.add(tfnewBookID);
            inputForm.add(lblPayDate);
            inputForm.add(returnBook);
            inputForm.add(payDate);

        } else if (e.getSource() == btnResetLoan) {
            tfLoanID.setText(null);
            tfReaderID2.setText(null);
            tfBookID2.setText(null);
            tfLoanNO.setText(null);
            lblOutputReader.setText(null);
            lblOutputBook.setText(null);
            payAppoinmentDate.setCalendar(null);
            borrowedDate.setCalendar(null);
        } else if (e.getSource() == btnPunish) {
            pController.displayPunishment(tableModel, table, punishmentList);
        } else if (e.getSource() == btnLoanList) {
            lController.displayLoan(tableModel, table, loanList);
        } else if (e.getSource() == btnSearchLoan) {
            if (loanList.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Không có phiếu mượn trả nào");
                return;
            }
            String inputSearch = tfLoanSearch.getText();
            String parameter = String.valueOf(cbbLoanSearch.getSelectedItem());
            lController.searchLoanBy(tableModel, table, loanList, inputSearch, parameter);
            tfLoanSearch.setText(null);
        }
    }
    
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
    }
}
