package com.example.kalkulator;

import java.util.ArrayList;
import java.util.List;

public class Loan {

    private int duration;  //years
    private double ir;     //interest rate
    private double amount; //Capital amount

    private double totalInterest;
    private double totalPaid;

    List<Payment> payments;

    private double monthlyPayment;


    public Loan(){
        payments = new ArrayList<>();
        totalInterest = 0;
        totalPaid = 0;
    }

    public Loan(int duration, double ir, double amount) {
        this.duration = duration;
        this.ir = ir;
        this.amount = amount;
        payments = new ArrayList<>();
        totalInterest = 0;
        totalPaid = 0;
    }
    /*
     * monthly payment
     * rate for each month
     * months for duration
     * monthly payment
     *  */
    public double calcPayment(double rate, int months){
        return (rate * amount) / (1 - Math.pow(1 + rate, -months));
    }

    /*
     * Function to build loan schedule
     * */
    public void buildTable(){

        double monthlyRate = ir / 12 /100;

        int months = duration * 12;

        monthlyPayment = calcPayment(monthlyRate, months);

        double irPayment, amountPaid, newBalance;

        double balance = amount;

        for (int month = 1; month <= months; month++) {
            irPayment = balance * monthlyRate; // Corrected this line
            amountPaid = monthlyPayment - irPayment;
            newBalance = balance - amountPaid;

            Payment object = new Payment(month, balance, monthlyPayment, irPayment, amountPaid, newBalance);

            payments.add(object);

            this.totalInterest += irPayment;
            balance = newBalance; // Update balance for the next iteration
        }


        totalPaid = amount + totalInterest;

    }

    public double getTotalInterest() {

        return totalInterest;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public double getMonthlyPayment() {
        return this.monthlyPayment;
    }


}


