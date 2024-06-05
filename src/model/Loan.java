package model;

import java.io.Serializable;


public class Loan implements Serializable {
    private String loanID;
    private String bookID;
    private String readerID;
    private String loanNo;
    private String loanDate;
    private String bookReturnAppointmentDate;
    private String bookReturnDate;
    private String status;

    public Loan() {
    }

    public Loan(String loanID, String bookID, String readerID, String loanNo, String loanDate, String bookReturnAppointmentDate, String bookReturnDate, String status) {
        this.loanID = loanID;
        this.bookID = bookID;
        this.readerID = readerID;
        this.loanNo = loanNo;
        this.loanDate = loanDate;
        this.bookReturnAppointmentDate = bookReturnAppointmentDate;
        this.bookReturnDate = bookReturnDate;
        this.status = status;
    }

    public String getLoanID() {
        return loanID;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getBookReturnAppointmentDate() {
        return bookReturnAppointmentDate;
    }

    public void setBookReturnAppointmentDate(String bookReturnAppointmentDate) {
        this.bookReturnAppointmentDate = bookReturnAppointmentDate;
    }

    public String getBookReturnDate() {
        return bookReturnDate;
    }

    public void setBookReturnDate(String bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return loanID + "," + bookID + "," + readerID + ","
                + loanNo + "," + loanDate + "," + bookReturnAppointmentDate
                + "," + bookReturnDate + "," + status;
    }
}
