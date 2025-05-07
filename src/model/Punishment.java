package model;

import java.io.Serializable;

public class Punishment implements Serializable {
    private String loanID;
    private String readerID;
    private String fullName;
    private String bookID;
    private String book;
    private String loanNo;
    private String daysLate;
    private String total;
    private String status;

    public Punishment() {
    }

    public Punishment(String loanID, String readerID, String fullName, String bookID, String book, String loanNo, String daysLate, String total, String status) {
        this.loanID = loanID;
        this.readerID = readerID;
        this.fullName = fullName;
        this.bookID = bookID;
        this.book = book;
        this.loanNo = loanNo;
        this.daysLate = daysLate;
        this.total = total;
        this.status = status;
    }

    public String getLoanID() {
        return loanID;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(String daysLate) {
        this.daysLate = daysLate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return loanID + "," + readerID + ","
                + fullName + "," + bookID + ","
                + book + "," + loanNo + ","
                + daysLate + "," + total + ","
                + status;
    }
}
