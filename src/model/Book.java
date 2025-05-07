package model;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookID;
    private String bookName;
    private String pageNo;
    private String language;
    private String price;
    private String amount;
    private String publishYear;
    private String type;
    private String author;
    private String publisher;

    public Book() {
    }

    public Book(String bookID, String bookName, String pageNo, String language, String price, String amount, String publishYear, String type, String author, String publisher) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.pageNo = pageNo;
        this.language = language;
        this.price = price;
        this.amount = amount;
        this.publishYear = publishYear;
        this.type = type;
        this.author = author;
        this.publisher = publisher;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return bookID + "," + bookName + "," + pageNo + ","
                + language + "," + price + "," + amount + ","
                + publishYear + "," + type + "," + author + ","
                + publisher ;
    }
}
