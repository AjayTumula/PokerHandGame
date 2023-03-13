package org.example;

import java.util.ArrayList;


public class Hand {
    private ArrayList<Card> cards;
    private int combination;
    private int highCard;

    public Hand(String[] input) {
        cards = new ArrayList<>(5);
        for (int i = 0; i < input.length; i++)
            cards.add(new Card(input[i]));
        sort();
    }

    private void sort() {
        for (int i = 0; i < cards.size(); i++) {
            int indexOfMin = i, min = getValue(i);
            for (int j = 0; j < cards.size(); j++) {
                if (getValue(j) < min) {
                    min = getValue(j);
                    indexOfMin = j;
                }
            }
            Card temp = getCard(i);
            cards.set(i, getCard(indexOfMin));
            cards.set(indexOfMin, temp);
        }
    }

    public void printHand() {
        for (int i = 0; i < cards.size(); i++)
            System.out.println(Integer.toString(getValue(i)) + getSuit(i));
    }

    public int getValue(int i) {
        return cards.get(i).getValue();
    }

    public char getSuit(int i) {
        return cards.get(i).getSuit();
    }

    public int getCombination() {
        return combination;
    }

    public int getHighCard() {
        return highCard;
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public void setCombination(int combination) {
        this.combination = combination;
    }

    public void setHighCard(int i) {
        highCard = i;
    }
}

