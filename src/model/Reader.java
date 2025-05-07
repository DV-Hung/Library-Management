package model;

import java.io.Serializable;

public class Reader implements Serializable {
    private String readerID;
    private String surname;
    private String name;
    private String identityCard;
    private String phone;
    private String cardIssueDate;
    private String job;

    public Reader() {
    }

    public Reader(String readerID, String surname, String name, String identityCard, String phone, String cardIssueDate, String job) {
        this.readerID = readerID;
        this.surname = surname;
        this.name = name;
        this.identityCard = identityCard;
        this.phone = phone;
        this.cardIssueDate = cardIssueDate;
        this.job = job;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardIssueDate() {
        return cardIssueDate;
    }

    public void setCardIssueDate(String cardIssueDate) {
        this.cardIssueDate = cardIssueDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return readerID + "," + surname + "," + name + ","
                + identityCard + "," + phone + "," + cardIssueDate + ","
                + job;
    }
}
