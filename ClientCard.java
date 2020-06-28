package com.rumendior.series;

import java.nio.channels.FileLockInterruptionException;

public class ClientCard{
    //it was not specified if setters and card type update method are required, so i did not include them, in order to preserve encapsulation

    private Card cardType;
    private double turnover;
    private double discount;
    private  String firstName;
    private  String lastName;
    private String phoneNumber;

    public enum Card {
        BRONZE,
        SILVER,
        GOLD
    }

    public ClientCard(String firstName, String lastName, String phoneNumber, Card cardType, int turnover) throws IllegalArgumentException {
        updateCredentials(firstName, lastName, phoneNumber);
        if(cardType != null) {
            this.cardType = cardType;
        }
        else {
            this.cardType = Card.BRONZE;
        }
        getDiscountRate(turnover);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Card getCardType() {
        return cardType;
    }

    public double getTurnover() {
        return turnover;
    }

    public double getDiscount() {
        return discount;
    }

    public void updateCredentials(String firstName, String lastName, String phoneNumber) {
        if(firstName.replaceAll("\\s","").isEmpty()) {
            System.out.println("First name can not be empty");
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        if(lastName.replaceAll("\\s","").isEmpty()) {
            System.out.println("Last name can not be empty");
            throw new IllegalArgumentException();
        }
        this.lastName = lastName;
        if(phoneNumber.replaceAll("\\s","").isEmpty()) {
            System.out.println("Phone number can not be empty");
            throw new IllegalArgumentException();
        }
        this.phoneNumber = phoneNumber;
    }

    public String getCredentials() {
        return "First name: " + firstName + "\nLast name: " + lastName + "\nPhone Number: " + phoneNumber;
    }

    private double getDiscountRate(double newTurnover) {
        turnover = newTurnover;
        if(cardType == Card.BRONZE) {
            if(turnover >= 100d && turnover <= 300d) {
                discount = 1d;
            }
            else if(turnover > 300d) {
                discount = 2.5d;
            }
            else {
                discount = 0d;
            }
        }
        else if(cardType == Card.SILVER) {
            if(turnover >= 300d) {
                discount = 3.5d;
            }
            else {
                discount = 2d;
            }
        }
        else if(cardType == Card.GOLD) {
            if((int)turnover > 1000) {
                discount = 12d;
            }
            else if(turnover <= 99) {
                discount = 2;
            }
            else {
                discount = 2 + ((int)turnover/100);
            }
        }
        return discount;
    }

    public void purchase(double turnover, double purchaseValue) { //this overloaded method accepts new turnover and uses it to calculate the discount rate, as well as setting this turnover as current
        getDiscountRate(turnover);
        System.out.println("Turnover updated");
        purchase(purchaseValue);
    }

    public void purchase(double purchaseValue) throws IllegalArgumentException{ //this method uses the current(last given) turnover to calculate discount rate
        if(purchaseValue <= 0) {
            System.out.println("Invalid purchase value");
            throw new IllegalArgumentException();
        }

        double currentDiscount = purchaseValue * (discount / 100);
        double total = purchaseValue - currentDiscount;
        print(purchaseValue,currentDiscount, total);
    }

    public void print(double purchaseValue, double currentDiscount, double total) {
        StringBuilder builder = new StringBuilder();

        builder.append("Purchase value: $").append(String.format("%.2f", purchaseValue)).append("\n");
        builder.append("Discount rate: ").append(String.format("%.1f", discount)).append("%\n");
        builder.append("Discount: $").append(String.format("%.2f", currentDiscount)).append("\n");
        builder.append("Total: $").append(total).append("\n");

        System.out.println(builder.toString());
    }
}
