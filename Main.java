package com.rumendior.series;

public class Main {

    public static void main(String[] args) {
        ClientCard rumen = new ClientCard("Rumen", "Pakov", "    3 ", null, 99);
        rumen.purchase(100000, 850);
        System.out.println(rumen.getCredentials());
        ClientCard georgi = new ClientCard("Georgi", " Ivanov ", "0882710663", ClientCard.Card.SILVER, -23);
        georgi.purchase(850);
        ClientCard ivan = new ClientCard("Ivan", " _Dimitrov", "123123123", ClientCard.Card.SILVER, 0);
        ivan.purchase(0.3d);
        ClientCard iliaina = new ClientCard("Iliana", " _", "123123123", ClientCard.Card.BRONZE, 500000);
        iliaina.purchase(0, 850);
    }
}
