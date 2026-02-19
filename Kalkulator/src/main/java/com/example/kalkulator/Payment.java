package com.example.kalkulator;

public class Payment {

    private int month;
    private double oldBalance;
    private double payment;
    private double interest;
    private double capital;
    private double remaining;

    public Payment() {
    }

    public Payment(int month, double oldBalance, double payment, double interest, double capital, double remaining) {
        this.month = month;
        this.oldBalance = oldBalance;
        this.payment = payment;
        this.interest = interest;
        this.capital = capital;
        this.remaining = remaining;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(double oldBalance) {
        this.oldBalance = oldBalance;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }
}

